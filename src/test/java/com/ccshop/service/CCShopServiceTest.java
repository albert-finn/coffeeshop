package com.ccshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.ccshop.entity.Customer;
import com.ccshop.entity.Order;


public class CCShopServiceTest {

	
	
	@Test
	public void getCustomerTest() {
		
		CCShopService service = new CCShopService();
		Customer customer = service.getCustomer(" asdfghj ");
		customer.addBeverage();
		assertEquals("ASDFGHJ", customer.getName());
		assertEquals(1, customer.getBeverages());
		
		Customer customer1 = service.getCustomer("asdfghj");
		customer.addBeverage();
		assertEquals("ASDFGHJ", customer1.getName());
		assertEquals(2, customer.getBeverages());
		
		Customer customer2 = service.getCustomer("aSdfGhJ");
		customer.addBeverage();
		assertEquals("ASDFGHJ", customer1.getName());
		assertEquals(3, customer.getBeverages());
		
		
        assertThrows(IllegalArgumentException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	service.getCustomer(" ");
            }
        });
		
        assertThrows(IllegalArgumentException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	service.getCustomer("");
            }
        });
	}
	
	@Test
	public void createOrderTest() {
		CCShopService service = new CCShopService();
		Customer customer = service.getCustomer("John");
		Order order = service.createOrder(customer, "Freshly squeezed orange juice,small Coffee,Bacon Roll");
		assertEquals(3, order.getProducts().size());
		assertEquals(2, customer.getBeverages());
		assertEquals(0, order.getDiscounts().size());
		
		Order order1 = service.createOrder(customer, "Freshly squeezed orange juice,small Coffee with extra milk,Bacon Roll");
		assertEquals(4, order1.getProducts().size());
		assertEquals(4, customer.getBeverages());
		assertEquals(0, order.getDiscounts().size());
		
		//			 every 5th beverage is for free
		Order order2 = service.createOrder(customer, "Freshly squeezed orange juice,small Coffee with extra milk,Bacon Roll");
		assertEquals(4, order2.getProducts().size());
		assertEquals(6, customer.getBeverages());
		assertEquals(1, order2.getDiscounts().size());
	}
	
}
