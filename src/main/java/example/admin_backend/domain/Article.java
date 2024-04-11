package example.admin_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import example.admin_backend.anno.State;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    //@State
    private String state;
    private Integer categoryId;
    //LEFT JOIN数据显示需要categoryName
    private String categoryName;
    private Integer createUser;
    //LEFT JOIN数据显示需要对应id的username
    private String username;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
