package edu.hubu.learn.web;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Blog;
import edu.hubu.learn.service.BlogService;
@Controller


@RequestMapping("/blog")

public class BlogController {

    @Autowired

    private BlogService blogService;

    @RequestMapping("/{id}")

    public ModelAndView blog(@PathVariable Long id) {

        ModelAndView mav = new ModelAndView();

        Blog blog = blogService.getBlog(id);

        mav.addObject("blog", blog);

        mav.setViewName("blog");

        return mav;

    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        ModelAndView mav = new ModelAndView("redirect:/blog/list");
        return mav;
    }


    @RequestMapping("/list")
    public ModelAndView blogs() {

        ModelAndView mav = new ModelAndView();

        List<Blog> blogs= blogService.getBlogs();

        mav.addObject("blogs", blogs);

        mav.setViewName("blogs");

        return mav;

    }

   
    @RequestMapping("/add")
    public ModelAndView addBlog() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("blog_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddBlog(Blog blog) {
        blogService.addBlog(blog);
        ModelAndView mav = new ModelAndView("redirect:/blog/list");
        return mav;
    }
    @RequestMapping("/modify/{id}")
    public ModelAndView modifyBlog(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("blog", blogService.getBlog(id));
        mav.setViewName("blog_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyBlog(Blog blog) {
        blogService.modifyBlog(blog);
        ModelAndView mav = new ModelAndView("redirect:/blog/list");
        return mav;
    }


} 