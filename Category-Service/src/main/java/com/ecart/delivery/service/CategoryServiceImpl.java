package com.ecart.delivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecart.delivery.dao.CategoryDao;
import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.exceptionhandeler.ElementNotFoundException;
import com.ecart.delivery.exceptionhandeler.UserInputException;
import com.ecart.delivery.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	private Category category;

	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class.getName());

	private static final String ADDED_NEW_CATEGORY = "New Category Added Succesfully!";

	private static final String UPDATED_EXISTING_CATEGORY = "Category Updated Succesfully!";

	private static final String NEW_CATEGORY_LISTS_ADDED = "New Lists of Categories added Sucesfully!";

	@Override
	public String addNewCategory(CategoryDTO categoryDTO) {
		if (categoryDTO.getCategoryName().isEmpty() || categoryDTO.getCategoryName().isBlank()) {
			throw new UserInputException();
		}
		category = new Category();
		category.setId(categoryDTO.getId());
		category.setCategoryName(categoryDTO.getCategoryName());
		categoryDao.save(category);
		logger.info("addNewCategory() called");
		return ADDED_NEW_CATEGORY;
	}

	// required to use saveorupate method of jpa
	@Override
	public String addOrUpdateCategory(CategoryDTO categoryDTO) {
		if (categoryDTO.getCategoryName().isEmpty() || categoryDTO.getCategoryName().isBlank()) {
			throw new UserInputException();
		}
		Optional<Category> existingCategory = categoryDao.findById(categoryDTO.getId());
		if (existingCategory.isPresent()) {
			category = new Category();
			category.setId(categoryDTO.getId());
			category.setCategoryName(categoryDTO.getCategoryName());
			categoryDao.save(category);
			return UPDATED_EXISTING_CATEGORY;
		} else {
			category = new Category();
			category.setId(categoryDTO.getId());
			category.setCategoryName(categoryDTO.getCategoryName());
			categoryDao.save(category);
			return ADDED_NEW_CATEGORY;
		}
	}

	@Override
	public String addNewListsofCategories(List<Category> catLists) {
		for (Category eachCatDTOLists : catLists) {
			if (eachCatDTOLists.getCategoryName().isEmpty() || eachCatDTOLists.getCategoryName().isBlank()) {
				throw new UserInputException();
			}
		}
		categoryDao.saveAll(catLists);
		logger.info("addNewListsofCategories() called");
		return NEW_CATEGORY_LISTS_ADDED;
	}

	@Override
	public List<Category> addOrUpdateNewListsofCategories(List<Category> catLists) {
		List<Category> addOrUpdateCatsLists = new ArrayList<Category>();
		for (Category eachCatLists : catLists) {
			if (eachCatLists.getCategoryName().isEmpty() || eachCatLists.getCategoryName().isBlank()) {
				throw new UserInputException();
			}
		}

		for (Category eachCatLists : catLists) {
			Optional<Category> existingCategory = categoryDao.findById(eachCatLists.getId());

			if (!existingCategory.isPresent()) {
				addOrUpdateCatsLists.add(eachCatLists);
			} else {
				category = new Category();
				category.setId(eachCatLists.getId());
				category.setCategoryName(eachCatLists.getCategoryName());
				categoryDao.save(category);
				addOrUpdateCatsLists.add(category);
			}
			categoryDao.saveAll(addOrUpdateCatsLists);
		}
		logger.info("addOrUpdateNewListsofCategories() called");
		return addOrUpdateCatsLists;
	}

	@Override
	public List<Category> fetchAll() {
		List<Category> existingListsOfCategory = categoryDao.findAll();
		if (existingListsOfCategory.isEmpty()) {
			throw new ElementNotFoundException();
		}
		logger.info("fetchAll() called");
		return existingListsOfCategory;
	}

	@Override
	public Optional<Category> fetchByCategoryName(String categoryName) {
		Optional<Category> existingCategory = categoryDao.findByCategoryName(categoryName);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFoundException();
		}
		logger.info("fetchByCategoryName() called");
		return existingCategory;
	}

	@Override
	public Optional<Category> fetchById(int id) {
		Optional<Category> existingCategory = categoryDao.findById(id);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFoundException();
		}
		logger.info("fetchById() called");
		return existingCategory;
	}

	@Override
	public String deleteById(int id) {
		Optional<Category> existingCategory = categoryDao.findById(id);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFoundException();
		}
		categoryDao.deleteById(id);
		logger.info("deleteById() called");
		return "delete existing Category Succesfully : " + id;
	}

}
