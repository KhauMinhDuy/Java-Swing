package com.project1.model;

import java.util.List;

public class Bill {

	private String companyName;
	private String website;
	private String storeAddress;
	private String outputVoucherID;
	private String outputDATE;
	private String outputUSER;
	private int totalAmount;
	private int totalDiscount ;
	private int totalGiftVoucherAmount;
	private int moneyCard;
	private int totalAmountRound;
	private String barcode;
	private String qrCode;
	private String specialMessage;

	private List<Product> products;

	public Bill() {
		this.companyName = "BÁCH HÓA XANH";
		this.website = "www.bachhoaxanh.com";
		this.specialMessage = "Tổng đài góp ý/khiếu nại:1800 1067.\n" 
				+ "Lưu ý: Bách Hóa Xanh chỉ xuất hóa đơn trong ngày, Quý khách vui lòng"
				+ "liên hệ thu ngân để được hỗ trợ. Quý khách có thể in bản sao hóa đơn"
				+ "VAT tại trang web https://hddt.bachhoaxanh.com\n"
				+ "Quý khách vui lòng xem chi tiết Chính Sách đổi-trả hàng được niêm yết"
				+ "tại cửa hàng BHX. Xin cảm ơn quý khách. Hẹn gặp lại.";
	}

	public Bill(String storeAddress, String outputVoucherID, String outputDATE, String outputUSER,
			int totalAmount, int totalDiscount, int totalGiftVoucherAmount, int moneyCard,
			String barcode, String qrcode) {
		this();
		this.storeAddress = storeAddress;
		this.outputVoucherID = outputVoucherID;
		this.outputDATE = outputDATE;
		this.outputUSER = outputUSER;
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
		this.totalGiftVoucherAmount = totalGiftVoucherAmount;
		this.moneyCard = moneyCard;
		this.barcode = barcode;
		this.qrCode = qrcode;
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

	public int getTotalAmount() {
		if (products != null) {
			return products.stream().mapToInt(Product::getTotalAmountVAT).sum();
		}
		return 0;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(int totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public int getTotalGiftVoucherAmount() {
		return totalGiftVoucherAmount;
	}

	public void setTotalGiftVoucherAmount(int totalGiftVoucherAmount) {
		this.totalGiftVoucherAmount = totalGiftVoucherAmount;
	}

	public int getMoneyCard() {
		return moneyCard;
	}

	public void setMoneyCard(int moneyCard) {
		this.moneyCard = moneyCard;
	}

	public int getTotalAmountRound() {
		return getTotalAmount() - (totalDiscount + totalGiftVoucherAmount + moneyCard);
	}

	public void setTotalAmountRound(int totalAmountRound) {
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
