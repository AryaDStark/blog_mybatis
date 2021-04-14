package com.yangyu.web;

import com.yangyu.po.Blog;
import com.yangyu.po.Comment;
import com.yangyu.po.Consumer;
import com.yangyu.po.User;
import com.yangyu.service.BlogService;
import com.yangyu.service.CommentService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class BlogShow {

    private String avatarStr="";

    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @GetMapping("/thisBlog/{id}")
    @ResponseBody
    public Result blogShow(@PathVariable Long id){
      // .............. 每使用一次 views+1  并更新  ..........
        Blog b= blogService.getById(id);
       Integer newView=b.getViews()+1;
        b.setViews(newView);
        blogService.update(b);
      //  System.out.println(b.getViews()+1);
     //''''''''''''''''''''''''''''''''''
        return Result.ok().data("thisBlog",b);
    }


    // 根据  或title 或content 或 description 中 关键字 查找博客
    @GetMapping("/search")
    @ResponseBody
    public  Result search(@PathVariable String keywords){
        List<Blog> blogs=blogService.findByKeywords("%"+keywords+"%");
        if (null!=blogs){  return Result.ok().data("blogsFoundByKeywords",blogs) ; }
        else {return Result.error().data("noBlogsFound","noBlogsFound");}
    }


    //***************   展示 评论   ******************
    @GetMapping("/comment/{blogId}")
    @ResponseBody
    public Result commentShow(@PathVariable Long blogId){
        return Result.ok().data("comments",commentService.findCommentsByBlogId(blogId));
    }

    //************    写评论   ******************
    @GetMapping ("/say")
    @ResponseBody
    public  Result writeComment(@RequestParam String content,@RequestParam Long blogId,@RequestParam Long parentCommentId, HttpSession session){
        User user=null;
        Consumer consumer=null;
        try {
             user =(User)session.getAttribute("user");
             consumer=(Consumer)session.getAttribute("consumer");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error().data("ss","ss");
        }
//        User user =(User)session.getAttribute("user");
        Comment comment=new Comment();
        Blog blog=new Blog();
        blog.setId(blogId);
        comment.setContent(content);
        comment.setBlog(blog);
        comment.setParentComment(commentService.findById(parentCommentId));
        if (user!=null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            commentService.save(comment);
            return Result.ok().data("message","管理员评论 来点档次");
        }
       else {
           if (consumer!=null) {
               comment.setAdminComment(false);
               comment.setNickname(consumer.getNickname());
               comment.setAvatar(consumer.getAvatar());
               comment.setEmail(consumer.getEmail());
               return Result.ok().data("message", "用户评论 整点花的");
           }
           else {
               comment.setAdminComment(false);
               comment.setNickname("游客");
               return Result.error().data("error","非注册用户评论");
           }
           }
        }
    }



