package example.admin_backend.mapper;

import example.admin_backend.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    /**
     * 新增文章
     * @param article
     */
    @Insert("INSERT INTO article(title, summary, cover_img, state, category_id, create_user, create_time, update_time) " +
            "VALUES (#{title}, #{summary}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})  ")
    void addArticle(Article article);
}
