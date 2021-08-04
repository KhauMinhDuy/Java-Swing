package com.project1.event;

import java.util.EventObject;

import com.project1.model.Bill;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private Bill bill;

	public FormEvent(Object source, Bill bill) {
		super(source);

		this.bill = bill;
	}

	public String getAddress() {
		return bill.getAddress();
	}
	
	public String getSoCT() {
		return bill.getSoCT();
	}
	
	public String getDateTime() {
		return bill.getDateTime();
	}
	
	public String getEmployee() {
		return bill.getNhanVien();
	}
	
	public boolean getGiamGia() {
		return bill.isGiamGia();
	}
	
	public boolean getPhieuMH() {
		return bill.isPhieuMH();
	}

	public boolean getTienThe() {
		return bill.isTienThe();
	}
}
