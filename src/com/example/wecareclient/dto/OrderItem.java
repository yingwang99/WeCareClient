package com.example.wecareclient.dto;

public class OrderItem {

	private Item item;
	private int itemNumber;
	private double subtotal;
	
	
	public OrderItem(Item item, int itemNumber, double subtotal) {
		super();
		this.setItem(item);
		this.setItemNumber(itemNumber);
		this.setSubtotal(subtotal);
	}


	public OrderItem() {
		super();
	}
	
	
	protected void updateItemNumber(){
		
	}
	
	protected void calculateSubtotal(){
		
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public int getItemNumber() {
		return itemNumber;
	}


	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
