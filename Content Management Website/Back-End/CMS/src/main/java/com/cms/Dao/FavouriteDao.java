package com.cms.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.Models.Favourite;

public interface FavouriteDao extends JpaRepository<Favourite, Long> {

}
