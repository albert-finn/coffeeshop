package com.ccshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilsTest {
	@Test
	public void convertNameToKeyTest() {
		Object keyStr = Utils.convertNameToKey("Freshly Squeezed Orange Juice");
		assertEquals("FRESHLYSQUEEZEDORANGEJUICE", keyStr);
	}
	
}
