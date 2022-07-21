package com.example.ajakoserver.application.service.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import com.example.ajakoserver.domain.product.Product;
import com.example.ajakoserver.domain.product.ProductFactory;
import com.example.ajakoserver.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


	private final ProductFactory productFactory;
	private final ProductRepository productRepository;

	public ProductService(ProductFactory productFactory, ProductRepository productRepository) {

		this.productFactory = productFactory;
		this.productRepository = productRepository;
	}

	public void create(ProductDto productDto) {
		Product product = createFromFactory(productDto);
		save(product);
	}

	private Product createFromFactory(ProductDto productDto) {
		return productFactory.create(productDto);
	}

	private void save(Product product) {
		productRepository.save(product);
	}
}
