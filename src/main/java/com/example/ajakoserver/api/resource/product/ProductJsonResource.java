package com.example.ajakoserver.api.resource.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import com.example.ajakoserver.application.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductJsonResource {


	private ProductService productService;

	public ProductJsonResource(ProductService productService) {

		this.productService = productService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody ProductDto productDto) throws Exception{

		productService.create(productDto);

		return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
	}
}
