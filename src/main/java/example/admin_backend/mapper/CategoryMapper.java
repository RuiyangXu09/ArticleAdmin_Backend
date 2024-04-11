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
    @Insert("INSERT INTO category(category_name, create_user) VALUES (#{categoryName} , #{createUser})")
    void addCategory(Category category);

    /**
     * 分类列表查询
     * @param userId 仅查询当前登录id下创建的分类
     * @return
     */
    @Select("SELECT category.*, user.username " +
            "FROM category  " +
            "LEFT JOIN user ON category.create_user = user.id " +
            "WHERE category.create_user = #{userId}")
    List<Category> getCategoryList(Integer userId);

    /**
     * 根据id获取分类详情
     * @param id
     * @return
     */
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category getCategoryDetails(Integer id);

    /**
     * 更新分类名
     * @param category
     */
    @Update("UPDATE category SET category_name = #{categoryName} WHERE id = #{id}")
    void updateCategory(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategory(Integer id);
}
