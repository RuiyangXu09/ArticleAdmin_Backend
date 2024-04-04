package example.admin_backend.controller;

import example.admin_backend.domain.Category;
import example.admin_backend.service.CategoryService;
import example.admin_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    /**
     * 注入categoryService的bean
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类，传入category的对象，使用注解@RequestBody，以json格式读取请求数据，然后转换为一个category类的对象
     * @param category
     * @return
     */
    @PostMapping(value = "/addCategory")
    public Result<Category> addCategory(@RequestBody Category category){
        if (category.getCategoryName() != null && !category.getCategoryName().isEmpty()){
            categoryService.addCategory(category);
            return Result.success();
        }else {
            return Result.error("category name cannot be empty.");
        }
    }

    /**
     * 分类列表查询
     * @return 返回一个list集合类型的数据
     */
    @GetMapping(value = "/getCategory")
    public Result<List<Category>> getCategory(){
        List<Category> categoryList = categoryService.getCategory();
        return Result.success(categoryList);
    }
}
