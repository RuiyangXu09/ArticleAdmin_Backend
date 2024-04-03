package example.admin_backend.service.impl;

import example.admin_backend.domain.User;
import example.admin_backend.mapper.UserMapper;
import example.admin_backend.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
