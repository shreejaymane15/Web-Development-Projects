package com.cms.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.Models.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>{
	
}
