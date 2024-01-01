package com.cms.Services;

import java.util.List;

import com.cms.Dto.BlogDto;
import com.cms.Models.Blog;

public interface BlogService {
	
	public String saveBlog(BlogDto blog);
	
	public List<Blog> showBlogs();
	
}
