package com.ccshop.entity;

import com.ccshop.Utils;

public class Customer {
	private String name;
	private int beverages = 0;
	
	public Customer(String name) {
		super();
		this.name = Utils.convertNameToKey(name);
	}

	public String getName() {
		return name;
	}

	public void addBeverage() {
		++beverages;
	}

	public int getBeverages() {
		return beverages;
	} 
}
