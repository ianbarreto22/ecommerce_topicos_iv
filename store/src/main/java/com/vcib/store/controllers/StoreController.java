package com.vcib.store.controllers;

import com.vcib.store.entities.Product;
import com.vcib.store.services.FailureService;
import com.vcib.store.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    private final ProductService productService;
    private final FailureService failureService;

    public StoreController(ProductService productService, FailureService failureService) {
        this.productService = productService;
        this.failureService = failureService;
    }

    @GetMapping("/product")
    public ResponseEntity<Product> product(@RequestParam Long id) {
        
        failureService.simulateOmissionFailure();

        Product product = productService.findById(id);

        return ResponseEntity.ok().body(product);
    }


    @PostMapping("/sell")
    public String sell(@RequestParam Long id) {

        failureService.simulateErrorFailure();

        String uniqueId = productService.sellProduct();

        return uniqueId;
    }
  
}
