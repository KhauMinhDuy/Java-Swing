package com.project1.model;

import java.util.List;

public class Bill {

	private String companyName;
	private String website;
	private String storeAddress;
	private String outputVoucherID;
	private String outputDATE;
	private String outputUSER;
	private Integer totalAmount;
	private Integer totalDiscount;
	private Integer totalGiftVoucherAmount;
	private Integer moneyCard;
	private Integer totalAmountRound;
	private String barcode;
	private String qrCode;
	private String specialMessage;

	private List<Product> products;

	public Bill() {
		this.companyName = "BÁCH HÓA XANH";
		this.website = "www.bachhoaxanh.com";
	}

	public Bill(String storeAddress, String outputVoucherID, String outputDATE, String outputUSER) {
		this();
		this.storeAddress = storeAddress;
		this.outputVoucherID = outputVoucherID;
		this.outputDATE = outputDATE;
		this.outputUSER = outputUSER;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getOutputVoucherID() {
		return outputVoucherID;
	}

	public void setOutputVoucherID(String outputVoucherID) {
		this.outputVoucherID = outputVoucherID;
	}

	public String getOutputDATE() {
		return outputDATE;
	}

	public void setOutputDATE(String outputDATE) {
		this.outputDATE = outputDATE;
	}

	public String getOutputUSER() {
		return outputUSER;
	}

	public void setOutputUSER(String outputUSER) {
		this.outputUSER = outputUSER;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Integer totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Integer getTotalGiftVoucherAmount() {
		return totalGiftVoucherAmount;
	}

	public void setTotalGiftVoucherAmount(Integer totalGiftVoucherAmount) {
		this.totalGiftVoucherAmount = totalGiftVoucherAmount;
	}

	public Integer getMoneyCard() {
		return moneyCard;
	}

	public void setMoneyCard(Integer moneyCard) {
		this.moneyCard = moneyCard;
	}

	public Integer getTotalAmountRound() {
		return totalAmountRound;
	}

	public void setTotalAmountRound(Integer totalAmountRound) {
		this.totalAmountRound = totalAmountRound;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getSpecialMessage() {
		return specialMessage;
	}

	public void setSpecialMessage(String specialMessage) {
		this.specialMessage = specialMessage;
	}

}
