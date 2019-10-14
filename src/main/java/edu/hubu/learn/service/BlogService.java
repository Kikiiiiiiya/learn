package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.BlogDao;

import edu.hubu.learn.entity.Blog;


@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public Blog getBlog(Long id) {
        return blogDao.findById(id).get();
    }
    public List<Blog> getBlogs(){



        return blogDao.findAll();

    }
    public Blog addBlog(Blog blog) {
        return blogDao.save(blog);
    }
}