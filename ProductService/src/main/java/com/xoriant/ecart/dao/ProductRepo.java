package com.xoriant.ecart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.ecart.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	Optional<Product> findByProductName(String productName);

	List<Product> findByBrandName(String brandName);

	List<Product> findByCategoryName(String categoryName);

	List<Product> findByPriceGreaterThan(double minPrice);

	List<Product> findByPriceLessThan(double maxPrice);

	List<Product> findByPriceIsBetween(double minPrice, double maxPrice);

}
