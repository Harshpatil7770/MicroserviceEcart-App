package com.xoriant.ecart.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xoriant.ecart.dto.Category;

@FeignClient(name = "http://localhost:9090")
public interface CategoryServiceProxy {

	@GetMapping("/api/categories/find-category")
	public Optional<Category> fetchById(@RequestParam int id);
}
