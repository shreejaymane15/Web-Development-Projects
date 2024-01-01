package com.cms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Services.CommentService;
import com.cms.Dto.CommentDto;


@CrossOrigin("*")
@RestController
@RequestMapping("/cms/comment")
public class CommentsController {
	
	@Autowired
	private CommentService cser;
	
	@PostMapping("/post")
	public String postComment(@RequestBody CommentDto comment){
		return cser.addComment(comment);
	}
}
