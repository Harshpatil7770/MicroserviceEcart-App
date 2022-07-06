package com.ecart.delivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecart.delivery.dao.BrandRepo;
import com.ecart.delivery.dto.Category;
import com.ecart.delivery.exceptionhandeler.ElementNotFoundException;
import com.ecart.delivery.exceptionhandeler.UserInputException;
import com.ecart.delivery.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	@Autowired
	private BrandRepo brandRepo;

	private Brand brand;

	private static final String UPDATED_EXISITNG_BRAND = "Updated Existing Brand Succesfully !";

	private static final String ADDED_NEW_BRAND = "Added New Brand Succesfully !";

	private static final String ADDED_NEW_LIST_BRANDS = "New Lists of Brand Added Succesfully !";

	private static final String DELETED_BRAND = "Deleted Brand Succesfully !";

	@Override
	public String addNewBrand(Brand brand, int categoryId) {
		if (brand.getBrandName().isBlank() || brand.getBrandName().isEmpty()) {
			throw new UserInputException();
		}

		Optional<Category> categoryResult = fetchById(categoryId);
		brand.setCategoryName(categoryResult.get().getCategoryName());
		brandRepo.save(brand);
		return ADDED_NEW_BRAND;
	}

	@Override
	public Optional<Category> fetchById(int categoryId) {
		Optional<Category> existingCategory = categoryServiceProxy.fetchById(categoryId);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingCategory;
	}

	@Override
	public String addOrUpdateBrand(Brand brand, int categoryId) {
		if (brand.getBrandName().isBlank() || brand.getBrandName().isEmpty()) {
			throw new UserInputException();
		}
		Optional<Brand> existingBrand = brandRepo.findById(brand.getBrandId());
		Brand upadateExistingBrand = brandRepo.findById(brand.getBrandId()).orElse(null);
		int temp = 0;
		Optional<Category> existingCategoryResult = fetchById(categoryId);
		if (!existingCategoryResult.isPresent()) {
			throw new ElementNotFoundException();
		}
		if (existingBrand.isPresent()) {
			upadateExistingBrand.setBrandName(brand.getBrandName());
			upadateExistingBrand.setCategoryName(existingCategoryResult.get().getCategoryName());
			brandRepo.save(upadateExistingBrand);
			return UPDATED_EXISITNG_BRAND;
		} else {
			brand.setCategoryName(existingCategoryResult.get().getCategoryName());
			brandRepo.save(brand);
			return ADDED_NEW_BRAND;
		}

	}

	//
	@Override
	public String addNewListsOfBrandsWithSameCategoryName(List<Brand> brandLists, int categoryId) {
		List<Brand> newBrandLists = new ArrayList<Brand>();
		for (Brand eachBrand : brandLists) {
			if (eachBrand.getBrandName().isEmpty() || eachBrand.getBrandName().isBlank()) {
				throw new UserInputException();
			}
		}

		for (Brand eachBrand : brandLists) {
			brand = new Brand();
			Optional<Category> existingCategory = fetchById(categoryId);
			if (existingCategory.isPresent()) {
				brand.setBrandName(eachBrand.getBrandName());
				brand.setCategoryName(existingCategory.get().getCategoryName());
				brandRepo.save(brand);
				newBrandLists.add(brand);

			} else {
				throw new ElementNotFoundException();
			}
		}

		brandRepo.saveAll(newBrandLists);
		return ADDED_NEW_LIST_BRANDS;
	}

	@Override
	public String addOrUpdateListsOfBrandsWithSameCategoryName(List<Brand> brandLists, int categoryId) {
		List<Brand> addOrupadteBrand = new ArrayList<Brand>();
		for (Brand eachNewBrand : brandLists) {
			if (eachNewBrand.getBrandName().isBlank() || eachNewBrand.getBrandName().isBlank()) {
				throw new UserInputException();
			}

			Optional<Category> exitingCategory = fetchById(categoryId);
			if (!exitingCategory.isPresent()) {
				throw new ElementNotFoundException();
			}
		}

		for (Brand eachNewBrand : brandLists) {

			Optional<Brand> existingBrand = brandRepo.findById(eachNewBrand.getBrandId());
			brand = new Brand();
			if (existingBrand.isPresent()) {
				brand.setBrandName(eachNewBrand.getBrandName());
				brand.setCategoryName(existingBrand.get().getCategoryName());
				brandRepo.save(brand);
				addOrupadteBrand.add(brand);
			} else {
				brand.setBrandName(eachNewBrand.getBrandName());
				brand.setCategoryName(eachNewBrand.getCategoryName());
				brandRepo.save(brand);
				addOrupadteBrand.add(brand);
			}
		}
		brandRepo.saveAll(addOrupadteBrand);

		return UPDATED_EXISITNG_BRAND;
	}

	@Override
	public String deleteExistingBrand(int brandId) {
		Optional<Brand> existingBrand = brandRepo.findById(brandId);
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		brandRepo.deleteById(brandId);
		return DELETED_BRAND;
	}

	@Override
	public List<Brand> fetchAllBrandsWithCategories() {
		List<Brand> existingBrands = brandRepo.findAll();
		if (existingBrands.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingBrands;
	}

	@Override
	public List<Brand> fetchBrandByCategoryName(String categoryName) {
		List<Brand> existingBrands = brandRepo.findByCategoryName(categoryName);
		if (existingBrands.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingBrands;
	}

	@Override
	public Optional<Brand> fetchByBrandName(String brandName) {
		Optional<Brand> existingBrands = brandRepo.findByBrandName(brandName);
		if (existingBrands.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingBrands;
	}

	@Override
	public Optional<Brand> fetchByBrandId(int brandId) {
		Optional<Brand> existingBrand = brandRepo.findById(brandId);
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingBrand;
	}

	@Override
	public List<Category> fetchAll() {
		List<Category> existingCategory = categoryServiceProxy.fetchAll();
		if (existingCategory.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingCategory;
	}

}
