package com.ecart.delivery.service;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
=======
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> refs/remotes/origin/master
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

<<<<<<< HEAD
	private CategoryDTO categoryDTO;

	private Category category;

	@BeforeEach
	void beforeEach() {
		categoryDTO = new CategoryDTO();
		categoryDTO.setId(101);
		categoryDTO.setCategoryName("WATCH");
=======
	private Category category;

	private CategoryDTO categoryDTO;

	@BeforeEach
	void setUp() {
		categoryDTO = new CategoryDTO();
		categoryDTO.setId(101);
		categoryDTO.setCategoryName("SMARTWATCH");
>>>>>>> refs/remotes/origin/master

		category = new Category();
		category.setId(categoryDTO.getId());
		category.setCategoryName(categoryDTO.getCategoryName());
	}

	@Test
<<<<<<< HEAD
	void addNewCategoryDetails() {
=======
	void addNewCategory() {
>>>>>>> refs/remotes/origin/master
		when(categoryDao.save(category)).thenReturn(category);
		assertEquals("New Category Added Succesfully", categoryServiceImpl.addNewCategory(categoryDTO));
	}

	@Test
<<<<<<< HEAD
	void addNewCagtegory_thorwsexception_if_caetgoryNameIsBlank() {
		categoryDTO.setCategoryName(" ");
=======
	void addNewCategory_throwsexception_if_categoryNameIsNull() {
		categoryDTO.setCategoryName(null);
>>>>>>> refs/remotes/origin/master
		category.setCategoryName(categoryDTO.getCategoryName());
		doThrow(UserInputException.class).when(categoryDao).save(category);
		assertThrows(UserInputException.class, () -> {
			categoryDao.save(category);
		});
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
	}
}
