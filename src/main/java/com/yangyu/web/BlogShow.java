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
     //每进入一次这个接口 view+1
        Blog b= blogService.getById(id);
       Integer newView=b.getViews()+1;
        b.setViews(newView);
        blogService.update(b);
        return Result.ok().data("thisBlog",b);
    }


    /**
     * 根据  或title 或content 或 description 中 关键字 查找博客
     *
     */
    @GetMapping("/search")
    @ResponseBody
    public  Result search(@PathVariable String keywords){
        List<Blog> blogs=blogService.findByKeywords("%"+keywords+"%");
        if (null!=blogs){  return Result.ok().data("blogsFoundByKeywords",blogs) ; }
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
        Consumer consumer=null;
        try {
             user =(User)session.getAttribute("user");
             consumer=(Consumer)session.getAttribute("consumer");
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
            comment.setParentComment(zero);
        }
        if(parentCommentId!=0){comment.setParentComment(commentService.findById(parentCommentId));}
        if (user!=null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            commentService.save(comment);
            return Result.ok().data("message","管理员评论 来点档次");
        }
       else {
           if(consumer!=null) {
               comment.setAdminComment(false);
               comment.setNickname(consumer.getNickname());
               comment.setAvatar(consumer.getAvatar());
               comment.setEmail(consumer.getEmail());
               commentService.save(comment);
               return Result.ok().data("message", "用户评论 整点花的");
           }
           else {
               comment.setAdminComment(false);
               comment.setAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F10%2F20170710021402_dn54m.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621343048&t=3e2c6f0006a0d132c351432581f1b838");
               comment.setNickname("游客");
               commentService.save(comment);
               return Result.error().data("error","非注册用户评论");
           }
       }
    }
}



