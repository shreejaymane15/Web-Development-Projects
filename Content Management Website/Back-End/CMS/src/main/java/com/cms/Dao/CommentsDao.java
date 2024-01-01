package com.cms.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.Models.Comment;

public interface CommentsDao extends JpaRepository<Comment, Long>{
	
}
