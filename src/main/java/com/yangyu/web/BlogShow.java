package com.yangyu.web;

import com.yangyu.dto.BlogDto;
import com.yangyu.po.Blog;
import com.yangyu.po.Comment;
import com.yangyu.po.User;
import com.yangyu.service.*;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BlogShow {

    private String avatarStr="";

    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogTagService blogTagService;
    @Autowired
    UserService userService;

    @GetMapping("/thisBlog/{id}")
    @ResponseBody
    public Result blogShow(@PathVariable Long id){
     //每进入一次这个接口 view+1
        BlogDto blogDto= blogService.getById(id);
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setDescription(blogDto.getDescription());
        blog.setFirstPicture(blogDto.getFirstPicture());
        blog.setFlag(blogDto.getFlag());
        blog.setViews(blogDto.getViews()+1);
        blog.setAppreciation(blogDto.isAppreciation());
        blog.setShareStatement(blogDto.isShareStatement());
        blog.setCommentabled(blogDto.isCommentabled());
        blog.setPublished(blogDto.isPublished());
        blog.setRecommend(blogDto.isRecommend());
        blog.setCreateTime(blogDto.getCreateTime());
        blog.setUpdateTime(blogDto.getUpdateTime());
        blog.setType(typeService.getById(blogDto.getTypeId()));
        blog.setTagIds(blogTagService.findTagByBlog(blogDto.getId())+"");
        blog.setTags(blogTagService.findTagByBlog(blogDto.getId()));
        blog.setUser(userService.getById(blogDto.getUserId()));
        blogService.update(blog);
        return Result.ok().data("thisBlog",blog).data("blogerName",userService.getById(blogDto.getUserId()).getNickname()).data("blogerId",blogDto.getUserId());
    }


    @GetMapping("/records")
    @ResponseBody
    public Result records(Long userId){
        return Result.ok().data("blogs",blogService.records(userId));
    }


    /**
     * 根据或title 或content 或 description 中 关键字 查找博客
     *
     */
    @GetMapping("/search")
    @ResponseBody
    public  Result search(@RequestParam("keywords")String keywords,@RequestParam("userId")Long userId){
        List<BlogDto> blogDtos=blogService.findByKeywords(keywords,userId);
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
        if (null!=blogs){  return Result.ok().data("blogsFoundByKeywords",blogs);}
        else {return Result.error().data("noBlogsFound","noBlogsFound");}
    }


    /**
     * 展示评论
     * */
    @GetMapping("/comment")
    @ResponseBody
    public Result commentShow(Long blogId){
        return Result.ok().data("comments",commentService.findNoParentComment(blogId));
    }

   /**
    * 写评论
    * */
    @GetMapping ("/say")
    @ResponseBody
    public  Result writeComment(@RequestParam String content,@RequestParam Long blogId,@RequestParam Long parentCommentId, HttpSession session){
        User user=null;
        try {
             user =(User)session.getAttribute("adminUser");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error().data("ss","ss");
        }
        Comment comment=new Comment();
        Blog blog=new Blog();
        blog.setId(blogId);
        comment.setContent(content);
        comment.setBlog(blog);
        if (parentCommentId==0){
            Comment zero=new Comment();
            zero.setId(blogId);
            zero.setId(0L);
            comment.setParentComment(zero);
        }
        if(parentCommentId!=0){comment.setParentComment(commentService.findById(parentCommentId));}
        if (user!=null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            comment.setUserId(user.getId());
            commentService.save(comment);
            return Result.ok().data("message","管理员评论 来点档次");
        }
       else {
               comment.setAdminComment(false);
               comment.setAvatar("");
               comment.setNickname("游客");
               commentService.save(comment);
               return Result.error().data("error","非注册用户评论");
       }
    }
}



