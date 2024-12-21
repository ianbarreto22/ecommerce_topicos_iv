package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(url="http://localhost:8080", name="exchange")
public interface ExchangeClient {
	@GetMapping("/exchange")
	public Double getExchangeRate();
}
