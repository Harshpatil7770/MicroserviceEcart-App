package com.ecart.delivery.service;

import java.util.List;
import java.util.Optional;

import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.model.Category;

public interface CategoryService {

	String addNewCategory(CategoryDTO categoryDTO);

	String addOrUpdateCategory(CategoryDTO categoryDTO);

	String addNewListsofCategories(List<Category> catLists);

	 List<Category> addOrUpdateNewListsofCategories(List<Category> catLists);

	List<Category> fetchAll();

	Optional<Category> fetchByCategoryName(String categoryName);

	Optional<Category> fetchById(int id);

	String deleteById(int id);

}
