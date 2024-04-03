package example.admin_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 文章实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String summary;
    private String coverImg;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDate createTime;
    private LocalDate updateTime;
}
