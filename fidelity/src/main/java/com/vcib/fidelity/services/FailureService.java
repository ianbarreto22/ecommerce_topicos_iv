package com.vcib.fidelity.services;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

@Service
public class FailureService {
	private static final double FAILURE_PROBABILITY = 0.1;
	private static final long SLEEP_DURATION_MS = 2 * 1000;
	private static final long FAILURE_DURATION_MS = 30 * 1000;
	private static AtomicBoolean inFailureState = new AtomicBoolean(false);
	
	public void simulateFailure() {
		
		boolean inFailure = inFailureState.get();
		
		if(inFailure || Math.random() < FAILURE_PROBABILITY) {
			try {
			
				if(!inFailure) {
					enterFailureState();
				}
				
				Thread.sleep(SLEEP_DURATION_MS);
			
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void enterFailureState() {
		inFailureState.set(true);;
		
		new Thread(() -> {
			try {
				Thread.sleep(FAILURE_DURATION_MS);
			} catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				System.out.println("Saindo do estado de falha...");
				inFailureState.set(false);
			}
			
		}).start();
	}
	
}
