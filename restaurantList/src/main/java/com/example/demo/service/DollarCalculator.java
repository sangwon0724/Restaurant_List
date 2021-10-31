package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.Calculator;

@Service
public class DollarCalculator implements Calculator{
	private int priceDollar=1;
	private ExchangeRate exchangeRate;
	
	public DollarCalculator(ExchangeRate exchangeRate) {
		this.exchangeRate=exchangeRate;
	}
	
	public void init() {
		this.priceDollar=exchangeRate.priceDollar();
	}
	
	@Override
    public int sum(int x, int y) {
        x *= priceDollar;
        y *= priceDollar;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= priceDollar;
        y *= priceDollar;
        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        x *= priceDollar;
        y *= priceDollar;
        return x * y;
    }

    @Override
    public int division(int x, int y) {
        x *= priceDollar;
        y *= priceDollar;
        return x / y;
    }
}
