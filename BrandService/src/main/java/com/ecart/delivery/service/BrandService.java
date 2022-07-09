package com.ecart.delivery.service;

import java.util.List;
import java.util.Optional;

import com.ecart.delivery.dto.Category;
import com.ecart.delivery.model.Brand;

public interface BrandService {

	public List<Category> fetchAll();

	String addNewBrand(Brand brand, int categoryId);

	Optional<Category> fetchById(int categoryId);

	String addOrUpdateBrand(Brand brand, int categoryId);

	String addNewListsOfBrandsWithSameCategoryName(List<Brand> brandLists, int categoryId);

	String addOrUpdateListsOfBrandsWithSameCategoryName(List<Brand> brandLists, int categoryId);

	String deleteExistingBrand(int brandId);

	List<Brand> fetchAllBrandsWithCategories();

	List<Brand> fetchBrandByCategoryName(String categoryName);

	Optional<Brand> fetchByBrandName(String brandName);

	Optional<Brand> fetchByBrandId(int brandId);

}
