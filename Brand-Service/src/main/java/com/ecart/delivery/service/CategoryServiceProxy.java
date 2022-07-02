package com.ecart.delivery.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecart.delivery.dto.Category;
import com.ecart.delivery.dto.CategoryDTO;

@FeignClient(name="category-service")
public interface CategoryServiceProxy {

	@PostMapping("/api/categories/save")
	public String addNewCategory(@RequestBody CategoryDTO categoryDTO);
	
	@GetMapping("/api/categories/find-category")
	public Optional<Category> fetchById(@RequestParam int id);
	
}
