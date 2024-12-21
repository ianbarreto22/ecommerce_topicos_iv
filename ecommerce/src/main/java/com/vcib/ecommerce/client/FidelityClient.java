package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url="http://localhost:8080", name="fidelity")
public interface FidelityClient {
	@PostMapping("/bonus")
	public void addBonus(@RequestParam("user") Long user, @RequestParam("bonus") Integer bonus);
}
