package edu.hubu.learn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        return blogDao.findAll(new Sort(Direction.DESC, "id"));
    }

    public List<Blog> searchBlogs(String keyword) {
        Blog blog = new Blog();
        blog.setTitle(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("title", match->match.contains());
        Example<Blog> example = Example.of(blog, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return blogDao.findAll(example, sort);
    }

    public Blog addBlog(Blog blog) {
        return blogDao.save(blog);
    }

    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    public void modifyBlog(Blog blog) {
        blogDao.save(blog);
    }
}