package example.admin_backend.controller;

import example.admin_backend.domain.Article;
import example.admin_backend.service.ArticleService;
import example.admin_backend.utils.Result;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {
    /**
     * 注入articleService的bean
     */
    @Autowired
    ArticleService articleService;

    /**
     * 新增文章，json格式的请求数据以@RequestBody注解返回一个对象形式的数据
     * @param article
     * @return
     */
    @PostMapping(value = "/addArticle")
    public Result addArticle(@RequestBody @Validated Article article){
        //判断标题和内容是否为空或null
        if (StringUtils.isNotBlank(article.getTitle()) && StringUtils.isNotBlank(article.getSummary())){
            articleService.addArticle(article);
            return Result.success();
        }else {
            return Result.error("Title and Summary cannot be empty.");
        }
    }
}
