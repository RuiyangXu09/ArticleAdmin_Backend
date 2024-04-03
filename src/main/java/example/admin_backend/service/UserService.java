package example.admin_backend.service;

import example.admin_backend.domain.User;

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
}
