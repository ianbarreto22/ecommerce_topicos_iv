package com.vcib.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.entities.Product;

@Service
public class BuyService {
	
	@Autowired 
	private ProductService productService;

    public Product buy(Long product, Long user, boolean ft) {
    	return productService.getProductById(product, ft);
    }
}
