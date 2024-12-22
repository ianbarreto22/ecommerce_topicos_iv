package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcib.ecommerce.entities.Product;

@FeignClient(url="${services.default.url}", name="store")
public interface StoreClient {
	
	@GetMapping("/product")
	public Product product(@RequestParam Long id);
	
	@PostMapping("/sell")
	public int sell(@RequestParam Integer id);
}
