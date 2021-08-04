package com.project1.model;

public class Bill {

	private String address;
	private String soCT;
	private String dateTime;
	private String nhanVien;

	private Product product;

	public Bill() {
		super();
	}

	public Bill(String address, String soCT, String dateTime, String nhanVien) {
		super();
		this.address = address;
		this.soCT = soCT;
		this.dateTime = dateTime;
		this.nhanVien = nhanVien;
	}

	public Bill(String address, String soCT, String dateTime, String nhanVien, Product product) {
		super();
		this.address = address;
		this.soCT = soCT;
		this.dateTime = dateTime;
		this.nhanVien = nhanVien;
		this.product = product;
	}

	public String getDiaChi() {
		return address;
	}

	public void setDiaChi(String address) {
		this.address = address;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSoCT() {
		return soCT;
	}

	public void setSoCT(String soCT) {
		this.soCT = soCT;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(String nhanVien) {
		this.nhanVien = nhanVien;
	}

}
