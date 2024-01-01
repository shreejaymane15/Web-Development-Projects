package com.cms.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.BlogDao;
import com.cms.Dao.FavouriteDao;
import com.cms.Dao.UserDao;
import com.cms.Dto.FavouriteDto;
import com.cms.Models.Blog;
import com.cms.Models.Favourite;
import com.cms.Models.User;

@Service
public class FavouriteServiceImpl implements FavouriteService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BlogDao bdao;
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private FavouriteDao fdao;
	
	@Override
	public String addLike(FavouriteDto fav) {
		// TODO Auto-generated method stub
		Blog blog = bdao.findById(fav.getBlogId()).orElse(null);
		User user = udao.findById(fav.getUserId()).orElse(null);
		
		if(blog != null && user != null) {
			try {
				Favourite favourite = mapper.map(fav, Favourite.class);
				
				favourite.setBlog(blog);
				favourite.setUser(user);
				
				fdao.save(favourite);
				return "Post Liked";
				
				
			}catch(Exception e) {
				
				return "Something went wrong";
			
			}
		}else if(blog == null) {
			return "blog is null";
		}else {
			return "user is null";
		}
	}

}
