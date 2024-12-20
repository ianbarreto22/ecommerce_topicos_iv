package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "fidelity-service", url = "http://localhost:8080")
public interface FidelityClient {
	@PostMapping("/bonus")
	void addBonus(@RequestParam("user") Long user, @RequestParam("bonus") Integer bonus);
}
