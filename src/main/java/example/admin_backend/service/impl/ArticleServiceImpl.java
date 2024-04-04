package example.admin_backend.service.impl;

import example.admin_backend.domain.Article;
import example.admin_backend.mapper.ArticleMapper;
import example.admin_backend.service.ArticleService;
import example.admin_backend.utils.GetUserInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {
    /**
     * 注入articleMapper的bean
     */
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        //设置创建时间和修改时间
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        //设置创建的用户的id，从ThreadLocal中获取
        Integer loginId = GetUserInfoUtils.getCurrentThreadLocalUserId();
        article.setCreateUser(loginId);
        articleMapper.addArticle(article);
    }
}
