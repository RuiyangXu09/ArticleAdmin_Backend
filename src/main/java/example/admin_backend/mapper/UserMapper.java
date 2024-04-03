package example.admin_backend.mapper;

import example.admin_backend.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username} ")
    User findByUsername(String username);

    @Insert("INSERT INTO user(username, password, nickname, email) VALUES (#{username}, #{password}, #{nickname}, #{email})")
    void register(String username, String password, String nickname, String email);
}
