package example.admin_backend.service;

import example.admin_backend.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void addCategory(Category category);

    /**
     * 分类列表查询
     * @return
     */
    List<Category> getCategoryList();

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
}
