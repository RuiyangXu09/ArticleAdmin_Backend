package example.admin_backend.mapper;

import example.admin_backend.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 新增文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 获取数据库中的总记录数
     * @return
     */
    Integer countRows();

    /**
     * 分页查询，使用left join关联查询
     * @param startIndex 起始索引
     * @param pageSize 总页码数
     * @return
     */
    List<Article> pageArticleList(Integer startIndex, Integer pageSize);

    /**
     * 获取文章信息
     * @param id 传入的id
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
     * @param id
     */
    void deleteArticle(Integer id);
}
