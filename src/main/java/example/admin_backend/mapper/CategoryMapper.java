package example.admin_backend.mapper;

import example.admin_backend.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 新增文章分类
     * @param category
     */
    void addCategory(Category category);

    /**
     * 分类列表查询
     * @param userId 仅查询当前登录id下创建的分类
     * @return
     */
    List<Category> getCategoryList(Integer userId);

    /**
     * 根据id获取分类详情
     * @param id
     * @return
     */
    Category getCategoryDetails(Integer id);

    /**
     * 更新分类名
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除分类
     * @param id
     */
    void deleteCategory(Integer id);
}
