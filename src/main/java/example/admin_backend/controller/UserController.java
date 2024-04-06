package example.admin_backend.controller;

import example.admin_backend.domain.User;
import example.admin_backend.dto.UserDto;
import example.admin_backend.service.UserService;
import example.admin_backend.utils.Jwt;
import example.admin_backend.utils.Result;
import example.admin_backend.utils.ThreadLocalUtils;
import io.micrometer.common.util.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 注入userService的bean
     */
    @Autowired
    private UserService userService;
    /**
     * 注入jwt的bean
     */
    @Autowired
    private Jwt jwt;
    /**
     * 引入redis的bean存放token
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册并检查是否有重复的用户名
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/registerUser")
    public Result<User> registerUser(String username, String password, String nickname, String email){
        //检测用户名是否重复
        User user = userService.findByUsername(username);
        //检测用户名和密码是否为空
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            if (user != null){
                //如果username重复，返回一个error
                return Result.error("Username is duplicated.");
            }else {
                //没有占用，传入参数，返回成功信息
                userService.register(username, password, nickname, email);
                return Result.success();
            }
        }else {
            //用户名和密码为空返回一个错误信息
            return Result.error("Username and Password cannot be empty.");
        }
    }

    /**
     * 用户登录
     * @param username 前端传入的用户名
     * @param password 前端传入的密码
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(String username, String password){
        //根据username找用户信息
        User loginUser = userService.findByUsername(username);
        //检测用户名和密码是否为空
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            if (loginUser != null){
                //检测密码是否正确，是就返回true
                boolean passwordMatched = BCrypt.checkpw(password, loginUser.getPassword());
                if (passwordMatched){
                    //登陆成功，携带token，不包括密码
                    Map<String, Object> claims = new HashMap<>();
                    //将需要携带的信息放入token
                    claims.put("id", loginUser.getId());
                    claims.put("username", loginUser.getUsername());
                    claims.put("avatarUrl", loginUser.getAvatarUrl());
                    claims.put("email", loginUser.getEmail());
                    String token = jwt.generateJwt(claims);
                    /*
                    此时在这里应该将token设置到redis中，token作为键值对同时存入
                    当用户修改密码时应该重新登录获取新的token，同时在拦截器验证携带的token并寻找redis中存放的token
                    需要同时获取到之前存在redis中的token，获取不到就是失效
                    用户修改密码成功后，需要删除存放在redis中的旧token
                     */
                    //引入redis
                    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                    //将token作为key和value同时存入，之后获取的token只需要比对同样的键能不能获取到对应的值就可以判断token有没有失效，同时设置过期时间，防止redis中数据过多
                    operations.set(token, token, 3, TimeUnit.DAYS);
                    //返回一个token
                    return Result.success(token);
                }else {
                    //密码错误false，返回错误信息
                    return Result.error("Password is not correct");
                }
            }else {
                return Result.error("Username is not correct");
            }
        }else {
            return Result.error("Username and Password cannot be empty.");
        }
    }

    /**
     * 获取用户信息，token中携带了username，从token中获取，通过ThreadLocal获取在拦截其中set完成的业务数据
     * @return
     */
    @GetMapping(value = "/userInfoById")
    public Result<UserDto> userInfoById(){
        //通过ThreadLocalUtils获取解析好的token数据，在拦截器中已经将解析好的token数据存入了ThreadLocal中
        Map<String, Object> map = ThreadLocalUtils.get();
        //通过key值获取username
        Integer id = (Integer) map.get("id");
        //传入username获取用户对象的信息
        UserDto userDto = userService.userInfoById(id);
        return Result.success(userDto);
    }

    /**
     * 更新用户信息，添加注解@RequestBody将json格式的请求数据转换为实体类对象
     * @param user
     * @return
     */
    @PutMapping(value = "/updateUser")
    public Result<User> updateUser(@RequestBody User user){
        //判断当前登录的用户id是否等于需要修改的用户的id，调用ThreadLocal中存储的id
        Map<String, Object> map = ThreadLocalUtils.get();
        //当前进程中登录的id
        Integer loginId = (Integer) map.get("id");
        //判断需要修改的用户的id是否等于当前进程的用户id
        if (user.getId().equals(loginId)){
            //判断用户的nickname不能为空
            if (StringUtils.isNotBlank(user.getNickname())){
                userService.updateUser(user);
                return Result.success();
            }else {
                return Result.error("Nickname cannot be empty");
            }
        }else {
            return Result.error("Not your Information");
        }
    }

    /**
     * 更新用户头像，当调用阿里云oss完成上传时，返回一个url地址存储
     * 注解@RequestParam表明需要从query中获取String数据
     * @param avatarUrl
     * @return
     */
    @PatchMapping(value = "/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 修改用户密码
     * 注解@RequestParam表明需要从query中获取String数据
     * 注解@RequestHeader String token此时请求头中已经包含了token
     * @return
     */
    @PatchMapping(value = "/updatePassword")
    public Result updatePassword(String password, @RequestHeader("token") String token){
        //TODO 考虑使用邮箱验证码完成修改密码的校验
        if (StringUtils.isNotBlank(password)){
            userService.updatePassword(password);
            //当密码更新成功后，应该删除之前存放在redis中的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            //链式调用，delete方法删除token
            operations.getOperations().delete(token);
            //修改成功的响应
            return Result.success();
        }else {
            return Result.error("Password cannot be empty.");
        }

    }
}
