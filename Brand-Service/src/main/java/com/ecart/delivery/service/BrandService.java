package com.ecart.delivery.service;

import java.util.List;
import java.util.Optional;

import com.ecart.delivery.dto.BrandDTO;
import com.ecart.delivery.dto.Category;
import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.model.Brand;

public interface BrandService {

	String addNewBrand(BrandDTO brandDTO);

	String addNewCategory(CategoryDTO categoryDTO);

	Optional<Category> fetchById(int id);

	List<Brand> fetchAllBrandsWithTheirCategories();

	List<BrandDTO> fetchAll();

}
