package com.yangyu.web;

import com.yangyu.dto.BlogDto;
import com.yangyu.po.Blog;
import com.yangyu.service.BlogService;
import com.yangyu.service.BlogTagService;
import com.yangyu.service.TypeService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TypeShow {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogTagService blogTagService;

    @GetMapping("/allTypes")
    @ResponseBody
    public Result type(Long userId){
        return Result.ok().data("types",typeService.findTopTypes(userId));
    }


    //根据 type展示博客
    @GetMapping("/allTypes/{typeId}")
    @ResponseBody
    public Result findBlogsByType(@PathVariable Long typeId){
        if (typeId==-1){return Result.ok().data("0","未给id");}
        List<BlogDto> blogDtos=blogService.findBlogByType(typeId);
        Blog blog = new Blog();
        List<Blog> blogs = new ArrayList<>();
        for (BlogDto blogDto:blogDtos){
            blog.setId(blogDto.getId());
            blog.setTitle(blogDto.getTitle());
            blog.setContent(blogDto.getContent());
            blog.setDescription(blogDto.getDescription());
            blog.setFirstPicture(blogDto.getFirstPicture());
            blog.setFlag(blogDto.getFlag());
            blog.setViews(blogDto.getViews());
            blog.setAppreciation(blogDto.isAppreciation());
            blog.setShareStatement(blogDto.isShareStatement());
            blog.setCommentabled(blogDto.isCommentabled());
            blog.setPublished(blogDto.isPublished());
            blog.setRecommend(blogDto.isRecommend());
            blog.setCreateTime(blogDto.getCreateTime());
            blog.setUpdateTime(blogDto.getUpdateTime());
            blog.setType(typeService.getById(blogDto.getTypeId()));
            blog.setTagIds(blogTagService.findTagByBlog(blogDto.getId())+"");
            blogs.add(blog);
        }
        return Result.ok().data("blogs",blogs);
    }

}

