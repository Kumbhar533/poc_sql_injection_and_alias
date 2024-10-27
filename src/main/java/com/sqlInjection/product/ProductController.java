package com.sqlInjection.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/quantity/{quantity}")
	public ResponseEntity<List<Map<String, Object>>> getProductsByQuantity(@PathVariable double quantity) {
		List<Map<String, Object>> products = productService.findByQuantity(quantity);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/native/quantity/{quantity}")
	public ResponseEntity<List<Map<String, Object>>> getProductsByQuantityNative(@PathVariable double quantity) {
		List<Map<String, Object>> products = productService.findByQuantity1(quantity);
		return ResponseEntity.ok(products);
	}

	@GetMapping()
	public ResponseEntity<List<ProductDto>> getProductsByQuantityDto() {
		List<ProductDto> products = productService.findByQuantity2();
		return ResponseEntity.ok(products);
	}

//
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProductsWithAlias() {
		List<ProductDto> products = productService.findAllProductsWithAlias();
		return ResponseEntity.ok(products);
	}
}