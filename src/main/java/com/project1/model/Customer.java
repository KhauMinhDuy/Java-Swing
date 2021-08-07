package com.project1.model;

public class Customer {

	private String customerName;
	private String customerPhone;

	public Customer() {
		customerName = "";
		customerPhone = "";
	}

	public Customer(String customerName, String customerPhone) {
		this.customerName = customerName;
		this.customerPhone = customerPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

}
