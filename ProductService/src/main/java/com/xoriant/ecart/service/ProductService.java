package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.xoriant.ecart.dto.Brand;
import com.xoriant.ecart.dto.Category;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Product;

public interface ProductService {

	Optional<Brand> fetchByBrandId(@RequestParam int brandId);

	public Optional<Category> fetchById(int id);

	String saveOrUpdateProduct(ProductDTO productDTO, int brandId, int id);

	List<Product> saveOrUpdateListOfProduct(List<ProductDTO> prodLists, int brandId, int id);

	Optional<Product> findByProductName(String productName);

	Optional<Product> findById(int productId);

	List<Product> findByBrandName(String brandName);

	List<Product> findByCategoryName(String categoryName);

	List<Product> findAll();

	List<Product> findByPriceGreaterThan(double minPrice);

	List<Product> findByPriceLessThan(double maxPrice);

	List<Product> findByPriceInBetween(double minPrice, double maxPrice);

	void deleteById(int productId);
}
