package com.ecart.delivery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecart.delivery.dto.BrandDTO;
import com.ecart.delivery.model.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand,Integer> {

	@Query(value="select * from brands b inner join categories c on b.category_id=c.id",nativeQuery=true)
	List<BrandDTO> findAllBrandWithCategories();

	@Query(value="select * from brands b inner join categories c on b.category_id=c.id",nativeQuery=true)
	List<Brand> findAllBrandsWithCategories();
}
