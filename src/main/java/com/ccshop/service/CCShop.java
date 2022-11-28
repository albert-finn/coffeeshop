package com.ccshop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ccshop.Utils;
import com.ccshop.entity.Customer;
import com.ccshop.entity.Order;

public class CCShop {

	public static void main(String[] args) 
			 throws IOException
	{
		CCShopService service = new CCShopService();
		while (1==1) {
	        try {
				BufferedReader reader = new BufferedReader(
				    new InputStreamReader(System.in));
				System.out.println("Enter the customer name");
				String name = reader.readLine();

				Customer customer = service.getCustomer(name);
				System.out.println("Enter the order");
				String orderString = reader.readLine();
				Order order = service.createOrder(customer, orderString);
				System.out.print(order.createReceipt());
				
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}	
        
		}

	}

}
