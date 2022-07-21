package com.example.ajakoserver.domain.product;

import com.example.ajakoserver.api.dto.product.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFactory {

	public Product create(ProductDto productDto) {

		String productName = productDto.getName();
		String productType = productDto.getType();
		String productDescription = productDto.getDescription();
		float productPrice = productDto.getPrice();

		return new Product(productName,productPrice,productType,productDescription);
	}

	public ProductDto create(Product product){
		ProductDto productDto = new ProductDto();

		productDto.setDescription(productDto.getDescription());
		productDto.setName(productDto.getName());
		productDto.setPrice(productDto.getPrice());
		productDto.setType(productDto.getType());

		return productDto;
	}

	public List<ProductDto> createListDto (List<Product> products){

		return products.stream().map(this::create).collect(Collectors.toList());
	}

	public List<Product> createListProduct(List<ProductDto> productDtos){

		return productDtos.stream().map(this::create).collect(Collectors.toList());
	}
}
