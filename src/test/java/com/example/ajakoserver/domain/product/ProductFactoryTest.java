package com.example.ajakoserver.domain.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

	private static final String PRODUCT_NAME = "rouge a levre";
	private static final float PRODUCT_PRICE = 50f;
	private static final String PRODUCT_TYPE = "make-up";
	private static final String PRODUCT_DESCRIPTION = "sqdjkjqslkd";
	private ProductFactory productFactory;

	private Product product;
	private ProductDto productDto;

	private void setUpDto(){
		productDto = new ProductDto();

		productDto.setName(PRODUCT_NAME);
		productDto.setPrice(PRODUCT_PRICE);
		productDto.setType(PRODUCT_TYPE);
		productDto.setDescription(PRODUCT_DESCRIPTION);
	}

	private void setUpProduct(){
		product = new Product(PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_TYPE,PRODUCT_DESCRIPTION);
	}

	@BeforeEach
	public void setUp()throws Exception{
		setUpProduct();
		setUpDto();
		productFactory = new ProductFactory();
	}

	@Test
	public void whenCreatingANewProduct_thenAllAttributesAreEquals(){

		Product productFromFactory =  productFactory.create(productDto);

		assertEquals(PRODUCT_NAME, product.getProductName());
		assertEquals(PRODUCT_DESCRIPTION, product.getProductDescription());
		assertEquals(PRODUCT_PRICE, product.getProductPrice());
		assertEquals(PRODUCT_TYPE,product.getProductType());

	}

	@Test
	public void whenCreatingANewDto_thenAllAttributesAreEquals(){

		ProductDto dtoFromFactory = productFactory.create(product);

		assertEquals(PRODUCT_NAME,dtoFromFactory.getName());
		assertEquals(PRODUCT_DESCRIPTION, dtoFromFactory.getDescription());
		assertEquals(PRODUCT_TYPE, dtoFromFactory.getType());
		assertEquals(PRODUCT_PRICE,dtoFromFactory.getPrice());
	}




}
