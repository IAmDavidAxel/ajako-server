package com.example.ajakoserver.domain.product;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Column(name = "product_name")
	private  String productName;
	@Column(name = "produc_price")
	private  float productPrice;
	@Column(name = "product_type")
	private  String productType;
	@Column(name = "product_description")
	private  String productDescription;

	public Product(){

	}

	public Product(String productName, float productPrice, String productType, String productDescription) {

		this.productName = productName;
		this.productPrice = productPrice;
		this.productType = productType;
		this.productDescription = productDescription;
	}


	public long getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public String getProductType() {
		return productType;
	}

	public String getProductDescription() {
		return productDescription;
	}






}
