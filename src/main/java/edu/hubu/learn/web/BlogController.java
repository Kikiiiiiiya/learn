package edu.hubu.learn.web;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

import edu.hubu.learn.entity.Blog;
import edu.hubu.learn.service.BlogService;
@Controller
@Slf4j

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
    @RequestMapping("/search")
    public ModelAndView searchBlog() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("blog_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchBlog(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Blog> blogs = blogService.searchBlogs(keyword);
        mav.addObject("blogs", blogs);
        mav.setViewName("blogs");
        return mav;
    }

    @RequestMapping("/add_avatar/{id}")
    public ModelAndView addBlogAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("blog", blogService.getBlog(id));
        mav.setViewName("blog_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddBlogAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Blog blog = blogService.getBlog(id);
            blog.setAvatar(fileName);
            blogService.modifyBlog(blog);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/blog/list");
    }

} 