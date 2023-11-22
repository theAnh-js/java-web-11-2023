package com.fuku.model;

public class ProductModel {

	private int productID;
	private String productName;
	private String description;
	private int price;
	private String imageLink;
	private int categoryID;
	private int sellerID;
	private int amount;

	public ProductModel() {

	}

	public ProductModel(int productID, String productName, String description, int price, String imageLink,
			int categoryID, int sellerID, int amount) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.imageLink = imageLink;
		this.categoryID = categoryID;
		this.sellerID = sellerID;
		this.amount = amount;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ProductModel [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", imageLink=" + imageLink + ", categoryID=" + categoryID + ", sellerID="
				+ sellerID + ", amount=" + amount + "]";
	}

}
