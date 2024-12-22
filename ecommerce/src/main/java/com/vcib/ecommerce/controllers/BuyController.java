package com.vcib.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcib.ecommerce.entities.Product;
import com.vcib.ecommerce.services.BuyService;

@RestController
public class BuyController {
	
	@Autowired
	private BuyService buyService;

    @GetMapping("/buy")
    public ResponseEntity<String> buy(@RequestParam(name = "product") Long product,
                      @RequestParam(name = "user") Long user,
                      @RequestParam(name = "ft") boolean ft) {
    	
    	
    	return ResponseEntity.ok().body(buyService.buy(product, user, ft));
    }

}
