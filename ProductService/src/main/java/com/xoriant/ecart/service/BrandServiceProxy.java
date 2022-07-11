package com.xoriant.ecart.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xoriant.ecart.dto.Brand;

@FeignClient(name = "http://localhost:9091")
public interface BrandServiceProxy {

	@GetMapping("/api/brands/fetch-brand")
	Optional<Brand> fetchByBrandId(@RequestParam int brandId);
}
