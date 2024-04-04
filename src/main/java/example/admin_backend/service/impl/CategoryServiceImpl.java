package example.admin_backend.service.impl;

import example.admin_backend.domain.Category;
import example.admin_backend.mapper.CategoryMapper;
import example.admin_backend.service.CategoryService;
import example.admin_backend.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    /**
     * 注入CategoryMapper的bean
     */
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        //创建人的id的获取，从ThreadLocal中获取
        Map<String, Object> map = ThreadLocalUtils.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);

        categoryMapper.addCategory(category);
    }
}
