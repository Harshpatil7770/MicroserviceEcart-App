package com.ecart.delivery.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.delivery.dto.Category;
import com.ecart.delivery.model.Brand;
import com.ecart.delivery.service.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandResource {

	@Autowired
	private BrandService brandService;

	// Not working
	@GetMapping("/findAll")
	public List<Category> fetchAll() {
		return brandService.fetchAll();
	}

	@GetMapping("/find")
	public Optional<Category> fetchById(@RequestParam int categoryId) {
		return brandService.fetchById(categoryId);
	}

	@PostMapping("/save/{categoryId}")
	public String addNewBrand(@RequestBody Brand brand, @PathVariable int categoryId) {
		return brandService.addNewBrand(brand, categoryId);
	}

	@PostMapping("/saveOrupdate/{categoryId}")
	public String addOrUpdateBrand(@RequestBody Brand brand, @PathVariable int categoryId) {
		return brandService.addOrUpdateBrand(brand, categoryId);
	}

	// Not working
	@PostMapping("/saveAll/{categoryId}")
	public String addNewListsOfBrandsWithSameCategoryName(@RequestBody List<Brand> brandLists,
			@PathVariable int categoryId) {

		return brandService.addNewListsOfBrandsWithSameCategoryName(brandLists, categoryId);
	}

	// Not working
	@PostMapping("/saveAllorUpdate/{categoryId}")
	public String addOrUpdateListsOfBrandsWithSameCategoryName(@RequestBody List<Brand> brandLists,
			@PathVariable int categoryId) {
		return brandService.addOrUpdateListsOfBrandsWithSameCategoryName(brandLists, categoryId);
	}

	@DeleteMapping("/delete")
	String deleteExistingBrand(@RequestParam int brandId) {
		return brandService.deleteExistingBrand(brandId);
	}

	@GetMapping("/fetchAll")
	List<Brand> fetchAllBrandsWithCategories() {
		return brandService.fetchAllBrandsWithCategories();
	}

	@GetMapping("/findAll-brands")
	List<Brand> fetchBrandByCategoryName(@RequestParam String categoryName) {
		return brandService.fetchBrandByCategoryName(categoryName);
	}

	@GetMapping("/findBy/{brandName}")
	Optional<Brand> fetchByBrandName(@PathVariable String brandName) {
		return brandService.fetchByBrandName(brandName);
	}

	@GetMapping("/fetch-brand")
	Optional<Brand> fetchByBrandId(@RequestParam int brandId) {
		return brandService.fetchByBrandId(brandId);
	}

}
