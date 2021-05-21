package com.yangyu.web;




import com.fasterxml.jackson.databind.ObjectMapper;

import com.yangyu.dto.BlogDto;
import com.yangyu.po.Blog;
import com.yangyu.service.BlogService;
import com.yangyu.service.BlogTagService;
import com.yangyu.service.TagService;
import com.yangyu.service.TypeService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BlogerIndex {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogTagService blogTagService;

    //展示 type
    @GetMapping("/allTypes")
    @ResponseBody
    public Result showIndexTypes(Long userId)  {
        return Result.ok().data("types",typeService.findTopTypes(userId));
    }
    @GetMapping("/allTags")
    @ResponseBody
    public Result showIndexTags(Long userId)  {
        return Result.ok().data("tags",tagService.findTopTags(userId));
    }


    //展示 blog
   @GetMapping("/blogPage")
   @ResponseBody
    public Result showBlog(@RequestParam int pageNumber,@RequestParam Long userId){
          if (pageNumber==-1){pageNumber=0;}
       List<BlogDto> blogDtos=blogService.findBlogP(pageNumber,10,userId);
       List<Blog> blogs = new ArrayList<>();
       for (BlogDto blogDto:blogDtos){
           Blog blog = new Blog();
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
           blog.setTags(blogTagService.findTagByBlog(blogDto.getId()));
           blogs.add(blog);
       }
         return  Result.ok().data("blogs",blogs).data("count",blogService.count(userId));
    }


    //根据views 排热门blog
    @GetMapping("/hotBlogs")
    @ResponseBody
    public Result hotBlog(){
        return Result.ok().data("hotBlogs",blogService.findHotBlog());
    }




}
