package com.vcib.exchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcib.exchange.services.ExchangeService;
import com.vcib.exchange.services.FailureService;

@RestController
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private FailureService failureService;
	
	@GetMapping("/exchange")
	public double getExchangeRate() {
		
		failureService.simulateFailure();
		
		return exchangeService.getExchangeRate();
	}
}
