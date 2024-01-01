package com.cms.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.BlogDao;
import com.cms.Dao.CommentsDao;
import com.cms.Dao.UserDao;
import com.cms.Dto.CommentDto;
import com.cms.Models.Blog;
import com.cms.Models.Comment;
import com.cms.Models.User;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentsDao cdao;
	
	@Autowired
	private BlogDao bdao;
	
	@Autowired
	private UserDao udao;
	
	@Autowired	
	private ModelMapper mapper;
	
	@Override
	public String addComment(CommentDto comment) {
		
		Blog blog = bdao.findById(comment.getBlogId()).orElse(null);
		
		User user = udao.findById(comment.getUserId()).orElse(null);
		
		if(blog != null && user != null) {					
			try {
				Comment mappedComment = mapper.map(comment, Comment.class);
				mappedComment.setBlog(blog);
				mappedComment.setUser(user);
				
				cdao.save(mappedComment);
				return "Comment Added";
			
			}catch(Exception e){
				return "Comment not added";
			}
		}else if(blog != null){
		
			return "User Not Found";
		
		}else {
		
			return "Blog Not Found"; 
		}
	}
}
