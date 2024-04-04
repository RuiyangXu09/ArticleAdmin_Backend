package example.admin_backend.mapper;

import example.admin_backend.domain.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 新增文章分类
     * @param category
     */
    @Insert("INSERT INTO category(category_name, create_user) VALUES (#{categoryName} , #{createUser})")
    void addCategory(Category category);
}
