package example.admin_backend.service;

import example.admin_backend.domain.Article;
import example.admin_backend.domain.Page;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 分页查询文章
     * @param page
     * @param pageSize
     * @return
     */
    Page<Article> pageArticleList(Integer page, Integer pageSize);

    /**
     * 获取文章信息
     * @param id
     * @return
     */
    Article getArticleDetails(Integer id);

    /**
     * 修改文章信息
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     * @param id 前端传递的id参数
     */
    void deleteArticle(Integer id);
}
