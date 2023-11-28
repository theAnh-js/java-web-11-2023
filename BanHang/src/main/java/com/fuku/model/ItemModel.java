package com.fuku.model;

public class ItemModel {
	
	// chứa những ProductModel được ném vào trong giỏ hàng
	
	private ProductModel product;
	private int quantity;
	private double price;
	
	public ItemModel() {
		
	}
	
	public ItemModel(ProductModel product, int quantity, double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}



	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
