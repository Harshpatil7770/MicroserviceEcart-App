package com.ecart.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecart.delivery.dao.BrandDao;
import com.ecart.delivery.dto.BrandDTO;
import com.ecart.delivery.dto.Category;
import com.ecart.delivery.dto.CategoryDTO;
import com.ecart.delivery.exceptionhandeler.ElementNotFoundException;
import com.ecart.delivery.exceptionhandeler.UserInputException;
import com.ecart.delivery.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	private Brand brand;

	@Override
	public String addNewBrand(BrandDTO brandDTO) {
		if (brandDTO.getBrandName().isEmpty() || brandDTO.getBrandName().isBlank()) {
			throw new UserInputException();
		}
		// out of scope of bean
		brand = new Brand();
		brand.setId(brandDTO.getId());
		brand.setBrandName(brandDTO.getBrandName());
		brand.setCategoryId(brandDTO.getCategoryDTO().getId());
		String result = "New Brand Added !";
		brandDao.save(brand);
		return result;
	}

	@Override
	public String addNewCategory(CategoryDTO categoryDTO) {

		return categoryServiceProxy.addNewCategory(categoryDTO);
	}

	@Override
	public Optional<Category> fetchById(int id) {
		Optional<Category> result = categoryServiceProxy.fetchById(id);
		if (!result.isPresent()) {
			throw new ElementNotFoundException();
		}
		return result;
	}

	@Override
	public List<Brand> fetchAllBrandsWithTheirCategories() {
		List<Brand> existingBrands = brandDao.findAllBrandsWithCategories();
		if (existingBrands.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingBrands;
	}

	@Override
	public List<BrandDTO> fetchAll() {
		List<BrandDTO> existingBrands = brandDao.findAllBrandWithCategories();
		if (existingBrands.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingBrands;
	}

}
