package com.project1.model;

public class Product {

	private String productName;
	private int quantity;
	private int salePriceVAT;
	private int salePriceAfterDiscount;
	private int totalAmountVAT;

	public Product() {
	}

	public Product(String productName, int quantity, int salePriceVAT, int salePriceAfterDiscount) {
		this.productName = productName;
		this.quantity = quantity;
		this.salePriceVAT = salePriceVAT;
		this.salePriceAfterDiscount = salePriceAfterDiscount;
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
		return quantity * salePriceAfterDiscount;
	}

	public int getSalePriceAfterDiscount() {
		return salePriceAfterDiscount;
	}

	public void setSalePriceAfterDiscount(int salePriceAfterDiscount) {
		this.salePriceAfterDiscount = salePriceAfterDiscount;
	}

	public void setTotalAmountVAT(int totalAmountVAT) {
		this.totalAmountVAT = totalAmountVAT;
	}

}
