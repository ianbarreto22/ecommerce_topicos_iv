package com.vcib.ecommerce.services;

import com.vcib.ecommerce.client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.entities.Product;

@Service
public class BuyService {
	

	private final ProductService productService;

	private final ExchangeService exchangeService;

	public BuyService(ProductService productService, ExchangeService exchangeService) {
		this.productService = productService;
		this.exchangeService = exchangeService;
	}

    public Double buy(Long product, Long user, boolean ft) {

		Product p1 = productService.getProductById(product, ft);

		double exchangeValue = exchangeService.getExchangeValue(ft);

		return exchangeValue;
    }
}
