package example.admin_backend.service.impl;

import example.admin_backend.domain.Category;
import example.admin_backend.mapper.CategoryMapper;
import example.admin_backend.service.CategoryService;
import example.admin_backend.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Category> getCategoryList() {
        //仅查询当前登录id下创建的category，获取ThreadLocal中的id
        Map<String, Object> map = ThreadLocalUtils.get();
        Integer userId = (Integer) map.get("id");
        //传入一个userId，在sql中作为参数查询属于当前id的数据
        return categoryMapper.getCategoryList(userId);
    }

    @Override
    public Category getCategoryDetails(Integer id) {
        return categoryMapper.getCategoryDetails(id);
    }
}
