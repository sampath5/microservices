package com.microservice.demo.common;

public class Order {
	private int id;
	private String item;
	private int amount;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Order(int id, String item, int amount) {
		super();
		this.id = id;
		this.item = item;
		this.amount = amount;
	}
	public Order() {
		super();
	}
}
