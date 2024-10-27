package com.sqlInjection.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class ProductService {

	@Autowired
	private productRepository productRepo;

	@Autowired
	private EntityManager entityManager;

	// Using JPA Repository with Named Queries
	public List<Map<String, Object>> findByQuantity(double quantity) {
		return productRepo.findProductsByQuantity(quantity);
	}

	// Using Native Queries
	public List<Map<String, Object>> findByQuantity1(double quantity) {
		return productRepo.findProductsByQuantity1(quantity);
	}

	// Using projection Dto
	public List<ProductDto> findByQuantity2() {
		return productRepo.findProductsByQuantity();
	}

//	// using criteria query
	public List<ProductDto> findAllProductsWithAlias() {
		List<ProductDto> productDTOs = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductDto> query = cb.createQuery(ProductDto.class);
		Root<Product> product = query.from(Product.class);

		query.select(cb.construct(ProductDto.class, product.get("name"), product.get("quantity"), product.get("price"),
				product.get("description"), product.get("manufacturer")));
		productDTOs = entityManager.createQuery(query).getResultList();
		return productDTOs;
	}

}
