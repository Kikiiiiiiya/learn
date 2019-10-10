package edu.hubu.learn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Blog;
import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;
import edu.hubu.learn.service.BlogService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(1l);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @RequestMapping("/blog")
    public ModelAndView blog() {
        ModelAndView mav = new ModelAndView();
        Blog blog =blogService.getBlog(1l);
        mav.addObject("blog", blog);
        mav.setViewName("user");
        return mav;
    }
}