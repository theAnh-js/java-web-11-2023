package com.fuku.model;

public class CategoryModel {

	private int categoryID;
	private String categoryName;
	private String icon;
	
	private int amountOfProduct;


	public CategoryModel() {
	}

	public CategoryModel(int categoryID, String categoryName, String icon) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.icon = icon;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getAmountOfProduct() {
		return amountOfProduct;
	}

	public void setAmountOfProduct(int amountOfProduct) {
		this.amountOfProduct = amountOfProduct;
	}

}
