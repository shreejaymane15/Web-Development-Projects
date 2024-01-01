package com.cms.Dto;



public class CommentDto {
	
	private String comment;
	
	private Long blogId;
	
	private Long userId;

	public CommentDto() {
		super();
	}
	
	

	public CommentDto(String comment, Long blogId, Long userId) {
		super();
		this.comment = comment;
		this.blogId = blogId;
		this.userId = userId;
	}



	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "CommentDto [comment=" + comment + ", blogId=" + blogId + ", userId=" + userId + "]";
	}
	
	
	

}
