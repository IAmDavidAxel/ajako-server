package com.example.ajakoserver.application.service.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import com.example.ajakoserver.domain.product.Product;
import com.example.ajakoserver.domain.product.ProductFactory;
import com.example.ajakoserver.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;


public class ProductServiceTest {

	private ProductService productService;

	@Mock
	private ProductFactory productFactory;
	@Mock
	private ProductRepository productRepository;
	private ProductDto productDto;
	private Product product;

	@BeforeEach
	public void setUp()throws Exception{

		productService = new ProductService(productFactory,productRepository);

		willReturn(product).given(productFactory).create(productDto);
	}

	@Test
	public void whenCreatingANewProduct_thenDelegateToFactory()throws Exception{

		productService.create(productDto);

		verify(productFactory).create(productDto);

	}

}
