package demoapp.model;

import java.util.List;

import demoapp.model.Item;

public class CartList {

	List<Item> cart;

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public CartList(List<Item> cart) {
		super();
		this.cart = cart;
	}
	
	
}
