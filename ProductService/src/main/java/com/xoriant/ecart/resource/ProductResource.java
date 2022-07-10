package com.xoriant.ecart.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

}