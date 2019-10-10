package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Long> {

}

