package com.ecart.delivery.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.model.Category;
import com.ecart.delivery.msgsender.MessageSender;
import com.ecart.delivery.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

	private static final String SAVE_OR_UPDATE_LISTS_CATEGORIES = "save or update lists of categories succesfully!";

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Autowired
	private MessageSender messageSender;

	@PostMapping("/save")
	public String addNewCategory(@RequestBody CategoryDTO categoryDTO) {
		String result = categoryServiceImpl.addNewCategory(categoryDTO);
		if (result != null) {
			messageSender.sendNewCategoryAddDetails(result);
		}
		return result;
	}

	@PostMapping("/saveOrUpdate")
	public String addOrUpdateCategory(@RequestBody CategoryDTO categoryDTO) {
		String result = categoryServiceImpl.addOrUpdateCategory(categoryDTO);
		if (result != null) {
			messageSender.sendNewaddOrUpdateCategoryDetails(result);
		}
		return result;
	}

	@PostMapping("/saveAll")
	public String addNewListsofCategories(@RequestBody List<Category> catLists) {
		String result = categoryServiceImpl.addNewListsofCategories(catLists);
		if (result != null) {
			messageSender.addNewListsOfCategory(result);
		}
		return result;
	}

	@PostMapping("/saveOrUpdateAll")
	public List<Category> addOrUpdateNewListsofCategories(@RequestBody List<Category> catLists) {
		List<Category> result = categoryServiceImpl.addOrUpdateNewListsofCategories(catLists);
		if (result != null) {
			messageSender.addNewOrUpdateListsOfCategory(SAVE_OR_UPDATE_LISTS_CATEGORIES);
		}
		return result;
	}

	@GetMapping("/findAll")
	public List<Category> fetchAll() {
		return categoryServiceImpl.fetchAll();
	}

	@GetMapping("/find")
	public Optional<Category> fetchByCategoryName(@RequestParam String categoryName) {
		return categoryServiceImpl.fetchByCategoryName(categoryName);
	}

	@GetMapping("/find-category")
	public Optional<Category> fetchById(@RequestParam int id) {
		return categoryServiceImpl.fetchById(id);

	}

	@DeleteMapping("/delete")
	public String deleteById(@RequestParam int id) {
		String result = categoryServiceImpl.deleteById(id);
		if (result != null) {
			messageSender.deleteCategory(result);
		}
		return result;
	}
}
