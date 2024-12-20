package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name= "exchange-service", url = "http://localhost:8080")
public interface ExchangeClient {
	@GetMapping("/exchange")
	Double getExchangeRate();
}
