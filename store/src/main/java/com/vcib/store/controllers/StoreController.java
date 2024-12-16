package com.vcib.store.controllers;

import com.vcib.store.entities.Product;
import com.vcib.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    private final ProductService productService;

    @Autowired
    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity product(@RequestParam Long id) {

        Product product = productService.findById(id);

        return ResponseEntity.ok().body(product);
    }


    @PostMapping("/sell")
    public String sell(@RequestParam Long id) {

        String uniqueId = productService.sellProduct();

        return uniqueId;
    }
  
}
