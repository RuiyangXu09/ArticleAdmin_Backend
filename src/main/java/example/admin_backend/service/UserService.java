package example.admin_backend.service;

import example.admin_backend.domain.User;
import example.admin_backend.dto.UserDto;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户注册功能
     * @param username
     * @param password
     */
    void register(String username, String password, String nickname, String email);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UserDto userInfoById(Integer id);
}
