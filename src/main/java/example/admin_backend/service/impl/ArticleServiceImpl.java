package example.admin_backend.service.impl;

import example.admin_backend.domain.Article;
import example.admin_backend.domain.Page;
import example.admin_backend.mapper.ArticleMapper;
import example.admin_backend.service.ArticleService;
import example.admin_backend.utils.GetUserInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Page<Article> pageArticleList(Integer page, Integer pageSize) {
        //获取数据库中的总记录数
        Integer totalRows = articleMapper.countRows();
        //计算起始索引，(page - 1) * pageSize
        Integer startIndex = (page - 1) *pageSize;
        //创建一个list对象接收返回的数据，向sql语句的limits传递两个参数pageSize和startIndex
        List<Article> articleList = articleMapper.pageArticleList(startIndex, pageSize);
        //返回一个新的page对象，其中封装有总记录数totalRows和articleList的集合类型的总数据
        return new Page<>(totalRows, articleList);
    }

    @Override
    public Article getArticleDetails(Integer id) {
        return articleMapper.getArticleDetails(id);
    }

    @Override
    public void updateArticle(Article article) {
        //设置修改时间
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }
}
