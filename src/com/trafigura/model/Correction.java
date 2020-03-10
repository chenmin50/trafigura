package com.trafigura.model;

public class Correction {
	private String securityCode;
	private int quantity;
	
	public Correction(String securityCode, int quantity)
	{
		this.securityCode = securityCode;
		this.quantity = quantity;
	}
	
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
