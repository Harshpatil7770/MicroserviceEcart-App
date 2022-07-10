package com.xoriant.ecart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.ecart.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
