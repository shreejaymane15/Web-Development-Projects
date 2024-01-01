package com.cms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Dto.BlogDto;
import com.cms.Models.Blog;
import com.cms.Services.BlogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cms/blogs")
public class BlogsController {
	
	
	@Autowired
	private BlogService bser;
	
	@PostMapping("/add")
	public String addBlog(BlogDto blog) {
		
		return bser.saveBlog(blog);
	}
	
	@GetMapping("/displayAllBlogs")
	public List<Blog> displayAll(){
		
		return bser.showBlogs();
	
	}
	
}
