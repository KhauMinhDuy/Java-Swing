package com.project1.event;

import java.util.EventObject;
import java.util.List;

import com.project1.model.Bill;
import com.project1.model.Product;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private Bill bill;

	public FormEvent(Object source, Bill bill) {
		super(source);

		this.bill = bill;
	}

	public String getStoreAddress() {
		return bill.getStoreAddress();
	}

	public String getOutputVoucherID() {
		return bill.getOutputVoucherID();
	}

	public String getOutputDATE() {
		return bill.getOutputDATE();
	}

	public String getOutputUSER() {
		return bill.getOutputUSER();
	}

	public Integer getTotalDiscount() {
		return bill.getTotalDiscount();
	}

	public Integer getTotalGiftVoucherAmount() {
		return bill.getTotalGiftVoucherAmount();
	}

	public Integer getMoneyCard() {
		return bill.getMoneyCard();
	}
	
	public List<Product> getProducts() {
		return bill.getProducts();
	}
	
	public int getTotalAmount() {
		return bill.getTotalAmount();
	}
	

}
