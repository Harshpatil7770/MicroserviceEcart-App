package com.xoriant.ecart.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.xoriant.ecart.dto.Brand;
import com.xoriant.ecart.dto.Category;
import com.xoriant.ecart.dto.ProductDTO;

public interface ProductService {

	Optional<Brand> fetchByBrandId(@RequestParam int brandId);

	public Optional<Category> fetchById(@RequestParam int id);

	String saveOrUpdateProduct(ProductDTO productDTO, int brandId, int id);
}
