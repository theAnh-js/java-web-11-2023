package com.fuku.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

	// chứa danh sách các ItemModel
	// trong khi đó, mỗi itemModel lại là 1 product.

	private List<ItemModel> listItem;

	public CartModel() {
		setListItem(new ArrayList<>());
	}

	public List<ItemModel> getListItem() {
		return listItem;
	}

	public void setListItem(List<ItemModel> listItem) {
		this.listItem = listItem;
	}

	// lấy các item trong list theo id
	public ItemModel getItemById(int id) {

		for (ItemModel item : listItem) {
			if (item.getProduct().getProductID() == id) {
				return item;
			}
		}
		return null;
	}

	// lấy số lượng item qua id
	public int getQuantityById(int id) {
		return getItemById(id).getQuantity();
	}

	// thêm item vào , nếu có trong listProduct rồi thì chỉ cộng dồn quantity là ok
	public void addItem(ItemModel item) {
		ProductModel product = item.getProduct();
		if (getItemById(product.getProductID()) != null) {
			ItemModel existItem = getItemById(product.getProductID());
			existItem.setQuantity(item.getQuantity() + existItem.getQuantity());
		} else {
			listItem.add(item);
		}
	}

	// xóa item khỏi list
	public void removeItemById(int id) {
		if (getItemById(id) != null) {
			listItem.remove(getItemById(id));
		}
	}

	// lấy ra tổng tiền trong listItem
	public double getTotalMoney() {
		double money = 0;
		for (ItemModel item : listItem) {
			money += item.getPrice() * item.getQuantity();
		}
		return money;
	}

}
