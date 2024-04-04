package example.admin_backend.service;

import example.admin_backend.domain.Category;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void addCategory(Category category);
}
