package com.xoriant.ecart.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.ecart.dto.Brand;
import com.xoriant.ecart.dto.Category;
import com.xoriant.ecart.dto.ProductDTO;
import com.xoriant.ecart.model.Product;
import com.xoriant.ecart.msgsender.MessageSender;
import com.xoriant.ecart.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductResource {

	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSender messageSender;

	private static final String ADD_OR_UPDATE_PRODUCT_LISTS = "Add Or Update New Product Succesfully !";

	@GetMapping("/find")
	public Optional<Brand> fetchByBrandId(@RequestParam int brandId) {
		return productService.fetchByBrandId(brandId);
	}

	@GetMapping("/find/category")
	public Optional<Category> fetchById(@RequestParam int id) {
		return productService.fetchById(id);
	}

	@PostMapping("/save/{brandId}/{id}")
	public String saveOrUpdateProduct(@RequestBody ProductDTO productDTO, @PathVariable int brandId,
			@PathVariable int id) {
		String result = productService.saveOrUpdateProduct(productDTO, brandId, id);
		if (result != null) {
			messageSender.saveOrUpdateProduct(result);
		}
		return result;
	}

	@PostMapping("/saveAll/{brandId}/{id}")
	public List<Product> saveOrUpdateListOfProduct(@RequestBody List<ProductDTO> prodLists, @PathVariable int brandId,
			@PathVariable int id) {
		List<Product> result = productService.saveOrUpdateListOfProduct(prodLists, brandId, id);
		if (result != null) {
			messageSender.saveOrUpdateListOfProduct(ADD_OR_UPDATE_PRODUCT_LISTS);
		}
		return result;
	}

	@GetMapping("/find-product")
	public Optional<Product> findByProductName(@RequestParam String productName) {
		return productService.findByProductName(productName);
	}

	@GetMapping("/find/{productId}")
	Optional<Product> findById(@PathVariable int productId) {
		return productService.findById(productId);
	}

	@GetMapping("/find/productLists")
	List<Product> findByBrandName(@RequestParam String brandName) {
		return productService.findByBrandName(brandName);
	}

	@GetMapping("/find/productLists/{categoryName}")
	List<Product> findByCategoryName(@PathVariable String categoryName) {
		return productService.findByCategoryName(categoryName);
	}

	@GetMapping("/findAll")
	List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/findAll/{minPrice}")
	List<Product> findByPriceGreaterThan(@PathVariable double minPrice) {
		return productService.findByPriceGreaterThan(minPrice);
	}

	@GetMapping("/findAll/productLists/{maxPrice}")
	List<Product> findByPriceLessThan(@PathVariable double maxPrice) {
		return productService.findByPriceLessThan(maxPrice);
	}

	@GetMapping("/findAll/{minPrice}/{maxPrice}")
	List<Product> findByPriceInBetween(@PathVariable double minPrice, @PathVariable double maxPrice) {
		return productService.findByPriceInBetween(minPrice, maxPrice);
	}

	@DeleteMapping("delete/{productId}")
	public void deleteById(@PathVariable int productId) {
		productService.deleteById(productId);
	}
}