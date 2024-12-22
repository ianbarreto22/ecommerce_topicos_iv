package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url="${services.default.url}", name="exchange")
public interface ExchangeClient {
	@GetMapping("/exchange")
    double getExchangeRate();
}
