package com.cms.Services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.BlogDao;
import com.cms.Dao.UserDao;
import com.cms.Dto.BlogDto;
import com.cms.Models.Blog;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogDao bdao;
	
//	@Autowired
//	private ModelMapper mapper;
	
//	@Autowired
//	private UserDao udao;
	
	@Override
	public String saveBlog(BlogDto blog){
		
		
		return "";
	}
	
	
	@Override
	public List<Blog> showBlogs(){
		return bdao.findAll();
	}
	
}
