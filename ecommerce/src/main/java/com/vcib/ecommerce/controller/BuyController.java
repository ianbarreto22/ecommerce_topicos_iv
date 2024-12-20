package com.vcib.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

    @GetMapping("/buy")
    public String buy(@RequestParam(name = "product") int product,
                      @RequestParam(name = "user") int user,
                      @RequestParam(name = "ft") boolean ft) {
    	
    	
    	

        return "id-" + product + " buyer-" + user + " ft-" + ft;
    }

}
