package com.otj.itemservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.otj.itemservice.model.Item;
import com.otj.itemservice.repository.ItemRepo;

@RestController
public class Itemcontroller {
	
	@Autowired
	private ItemRepo itemRepo;
	
	@GetMapping("service2/items")
	public List<Item> getItems() {
		return itemRepo.findAll();
	}
	@GetMapping("service2/items/{itemname}")
	@HystrixCommand(fallbackMethod = "fallbackGetItems")
	public Item getItems(@PathVariable String itemname) {
		
		if(itemRepo.findByName(itemname) != null) {
			return itemRepo.findByName(itemname);
			
		}else {
			throw new RuntimeException("Item Not available");
		}
		
	}
	
	public Item fallbackGetItems(@PathVariable String itemname) {
		return new Item(0L,"dummyItem","Dummy Desc",0);
	}

}
