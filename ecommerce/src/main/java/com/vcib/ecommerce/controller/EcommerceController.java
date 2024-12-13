package com.vcib.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    @GetMapping("api/buy")
    public String buy(@RequestParam(name = "id") int productId,
                      @RequestParam(name = "buyer") int buyer,
                      @RequestParam(name = "ft") boolean ft) {


        return "id-" + productId + " buyer-" + buyer + " ft-" + ft;
    }

}
