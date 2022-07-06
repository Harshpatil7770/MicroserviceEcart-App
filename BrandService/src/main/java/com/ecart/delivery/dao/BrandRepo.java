package com.ecart.delivery.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecart.delivery.model.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {

	List<Brand> findByCategoryName(String categoryName);

	Optional<Brand> findByBrandName(String brandName);

}
