package com.yangyu.po;

//import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//标签
//@Entity
//@Table(name = "T_tag")
public class Tag {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
    public Tag() {
    }
    public  Tag(Long id,String name,List<Blog> blogs){
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
