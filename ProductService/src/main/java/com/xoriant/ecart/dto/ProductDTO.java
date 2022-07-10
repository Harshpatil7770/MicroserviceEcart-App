package com.xoriant.ecart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private int productId;

	private String productName;

	private int quantity;

	private double price;

	private String brandName;

	private String categoryName;
}
