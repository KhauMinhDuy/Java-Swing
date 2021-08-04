package com.project1.event;

import java.util.EventObject;

import com.project1.model.Payment;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private Payment order;

	public FormEvent(Object source, Payment order) {
		super(source);

		this.order = order;
	}

	public String getAddress() {
		return order.getAddress();
	}
	
	public String getSoCT() {
		return order.getSoCT();
	}
	
	public String getDateTime() {
		return order.getDateTime();
	}
	
	public String getEmployee() {
		return order.getNhanVien();
	}

}
