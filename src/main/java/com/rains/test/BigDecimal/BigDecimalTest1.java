package com.rains.test.BigDecimal;

import java.math.BigDecimal;

public class BigDecimalTest1 {

	public static void main(String[] args) {
		BigDecimal b = new BigDecimal("0.12");
		b = b.multiply(new BigDecimal(100));
		System.out.println(b.toString());//12.00
		System.out.println(b.stripTrailingZeros().toPlainString());//12
		
		b = b.multiply(new BigDecimal("100000000000000"));
		System.out.println(b.toString());//12000000000000.00
		System.out.println(b.stripTrailingZeros().toPlainString());//12000000000000
	}

}
