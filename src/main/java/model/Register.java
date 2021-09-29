package model;

import java.io.Serializable;

public class Register implements Serializable {
	private String itemName;
	private int payment;
	private int income;
	private int categoryId;

	public Register(String itemName, int payment, int income, int categoryId) {
		this.itemName = itemName;
		this.payment = payment;
		this.income = income;
		this.categoryId = categoryId;
	}
	public String getItemName() { return itemName; }
	public int getPayment() { return payment; }
	public int getIncome() { return income; }
	public int getCategoryId() { return categoryId; }
}
