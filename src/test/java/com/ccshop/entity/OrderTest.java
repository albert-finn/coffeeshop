package com.ccshop.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.ccshop.service.CCShopService;


public class OrderTest {
	@Test
	public void createReceiptTest() throws IOException {
		CCShopService service = new CCShopService();
		Customer customer = service.getCustomer("James");
		Order order = service.createOrder(customer, "Freshly squeezed orange juice,small Coffee,Bacon Roll");
		Path filePath = Path.of("src/test/resources/test_receipt_james.txt");
		String receiptStr = Files.readString(filePath);
		assertEquals(receiptStr, order.createReceipt());
	}
	
	@Test
	public void beverageDiscountTest() throws IOException {
		CCShopService service = new CCShopService();
		Customer customer = service.getCustomer("Robert");
		customer.addBeverage();
		customer.addBeverage();
		customer.addBeverage();
		customer.addBeverage();
		assertEquals(4, customer.getBeverages());

		Order order = service.createOrder(customer, "Freshly squeezed orange juice,small Coffee with Extra Milk,Bacon Roll");
		Path filePath = Path.of("src/test/resources/test_receipt_robert.txt");
		String receiptStr = Files.readString(filePath);
		assertEquals(receiptStr, order.createReceipt());
		
		assertEquals(6, customer.getBeverages());
	}
}
