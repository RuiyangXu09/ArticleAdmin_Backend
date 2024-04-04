package example.admin_backend.service;

import example.admin_backend.domain.Article;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void addArticle(Article article);
}
