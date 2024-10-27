package com.sqlInjection.product;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface productRepository extends JpaRepository<Product, String> {

	@Query("SELECT p.name AS productName, p.price AS productPrice, p.description as productDescription FROM Product p WHERE p.quantity > :quantity")
	List<Map<String, Object>> findProductsByQuantity(@Param("quantity") double quantity);

	@Query(value = "SELECT p.name AS product_name, p.price AS product_price FROM product p WHERE p.quantity > :quantity", nativeQuery = true)
	List<Map<String, Object>> findProductsByQuantity1(@Param("quantity") double quantity);

	@Query("SELECT new com.sqlInjection.product.ProductDto(p.name, p.quantity,p.price,p.description,p.manufacturer) FROM Product p")
	List<ProductDto> findProductsByQuantity();

}
