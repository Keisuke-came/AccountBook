package model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
	private int itemId;
	private String itemName;
	private int payment;
	private int income;
	private Date date;
	private String category;
	private String userName;

	public Item(int itemId) {
		this.itemId = itemId;
	}

	public Item(int itemId, String itemName, int payment, int income, Date date, String category, String userName) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.payment = payment;
		this.income = income;
		this.date = date;
		this.category = category;
		this.userName = userName;
	}
	public int getItemId() { return itemId; }
	public String getItemName() { return itemName; }
	public int getPayment() { return payment; }
	public int getIncome() { return income; }
	public Date getDate() { return date; }
	public String getCategory() { return category; }
	public String getUserName() { return userName; }
}
