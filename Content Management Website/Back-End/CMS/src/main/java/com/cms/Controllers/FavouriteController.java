package com.cms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Dto.FavouriteDto;
import com.cms.Services.FavouriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cms/like")
public class FavouriteController {
	
	@Autowired
	private FavouriteService fser;
	
	@PostMapping("/addlike")
	public String addFavourite(FavouriteDto fav) {
		return fser.addLike(fav);	
	}
}
