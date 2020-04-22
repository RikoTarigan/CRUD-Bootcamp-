package com.grit.template.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grit.template.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query(value = "select product_code, price, product_name, product_type from product where "
			+ "product_code like "
			+ "%:productCode% and (price >= 0.0 or price <= :price) and product_name like "
			+ "%:productName% and  product_type like "
			+ "%:productTyde%", nativeQuery = true)
	
	public List<Object[]> getDataByQueary(@Param("productCode") String productCode,
			@Param("productTyde") String productType, 
			@Param("price") double price,
			@Param("productName") String productName);
	
	//public List<Product> findByProductCodeLike(String str);
}
