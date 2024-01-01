package com.cms.Dto;

public class FavouriteDto {

	private Long blogId;
	
	private Long userId;

	public FavouriteDto() {
		super();
	}

	public FavouriteDto(Long blogId, Long userId) {
		super();
		this.blogId = blogId;
		this.userId = userId;
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
		return "FavouriteDto [blogId=" + blogId + ", userId=" + userId + "]";
	}
	
	
	
}
