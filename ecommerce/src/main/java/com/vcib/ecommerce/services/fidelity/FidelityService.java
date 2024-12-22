package com.vcib.ecommerce.services.fidelity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.client.FidelityClient;

import feign.FeignException;

@Service
public class FidelityService {
	
	@Autowired
	private FidelityClient fidelityClient;
	
	@Autowired
	private FidelityProducer fidelityProducer;
	
	public void addBonus(Long user, int bonus, boolean ft) {
		if(ft) {
			addBonusWithRabbit(user,bonus);
		} else {
			addBonusWithoutRabbit(user,bonus);
		}
	}
	
	private void addBonusWithRabbit(Long user, int bonus) {
		try {
			fidelityClient.addBonus(user, bonus);
		} catch(FeignException e) {
			fidelityProducer.sendMessage(user, bonus);
		}
	}
	
	private void addBonusWithoutRabbit(Long user, int bonus) {
		fidelityClient.addBonus(user, bonus);
	}
	
}
