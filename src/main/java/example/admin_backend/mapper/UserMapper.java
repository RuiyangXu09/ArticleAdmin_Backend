package example.admin_backend.mapper;

import example.admin_backend.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username} ")
    User findByUsername(String username);

    /**
     * 注册用户信息
     * @param username
     * @param password
     * @param nickname
     * @param email
     */
    @Insert("INSERT INTO user(username, password, nickname, email) VALUES (#{username}, #{password}, #{nickname}, #{email})")
    void register(String username, String password, String nickname, String email);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User userInfoById(Integer id);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("UPDATE user SET nickname = #{nickname}, email = #{email} WHERE id = #{id}")
    void updateUser(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Update(("UPDATE user SET avatarUrl = #{avatarUrl} WHERE id = #{id}"))
    void updateAvatar(String avatarUrl, Integer id);
}
