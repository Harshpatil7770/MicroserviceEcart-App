package com.ecart.delivery.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecart.delivery.dao.CategoryDao;
import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.exceptionhandeler.UserInputException;
import com.ecart.delivery.model.Category;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryDao categoryDao;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	private Category category;

	private CategoryDTO categoryDTO;

	@BeforeEach
	void setUp() {
		categoryDTO = new CategoryDTO();
		categoryDTO.setId(101);
		categoryDTO.setCategoryName("SMARTWATCH");

		category = new Category();
		category.setId(categoryDTO.getId());
		category.setCategoryName(categoryDTO.getCategoryName());
	}

	@Test
	void addNewCategory() {
		when(categoryDao.save(category)).thenReturn(category);
		assertEquals("New Category Added Succesfully", categoryServiceImpl.addNewCategory(categoryDTO));
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
}
