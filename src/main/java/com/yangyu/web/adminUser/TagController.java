package com.yangyu.web.adminUser;

import com.yangyu.mapper.BlogTagMapper;
import com.yangyu.po.Tag;
import com.yangyu.po.Type;
import com.yangyu.po.User;
import com.yangyu.service.BlogTagService;
import com.yangyu.service.TagService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    BlogTagService blogTagService;

    @GetMapping("/tagControl")
    @ResponseBody
    public Result showAllTags(int pageNum,HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
        if (pageNum==-1){pageNum=0;}
        return Result.ok().data("tags",tagService.findAllTags(pageNum,userId));
    }

    //增加
    @GetMapping("/addTag")
    @ResponseBody
    public Result addTag(String name, HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
        tagService.saveTag(name,userId);
        return  Result.ok().data("message","成功");
    }

    //删除
    @GetMapping("/deleteTag/{id}")
    @ResponseBody
    public Result deleteTag(@PathVariable long id){
        blogTagService.updateTag(id);
        tagService.deleteTag(id);
        return  Result.ok().data("message","成功");
    }

    //填充 未作修改 的页面
    @GetMapping("/updateTag/{id}")
    @ResponseBody
    public Result showThisTag(@PathVariable Long id){
        return  Result.ok().data("tag",tagService.getById(id));
    }


    //提交修改后的 tag
    @GetMapping("/updateTag")
    @ResponseBody
    public Result updateTag(@RequestParam String name,@RequestParam Long id, HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
        if (tagService.getByName(name,userId)==null){
            Tag tag = new Tag();
            tag.setId(id);
            tag.setName(name);
            tagService.updateTag(tag);
            return Result.ok().data("message","over");
        }
        else{ return Result.error().data("message","名儿重了 换个");}
    }

}
