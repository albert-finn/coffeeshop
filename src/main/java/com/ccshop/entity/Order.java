package com.ccshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private static String RECEIPT_DELIMITER = "*****************************************";
	private Customer customer;
	private List<Product> products = new ArrayList<Product>();
	private List<Product> discounts = new ArrayList<Product>();
	
	public Order(Customer customer) {
		super();
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public List<Product> getDiscounts() {
		return discounts;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void addDiscount(Product product) {
		discounts.add(product);
	}
	public String createReceipt() {
		
//		If a customer orders a beverage and a snack, one of the extra's is free
		if (hasBeverage()&& hasSnack()) {
			addExtraToDiscounts();			
		}
		
		StringBuffer s = new StringBuffer();
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());
		s.append("Charlene's Coffee Corner").append(System.lineSeparator());
		s.append("Customer:                     " + getCustomer().getName());
 		s.append(System.lineSeparator());
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());
		double total = 0;
		for (Product product: getProducts()) {
			s.append(product.getName() + String.valueOf(product.getPrice() ) + " CHF" +  System.lineSeparator());
			total += product.getPrice();
		}
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());
		s.append("Subtotal:                        " + String.valueOf(total) + " CHF").append(System.lineSeparator());
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());
		s.append("Discounts").append(System.lineSeparator());
		for (Product product: getDiscounts()) {
			s.append(product.getName() + String.valueOf(-product.getPrice() ) + " CHF" +  System.lineSeparator());
			total -= product.getPrice();
		}
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());
		s.append("Total:                        " + String.valueOf(total) + " CHF").append(System.lineSeparator());
		s.append(RECEIPT_DELIMITER).append(System.lineSeparator());		
		return s.toString();
	}
	
	private boolean hasBeverage() {
		return hasProductType("BEVERAGE");
	}
	private boolean hasSnack() {
		return hasProductType("SNACK");
	}
	private boolean hasProductType(String type) {
		for (Product product: getProducts()) {
			if(product.getType().equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}
//	1st extra added to Discount
	private void addExtraToDiscounts() {
		for (Product product: getProducts()) {
			if(product.getType().equalsIgnoreCase("EXTRA")) {
				discounts.add(product);
				break;
			}
		}
		
	}

}
