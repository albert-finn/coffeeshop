package com.ccshop;

public class Utils {
	public static final String SPACE= " ";
	public static final String EXTRA_DELIMITER = " with ";
	public static final String PRODUCT_DELIMITER = ",";
	public static String convertNameToKey(String name) {
		return name.toUpperCase().replace(SPACE, "");
	}
}
