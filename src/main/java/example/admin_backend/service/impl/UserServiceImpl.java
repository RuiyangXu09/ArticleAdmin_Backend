package example.admin_backend.service.impl;

import example.admin_backend.domain.User;
import example.admin_backend.dto.UserDto;
import example.admin_backend.mapper.UserMapper;
import example.admin_backend.service.UserService;
import example.admin_backend.utils.ThreadLocalUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 注入userMapper的bean
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password, String nickname, String email) {
        //使用bcrypt对用户密码加密
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        userMapper.register(username, password, nickname, email);
    }

    @Override
    public UserDto userInfoById(Integer id) {
        User user = userMapper.userInfoById(id);

        //引入dto层对象，对密码等信息脱敏
        UserDto userDto = new UserDto();
        //对userDto对象设置必要传入信息
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());
        userDto.setAvatarUrl(user.getAvatarUrl());
        //返回一个userDto类的对象
        return userDto;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        //通过ThreadLocal获取id，作为参数坐标传入sql
        Map<String, Object> map = ThreadLocalUtils.get();
        Integer id = (Integer) map.get("id");
        //传入参数url和id
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePassword(String password) {
        //通过ThreadLocal获取id，作为参数坐标传入sql
        Map<String, Object> map = ThreadLocalUtils.get();
        Integer id = (Integer) map.get("id");
        //加密密码
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        userMapper.updatePassword(password, id);
    }
}
