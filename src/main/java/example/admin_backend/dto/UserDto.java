package example.admin_backend.dto;

import lombok.Data;

/**
 * 创建用户的dto类过滤敏感信息
 */
@Data
public class UserDto {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatarUrl;
}
