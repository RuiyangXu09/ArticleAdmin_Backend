package example.admin_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询数据的结果的封装类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    /**
     * 数据库总记录数
     */
    private Integer totalRows;
    /**
     * 接收返回当前页面的记录数，多条记录数（对象），使用list类型接收
     */
    private List<T> rows;
}
