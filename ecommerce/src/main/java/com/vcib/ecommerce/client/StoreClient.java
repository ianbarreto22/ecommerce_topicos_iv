package com.vcib.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcib.ecommerce.entities.Product;

@FeignClient(url = "http://localhost:8080", name="store")
public interface StoreClient {
	
	@GetMapping("/product")
    Product product(@RequestParam Long id);
	
	@PostMapping("/sell")
    ResponseEntity<String> sell(@RequestParam Long id);
}
