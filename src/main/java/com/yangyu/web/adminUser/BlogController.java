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
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;
    @Autowired
    BlogTagService blogTagService;

    @GetMapping("/blog")
    @ResponseBody
    public Result showAllBlogs(int pageNum,Long userId){
        if (pageNum==-1){pageNum=0;}
        return Result.ok().data("blogs",blogService.findBlog(pageNum,3,userId));
    }


    //增加
    @PostMapping("/addBlog")
    @ResponseBody
    public Result addBlog(@RequestBody BlogDto blogDto){
        Blog blog = new Blog();
        blog.setAppreciation(blogDto.isAppreciation());
        blog.setCommentabled(blogDto.isCommentabled());
        blog.setContent(blogDto.getContent());
        blog.setDescription(blogDto.getDescription());
        blog.setFirstPicture(blogDto.getFirstPicture());
        blog.setFlag(blogDto.getFlag());
        blog.setPublished(blogDto.isPublished());
        blog.setRecommend(blogDto.isRecommend());
        blog.setShareStatement(blogDto.isShareStatement());
        blog.setTitle(blogDto.getTitle());
        blog.setViews(0);
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blogService.save(blog);
        Long blogId = blog.getId();
        for (Long tagId:blogDto.getTagIds()){
            blogTagService.addBlogTag(blogId,tagId);
        }
        return  Result.ok().data("message","成功");
    }

    //删除
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result deleteTag(@PathVariable Long id){
        blogService.delete(id);
        if (blogTagService.deleteBlogTag(id)>0 || blogTagService.count(id)<1)
        return  Result.ok().data("message","成功");
        else return Result.error().data("blogTag关系","删除失败");
    }

    //填充 未作修改 的页面
    @PostMapping("/updateBlog/{id}")
    @ResponseBody
    public Result showThisBlog(@PathVariable Long id){
        Blog blog = new Blog();
        BlogDto blogDto = blogService.getById(id);
        blog.setId(blogDto.getId());
        blog.setAppreciation(blogDto.isAppreciation());
        blog.setCommentabled(blogDto.isCommentabled());
        blog.setContent(blogDto.getContent());
        blog.setDescription(blogDto.getDescription());
        blog.setFirstPicture(blogDto.getFirstPicture());
        blog.setFlag(blogDto.getFlag());
        blog.setPublished(blogDto.isPublished());
        blog.setRecommend(blogDto.isRecommend());
        blog.setShareStatement(blogDto.isShareStatement());
        blog.setTitle(blogDto.getTitle());
        blog.setViews(blogDto.getViews());
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blog.setTags(blogTagService.findTagByBlog(id));
        return Result.ok().data("blog",blog);

    }

    //提交修改后的 blog
    @PostMapping("/updateBlog")
    @ResponseBody
    public Result updateTag(@RequestBody BlogDto blogDto){
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setAppreciation(blogDto.isAppreciation());
        blog.setCommentabled(blogDto.isCommentabled());
        blog.setContent(blogDto.getContent());
        blog.setDescription(blogDto.getDescription());
        blog.setFirstPicture(blogDto.getFirstPicture());
        blog.setFlag(blogDto.getFlag());
        blog.setPublished(blogDto.isPublished());
        blog.setRecommend(blogDto.isRecommend());
        blog.setShareStatement(blogDto.isShareStatement());
        blog.setTitle(blogDto.getTitle());
        blog.setViews(blogDto.getViews());
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blogService.update(blog);

        blogTagService.deleteBlogTag(blogDto.getId());
        for (Long tagId:blogDto.getTagIds()){
            blogTagService.addBlogTag(blogDto.getId(),tagId);
        }
        return Result.ok().data("message","ok");
    }






}
