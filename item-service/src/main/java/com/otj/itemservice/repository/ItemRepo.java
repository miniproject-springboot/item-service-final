package com.otj.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otj.itemservice.model.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
	 
	Item findByName(String name);
	
}
