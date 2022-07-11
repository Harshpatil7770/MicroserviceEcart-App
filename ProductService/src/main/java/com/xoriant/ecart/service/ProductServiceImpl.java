package com.xoriant.ecart.service;

import java.util.ArrayList;
import java.util.List;
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

	private Product product;

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

	@Override
	public List<Product> saveOrUpdateListOfProduct(List<ProductDTO> prodLists, int brandId, int id) {
		List<Product> saveOrupdateProductsList = new ArrayList<Product>();
		for (ProductDTO eachNewProduct : prodLists) {
			if (eachNewProduct.getProductName().isEmpty() || eachNewProduct.getProductName().isBlank()) {
				throw new UserInputException();
			}
		}
		for (ProductDTO eachNewProduct : prodLists) {
			Optional<Product> existingProduct = productRepo.findById(eachNewProduct.getProductId());
			if (existingProduct.isPresent()) {
				Product updateProduct = productRepo.findById(eachNewProduct.getProductId()).orElse(null);
				updateProduct.setProductName(eachNewProduct.getProductName());
				updateProduct.setProductName(eachNewProduct.getProductName());
				updateProduct.setQuantity(eachNewProduct.getQuantity());
				updateProduct.setPrice(eachNewProduct.getPrice());

				Optional<Brand> brandResult = fetchByBrandId(brandId);
				updateProduct.setBrandName(brandResult.get().getBrandName());

				Optional<Category> categoryResult = fetchById(id);
				updateProduct.setCategoryName(categoryResult.get().getCategoryName());

				productRepo.save(updateProduct);
				saveOrupdateProductsList.add(updateProduct);

			} else {
				product = new Product();
				product.setProductName(eachNewProduct.getProductName());
				product.setQuantity(eachNewProduct.getQuantity());
				product.setPrice(eachNewProduct.getPrice());

				Optional<Brand> brandResult = fetchByBrandId(brandId);
				product.setBrandName(brandResult.get().getBrandName());

				Optional<Category> categoryResult = fetchById(id);
				product.setCategoryName(categoryResult.get().getCategoryName());

				productRepo.save(product);
				saveOrupdateProductsList.add(product);
			}

		}

		return productRepo.saveAll(saveOrupdateProductsList);
	}

	@Override
	public Optional<Product> findByProductName(String productName) {
		Optional<Product> existingProduct = productRepo.findByProductName(productName);
		if (!existingProduct.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingProduct;
	}

	@Override
	public Optional<Product> findById(int productId) {
		Optional<Product> existingProduct = productRepo.findById(productId);
		if (!existingProduct.isPresent()) {
			throw new ElementNotFoundException();
		}
		return existingProduct;
	}

	@Override
	public List<Product> findByBrandName(String brandName) {
		List<Product> existingProductLists = productRepo.findByBrandName(brandName);
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public List<Product> findByCategoryName(String categoryName) {
		List<Product> existingProductLists = productRepo.findByCategoryName(categoryName);
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public List<Product> findAll() {
		List<Product> existingProductLists = productRepo.findAll();
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public List<Product> findByPriceGreaterThan(double minPrice) {
		List<Product> existingProductLists = productRepo.findByPriceGreaterThan(minPrice);
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public List<Product> findByPriceLessThan(double maxPrice) {
		List<Product> existingProductLists = productRepo.findByPriceLessThan(maxPrice);
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public List<Product> findByPriceInBetween(double minPrice, double maxPrice) {
		List<Product> existingProductLists = productRepo.findByPriceIsBetween(minPrice, maxPrice);
		if (existingProductLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return existingProductLists;
	}

	@Override
	public void deleteById(int productId) {
		Optional<Product> existingProduct = productRepo.findById(productId);
		if (!existingProduct.isPresent()) {
			throw new ElementNotFoundException();
		}

		productRepo.deleteById(productId);
	}

}
