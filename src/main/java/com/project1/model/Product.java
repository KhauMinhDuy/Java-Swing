package com.project1.model;

public class Product {

	private String productName;
	private Integer quantity;
	private Integer salePriceVAT;
	private Integer totalAmountVAT;

	public Product() {
	}

	public Product(String productName, Integer quantity, Integer salePriceVAT) {
		this.productName = productName;
		this.quantity = quantity;
		this.salePriceVAT = salePriceVAT;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSalePriceVAT() {
		return salePriceVAT;
	}

	public void setSalePriceVAT(Integer salePriceVAT) {
		this.salePriceVAT = salePriceVAT;
	}

	public Integer getTotalAmountVAT() {
		return quantity * salePriceVAT;
	}

}
