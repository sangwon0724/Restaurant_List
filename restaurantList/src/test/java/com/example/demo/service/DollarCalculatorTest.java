package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DollarCalculatorTest {
	
	@Test
	public void dollarTest() {
		ExchangeRate exchangeRate=new ExchangeRate();
		DollarCalculator dollarCalculator=new DollarCalculator(exchangeRate);
		dollarCalculator.init();
		
		Assertions.assertEquals(dollarCalculator.sum(3, 5), 8800);
	}
	
}
