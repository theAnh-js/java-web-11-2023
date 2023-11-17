package com.laptrinhjavaweb.model;

public class CategoryModel extends AbstractModel<CategoryModel>{
	private String code;
	private String name;

	public CategoryModel() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
