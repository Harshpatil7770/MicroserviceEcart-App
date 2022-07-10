package com.xoriant.ecart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.ProductRepo;
import com.xoriant.ecart.dto.Brand;
import com.xoriant.ecart.dto.Category;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.exceptionhandeler.ElementNotFoundException;
import com.xoriant.ecart.exceptionhandeler.UserInputException;
import com.xoriant.ecart.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	@Autowired
	private BrandServiceProxy brandServiceProxy;

	private Product product = new Product();

	private static final String NEW_PRODUCT_ADDED = "New Product Added Succesfully !";

	private static final String UPDATE_EXISTING_PRODUCT = "Update Existing product Succesfully !";

	@Override
	public Optional<Brand> fetchByBrandId(int brandId) {
		Optional<Brand> existingBrand = brandServiceProxy.fetchByBrandId(brandId);
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingBrand;
	}

	@Override
	public Optional<Category> fetchById(int id) {
		Optional<Category> existingCategory = categoryServiceProxy.fetchById(id);
		if (!existingCategory.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingCategory;
	}

	@Override
	public String saveOrUpdateProduct(ProductDTO productDTO, int brandId, int id) {
		if (productDTO.getProductName().isEmpty() || productDTO.getProductName().isBlank()) {
			throw new UserInputException();
		}

		Optional<Product> existingProduct = productRepo.findById(productDTO.getProductId());
		if (existingProduct.isPresent()) {
			product.setProductName(productDTO.getProductName());
			product.setQuantity(productDTO.getQuantity());
			product.setPrice(productDTO.getPrice());

			Optional<Brand> brandResult = fetchByBrandId(brandId);
			product.setBrandName(brandResult.get().getBrandName());

			Optional<Category> categoryResult = fetchById(id);
			product.setCategoryName(categoryResult.get().getCategoryName());

			productRepo.save(product);

			return UPDATE_EXISTING_PRODUCT;

		} else {
			product.setProductName(productDTO.getProductName());
			product.setQuantity(productDTO.getQuantity());
			product.setPrice(productDTO.getPrice());

			Optional<Brand> brandResult = fetchByBrandId(brandId);
			product.setBrandName(brandResult.get().getBrandName());

			Optional<Category> categoryResult = fetchById(id);
			product.setCategoryName(categoryResult.get().getCategoryName());

			productRepo.save(product);

			return NEW_PRODUCT_ADDED;

		}
	}

}
