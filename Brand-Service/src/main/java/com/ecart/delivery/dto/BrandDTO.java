package com.ecart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

	private int id;
	
	private String brandName;
	
	private CategoryDTO categoryDTO;
}
