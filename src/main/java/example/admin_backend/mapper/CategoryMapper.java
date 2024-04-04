package example.admin_backend.mapper;

import example.admin_backend.domain.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("SELECT * FROM category WHERE create_user = #{userId}")
    List<Category> getCategory(Integer userId);
}