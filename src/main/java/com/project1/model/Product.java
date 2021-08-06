package com.project1.model;

public class Product {

	private String productName;
	private int quantity;
	private int salePriceVAT;
	private int totalAmountVAT;

	public Product() {
	}

	public Product(String productName, int quantity, int salePriceVAT) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSalePriceVAT() {
		return salePriceVAT;
	}

	public void setSalePriceVAT(int salePriceVAT) {
		this.salePriceVAT = salePriceVAT;
	}

	public int getTotalAmountVAT() {
		return quantity * salePriceVAT;
	}

}
