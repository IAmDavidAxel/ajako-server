package com.example.ajakoserver.api.resource.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import com.example.ajakoserver.application.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ProductJsonResourceTest {

	private ProductJsonResource productJsonResource;

	@Mock
	private ProductService productService;
	private ProductDto productDto;


	@BeforeEach
	public void setUp()throws Exception{
		productJsonResource = new ProductJsonResource(productService);
	}

	@Test
	public void whenCreatingAProduct_thenDelegateToService()throws Exception{

		productJsonResource.create(productDto);

		verify(productService).create(productDto);

	}

}
