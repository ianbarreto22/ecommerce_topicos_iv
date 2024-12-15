package com.vcib.exchange.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
	
	public double getExchangeRate() {
		
		double min = 5.8;
        double max = 6.2;
        
        double newRate = min + (max - min) * new Random().nextDouble();
        
        return newRate;
	}
}
