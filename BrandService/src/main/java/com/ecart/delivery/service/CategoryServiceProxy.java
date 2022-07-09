package com.ecart.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecart.delivery.dto.Category;

@FeignClient(name = "category-service")
public interface CategoryServiceProxy {

	@GetMapping("/api/categories/findAll")
	public List<Category> fetchAll();

	@GetMapping("/api/categories/find-category")
	public Optional<Category> fetchById(@RequestParam int id);
}
