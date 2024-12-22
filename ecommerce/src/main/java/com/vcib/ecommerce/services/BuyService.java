package com.vcib.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.entities.Product;
import com.vcib.ecommerce.services.fidelity.FidelityService;

@Service
public class BuyService {
	

	private final ProductService productService;

	private final ExchangeService exchangeService;
	
	@Autowired
	private FidelityService fidelityService; 

	public BuyService(ProductService productService, ExchangeService exchangeService) {
		this.productService = productService;
		this.exchangeService = exchangeService;
	}

    public Double buy(Long product, Long user, boolean ft) {

		Product p1 = productService.getProductById(product, ft);

		double exchangeValue = exchangeService.getExchangeValue(ft);
		
		fidelityService.addBonus(user,(int) Math.round(p1.getPrice()), ft);

		return exchangeValue;
    }
}
