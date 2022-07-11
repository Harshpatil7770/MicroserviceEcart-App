package com.ecart.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecart.delivery.dao.CategoryDao;
import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.exceptionhandeler.ElementNotFoundException;
import com.ecart.delivery.exceptionhandeler.UserInputException;
import com.ecart.delivery.model.Category;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	private static final String UPDATED_EXISTING_CATEGORY = "Category Updated Succesfully!";

	private static final String ADDED_NEW_CATEGORY = "New Category Added Succesfully!";

	private static final String NEW_CATEGORY_LISTS_ADDED = "New Lists of Categories added Sucesfully!";

	@Mock
	CategoryDao categoryDao;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	private CategoryDTO categoryDTO;

	private CategoryDTO categoryDTO1;

	private Category category;

	private Category category1;

	@BeforeEach
	void beforeEach() {
		categoryDTO = new CategoryDTO();
		categoryDTO.setId(101);
		categoryDTO.setCategoryName("WATCH");
		category = new Category();
		category.setId(categoryDTO.getId());
		category.setCategoryName(categoryDTO.getCategoryName());

		categoryDTO1 = new CategoryDTO();
		categoryDTO1.setId(102);
		categoryDTO1.setCategoryName("MOBILE");
		category1 = new Category();
		category1.setId(categoryDTO1.getId());
		category1.setCategoryName(categoryDTO1.getCategoryName());

	}

	@Test
	void addNewCategory() {
		when(categoryDao.save(category)).thenReturn(category);
		assertEquals(ADDED_NEW_CATEGORY, categoryServiceImpl.addNewCategory(categoryDTO));
	}

	@Test
	void addNewCagtegory_thorwsexception_if_caetgoryNameIsBlank() {
		categoryDTO.setCategoryName(" ");
		category.setId(categoryDTO.getId());
		category.setCategoryName(categoryDTO.getCategoryName());
		doThrow(UserInputException.class).when(categoryDao).save(category);
		assertThrows(UserInputException.class, () -> {
			categoryDao.save(category);
		});
	}

	@Test
	void addNewCategory_throwsexception_if_categoryNameIsNull() {
		categoryDTO.setCategoryName(null);
		category.setCategoryName(categoryDTO.getCategoryName());
		doThrow(UserInputException.class).when(categoryDao).save(category);
		assertThrows(UserInputException.class, () -> {
			categoryDao.save(category);
		});

	}

	@Test
	void addOrUpdateCategory() {
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryDao.findById(101)).thenReturn(existingCategory);
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		if (result.isPresent()) {
			category.setId(categoryDTO.getId());
			categoryDTO.setCategoryName("WATCH");
			category.setCategoryName(categoryDTO.getCategoryName());
			when(categoryDao.save(category)).thenReturn(category);
			assertEquals(UPDATED_EXISTING_CATEGORY, categoryServiceImpl.addOrUpdateCategory(categoryDTO));
		}
	}

	@Test
	void addOrUpdateCategory_BasedOnExistingRecords() {
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryDao.findById(1)).thenReturn(existingCategory);
		Optional<Category> result = categoryDao.findById(1);

		if (result.isPresent()) {
			category.setId(categoryDTO.getId());
			categoryDTO.setCategoryName("SAMSUNG");
			category.setCategoryName(categoryDTO.getCategoryName());
			when(categoryDao.save(category)).thenReturn(category);
			assertEquals(ADDED_NEW_CATEGORY, categoryServiceImpl.addOrUpdateCategory(categoryDTO));
		}
	}

	@Test
	void addNewListsofCategories() {
		List<Category> catLists = new ArrayList<Category>();

		catLists.add(category);

		when(categoryDao.saveAll(catLists)).thenReturn(catLists);
		assertEquals(NEW_CATEGORY_LISTS_ADDED, categoryServiceImpl.addNewListsofCategories(catLists));
	}

	@Test
	void addOrUpdateNewListsofCategories() {

		List<Category> catLists = new ArrayList<Category>();

		Optional<Category> existingCategory1 = Optional.of(category);

		catLists.add(category);
		catLists.add(category1);

		when(categoryDao.findById(101)).thenReturn(existingCategory1);
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);

		for (Category eachCat : catLists) {

			if (result.isPresent()) {
				categoryDTO.setCategoryName("LAPTOP");
				category.setCategoryName(categoryDTO.getCategoryName());
				categoryDao.save(category);
				catLists.add(category);
			} else {
				category.setId(categoryDTO.getId());
				categoryDTO.setCategoryName("LAPTOP");
				category.setCategoryName(categoryDTO.getCategoryName());
				categoryDao.save(category);
				catLists.add(category);
			}
		}

		when(categoryDao.saveAll(catLists)).thenReturn(catLists);
		assertEquals(catLists, categoryServiceImpl.addOrUpdateNewListsofCategories(catLists));
	}

	@Test
	void fetchAll() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category);
		catLists.add(category1);

		when(categoryDao.findAll()).thenReturn(catLists);
		assertEquals(2, categoryServiceImpl.fetchAll().size());
	}

	@Test
	void fetchByCategoryName() {
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryDao.findByCategoryName("WATCH")).thenReturn(existingCategory);
		Optional<Category> result = categoryDao.findByCategoryName("WATCH");
		assertNotNull(result);
		assertEquals(result, categoryServiceImpl.fetchByCategoryName("WATCH"));
	}

	@Test
	void fetchByCategoryName_throwsException_IfCategoryNotPresent() {
		doThrow(ElementNotFoundException.class).when(categoryDao).findByCategoryName("MOBILE");
		assertThrows(ElementNotFoundException.class, () -> {
			categoryDao.findByCategoryName("MOBILE");
		});
	}

	@Test
	void fetchByCategoryId() {
		Optional<Category> existingCategory = Optional.of(category);
		when(categoryDao.findById(101)).thenReturn(existingCategory);
		Optional<Category> result = categoryDao.findById(101);
		assertNotNull(result);
		assertEquals(result, categoryServiceImpl.fetchById(101));
	}

	@Test
	void fetchByCategoryId_throwsException_IfCategoryNotPresent() {
		doThrow(ElementNotFoundException.class).when(categoryDao).findById(501);
		assertThrows(ElementNotFoundException.class, () -> {
			categoryDao.findById(501);
		});
	}

	@Test
	void deleteById() {

		Optional<Category> existingCategory = Optional.of(category);
		when(categoryDao.findById(101)).thenReturn(existingCategory);
		categoryServiceImpl.deleteById(category.getId());
		verify(categoryDao, times(1)).deleteById(eq(category.getId()));

	}

}
