package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Brand;
import com.example.demo.dto.Category;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.msgsender.MessageSender;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSender messageSender;

	private static final String ADDED_NEW_PRODUCT = "New Product Details Added Succesfully !";

	private static final String UPDATE_PRODUCT = "Update existing Product succesfully !";

	private static final String SAVE_OR_UPDATE_PRODUCTS_LIST = "Save Or Update Lists Of Product Succesfully !";

	private static final String DELETED_PRODUCT = "deleted product succesfully !";

	@PostMapping("/save")
	public String addNewProduct(@RequestBody ProductDTO productDTO, @RequestParam int brandId, @RequestParam int id) {

		Product result = productService.addNewProduct(productDTO, brandId, id);
		if (result != null) {
			messageSender
					.addNewProduct(ADDED_NEW_PRODUCT + productDTO.getProductId() + " : " + productDTO.getProductName());
		}
		return ADDED_NEW_PRODUCT;
	}

	@GetMapping("find")
	public Optional<Brand> fetchByBrandId(@RequestParam int brandId) {
		return productService.fetchByBrandId(brandId);
	}

	@GetMapping("/find-category")
	public Optional<Category> fetchById(@RequestParam int id) {
		return productService.fetchById(id);
	}

	@PutMapping("/saveOrupdate/{brandId}/{id}")
	public String saveOrUpdateTheProduct(@RequestBody ProductDTO productDTO, @PathVariable int brandId,
			@PathVariable int id) {
		String result = productService.saveOrUpdateTheProduct(productDTO, brandId, id);
		if (result.equals(ADDED_NEW_PRODUCT)) {
			messageSender
					.addNewProduct(ADDED_NEW_PRODUCT + productDTO.getProductId() + " : " + productDTO.getProductName());
		}
		if (result.equals(UPDATE_PRODUCT)) {
			messageSender.updateExisingProduct(
					UPDATE_PRODUCT + productDTO.getProductId() + " : " + productDTO.getProductName());
		}
		return result;
	}

	@PutMapping("/saveOrUpdate/productLists/{brandId}/{id}")
	public List<Product> saveOrUpdateListsOfProducts(@RequestBody List<ProductDTO> productLists,
			@PathVariable int brandId, @PathVariable int id) {
		List<Product> result = productService.saveOrUpdateListsOfProducts(productLists, brandId, id);
		if (result != null) {
			messageSender.saveOrUpdateListsOfProducts(SAVE_OR_UPDATE_PRODUCTS_LIST);
		}
		return result;
	}

	@GetMapping("/findAll")
	public List<Product> fetchAll() {
		return productService.fetchAll();
	}

	@GetMapping("/findAll/{categoryName}")
	public List<Product> fetchProductBasedOnCategoryName(@PathVariable String categoryName) {
		return productService.fetchProductBasedOnCategoryName(categoryName);
	}

	@GetMapping("/findAll/products/{brandName}")
	public List<Product> fetchProductBasedOnBrandName(@PathVariable String brandName) {
		return productService.fetchProductBasedOnBrandName(brandName);
	}

	@GetMapping("/find/{productName}")
	public Optional<Product> findByProductName(@PathVariable String productName) {
		return productService.findByProductName(productName);
	}

	@GetMapping("/fetchBy")
	public Optional<Product> findById(@RequestParam int productId) {
		return productService.findById(productId);
	}

	@DeleteMapping("/delete")
	public String deleteProduct(@RequestParam int productId) {
		productService.deleteProduct(productId);
		return DELETED_PRODUCT;
	}
}
