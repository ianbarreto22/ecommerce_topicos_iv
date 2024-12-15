package com.vcib.exchange.services;

import org.springframework.stereotype.Service;

@Service
public class FailureService {
	private static double FAILURE_PROBABILITY = 0.1;
	
	public void simulateFailure() {
		if(Math.random() < FAILURE_PROBABILITY) {
			System.exit(1);
		}
	}
}
