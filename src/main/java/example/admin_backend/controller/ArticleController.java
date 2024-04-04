package example.admin_backend.controller;

import example.admin_backend.domain.Article;
import example.admin_backend.domain.Page;
import example.admin_backend.service.ArticleService;
import example.admin_backend.utils.GetUserInfoUtils;
import example.admin_backend.utils.Result;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    /**
     * 分页查询文章
     * @param page 前端传递的当前页码数
     * @param pageSize 前端传递的总页码数
     * @return
     */
    @GetMapping(value = "/pageArticleList")
    public Result<Page<Article>> pageArticleList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "2") Integer pageSize){
        //封装获取的总记录数totalRows和返回的限定条数的数据列表到articleList对象中，向service层传递页码和总页码数
        Page<Article> articleList = articleService.pageArticleList(page, pageSize);
        return Result.success(articleList);
    }

    /**
     * 获取文章信息
     * @param id 前端传递的id
     * @return 返回一个article对象
     */
    @GetMapping(value = "/getArticleDetails")
    public Result getArticleDetails(Integer id){
        //判断当前线程的id是否等于获取文章内容中创建者的id
        Integer loginId = GetUserInfoUtils.getCurrentThreadLocalUserId();
        //创建一个article对象，接收查询到的article的内容，包含创建者id
        Article article = articleService.getArticleDetails(id);
        //判断文章创建者的id是否等于当前线程的用户id
        if (Objects.equals(article.getCreateUser(), loginId)){
            return Result.success(article);
        }else {
            return Result.error("Not your Information");
        }
    }
}
