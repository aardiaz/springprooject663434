package com.appsoft.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springproject.model.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	
}
