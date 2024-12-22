package com.vcib.ecommerce.services;

import com.vcib.ecommerce.client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.entities.Product;

@Service
public class BuyService {
	

	private final ProductService productService;
	private final ExchangeService exchangeService;
	private final SellService sellService;

	public BuyService(ProductService productService, ExchangeService exchangeService, SellService sellService) {
		this.productService = productService;
		this.exchangeService = exchangeService;
        this.sellService = sellService;
    }

    public String buy(Long productId, Long user, boolean ft) {

		Product p1 = productService.getProductById(productId, ft);

		double exchangeValue = exchangeService.getExchangeValue(ft);

		String transactionId = sellService.sellProduct(productId, ft);

		return transactionId;
    }
}
