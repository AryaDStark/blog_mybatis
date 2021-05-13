package com.yangyu.mapper;
import com.yangyu.dto.BlogDto;
import com.yangyu.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;

//import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Component
@Mapper
public interface BlogMapper {

    List<Blog> findBlog(@Param("n1") Integer n1,@Param("n")int num,@Param("userId")Long userId);

    void save(Blog blog);

    void delete(@Param("id")Long id);

    BlogDto getById(@Param("id")Long id);

    void update(Blog blog);

    int updateType(Long typeId);

    List<Blog>  findBlogByType(@Param("id") Long id);

    List<Blog>  findBlogByTag(@Param("id")Long id);

    Integer     count(Long userId);

    List<Blog>   findHotBlog();

    List<BlogDto>   findByKeywords(@Param("key") String keywords, @Param("userId")Long userId);
}
