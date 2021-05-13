package com.yangyu.web.adminUser;

import com.yangyu.po.Blog;
import com.yangyu.dto.BlogDto;
import com.yangyu.service.BlogService;
import com.yangyu.service.BlogTagService;
import com.yangyu.service.TypeService;
import com.yangyu.service.UserService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class blogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;
    @Autowired
    BlogTagService blogTagService;

    @GetMapping("/blog/{pageNum}")
    @ResponseBody
    public Result showAllBlogs(@PathVariable int pageNum){
        if (pageNum==-1){pageNum=0;}
        return Result.ok().data("blogs",blogService.findBlog(pageNum,3));
    }


    //增加
    @GetMapping("/addBlog")
    @ResponseBody
    public Result addBlog(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setAppreciation(blog.isAppreciation());
        blog.setCommentabled(blog.isCommentabled());
        blog.setContent(blog.getContent());
        blog.setDescription(blog.getDescription());
        blog.setFirstPicture(blog.getFirstPicture());
        blog.setFlag(blog.getFlag());
        blog.setPublished(blog.isPublished());
        blog.setRecommend(blog.isRecommend());
        blog.setShareStatement(blog.isShareStatement());
        blog.setTitle(blog.getTitle());
        blog.setViews(blog.getViews());
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blogService.save(blog);
        for (Long tagId:blogDto.getTagIds()){
            blogTagService.addBlogTag(blog.getId(),tagId);
        }
        return  Result.ok().data("message","成功");
    }

    //删除
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result deleteTag(@PathVariable Long id){
        blogService.delete(id);
        if (blogTagService.deleteBlogTag(id)>0)
        return  Result.ok().data("message","成功");
        else return Result.error().data("blogTag关系","删除失败");
    }

    //填充 未作修改 的页面
    @GetMapping("/updateBlog/{id}")
    @ResponseBody
    public Result showThisTag(@PathVariable Long id){
        return  Result.ok().data("tag",blogService.getById(id));
    }

    //提交修改后的 blog
    @GetMapping("/updateBlog")
    @ResponseBody
    public Result updateTag(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setAppreciation(blog.isAppreciation());
        blog.setCommentabled(blog.isCommentabled());
        blog.setContent(blog.getContent());
        blog.setDescription(blog.getDescription());
        blog.setFirstPicture(blog.getFirstPicture());
        blog.setFlag(blog.getFlag());
        blog.setPublished(blog.isPublished());
        blog.setRecommend(blog.isRecommend());
        blog.setShareStatement(blog.isShareStatement());
        blog.setTitle(blog.getTitle());
        blog.setViews(blog.getViews());
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blogService.update(blog);

        blogTagService.deleteBlogTag(blogDto.getId());
        for (Long tagId:blogDto.getTagIds()){
            blogTagService.addBlogTag(blog.getId(),tagId);
        }
        return Result.ok().data("message","ok");
    }






}
