package com.ccshop.service;

import java.util.HashMap;
import java.util.Map;


import com.ccshop.Utils;
import com.ccshop.entity.Customer;
import com.ccshop.entity.Order;
import com.ccshop.entity.Product;

public class CCShopService {
	private static Map<String, Customer> customers = new HashMap<String, Customer>();
	private static Map<String, Product> availableProductsMap = new HashMap<String, Product>();
	private static Product[] availableProducts = { 
    		new Product("Small Coffee                  ", 2.50, "BEVERAGE"),
    		new Product("Medium Coffee                 ", 3.00, "BEVERAGE"),
    		new Product("Large Coffee                  ", 3.50, "BEVERAGE"),
    		new Product("Bacon Roll                    ", 2.50, "SNACK"),
    		new Product("Freshly Squeezed Orange Juice ", 3.95, "BEVERAGE"),
    		new Product("Extra Milk                    ", 0.30, "EXTRA"),
    		new Product("Foamed Milk                   ", 0.50, "EXTRA"),
    		new Product("Special Roast Coffee          ", 0.90, "EXTRA"),
    };
	static {
		for(Product product: availableProducts) {
			availableProductsMap.put(Utils.convertNameToKey(product.getName()), product);
		}
	}
	
	public Customer getCustomer(String name) {
		if (name.isEmpty()||name.isBlank()) throw new IllegalArgumentException ("Customer name to be specified");
		name = Utils.convertNameToKey(name);
		if (customers.get(name)== null)
			customers.put(name, new Customer(name));
		return customers.get(name);
	}
	
	public Order createOrder(Customer customer, String orderString) {
		if (orderString.isEmpty()||orderString.isBlank()) throw new IllegalArgumentException ("At liest one product should be in the list");
		Order order = new Order (customer);
		String[] orderedProducts = orderString.replaceAll(Utils.EXTRA_DELIMITER, Utils.PRODUCT_DELIMITER).replaceAll(Utils.SPACE, "").split(Utils.PRODUCT_DELIMITER);
		for (String productName: orderedProducts) {
			Product productInMenu = availableProductsMap.get(Utils.convertNameToKey(productName));
			if(productInMenu==null)
				throw new IllegalArgumentException ("Product " + productName + "is not available"); 
			order.addProduct(productInMenu);
			if(productInMenu.getType().equalsIgnoreCase("BEVERAGE")) {
				customer.addBeverage();
			}
//			 every 5th beverage is for free
			if (customer.getBeverages()%5==0) {
				order.addDiscount(productInMenu);
			}
		}
		
		return order;
	}
}
 