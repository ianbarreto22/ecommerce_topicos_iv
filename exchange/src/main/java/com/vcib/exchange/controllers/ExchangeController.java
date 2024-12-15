package com.vcib.exchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcib.exchange.services.ExchangeService;

@RestController
public class ExchangeController {
	@Autowired
	private ExchangeService exchangeService;
	
	private static double FAILURE_PROBABILITY = 0.1;
	
	@GetMapping("/exchange")
	public double getExchangeRate() {
		
		if(Math.random() < FAILURE_PROBABILITY) {
			System.exit(1);
		}
		
		return exchangeService.getExchangeRate();
	}
}
