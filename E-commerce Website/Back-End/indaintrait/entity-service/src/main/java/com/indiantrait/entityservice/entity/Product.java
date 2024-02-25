package com.indiantrait.entityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	private Long id;
	
	private String productName;
	
	private String productDesc;
	
	
}
