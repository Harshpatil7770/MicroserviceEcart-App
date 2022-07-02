package com.ecart.delivery.resource;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.delivery.dto.BrandDTO;
import com.ecart.delivery.model.Brand;
import com.ecart.delivery.msgsender.MessageSender;
import com.ecart.delivery.service.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandResource {

	private Logger logger = Logger.getLogger(BrandResource.class.getName());

	@Autowired
	private BrandService brandService;

	@Autowired
	private MessageSender messageSender;

	@PostMapping("/save")
	public String addNewBrand(@RequestBody BrandDTO brandDTO) {
		int id = brandDTO.getCategoryDTO().getId();
		brandService.fetchById(id);
		String result = brandService.addNewBrand(brandDTO);
		if (result != null) {
			messageSender.addNewBrand(result);
		}
		logger.info("addNewBrand() called");
		return result;
	}

	@GetMapping("/findAll")
	public List<Brand> fetchAllBrandsWithTheirCategories() {
		logger.info("fetchAllBrandsWithTheirCategories() called");
		return brandService.fetchAllBrandsWithTheirCategories();
	}

	@GetMapping("/findAll-brands")
	public List<BrandDTO> fetchAll() {
		return brandService.fetchAll();
	}
}
