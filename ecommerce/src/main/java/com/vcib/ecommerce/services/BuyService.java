package com.vcib.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.entities.Product;
import com.vcib.ecommerce.services.fidelity.FidelityService;

@Service
public class BuyService {
	
	private final ProductService productService;
	private final ExchangeService exchangeService;
	private final SellService sellService;
	private final FidelityService fidelityService; 

	public BuyService(ProductService productService, ExchangeService exchangeService, SellService sellService, FidelityService fidelityService) {
			this.productService = productService;
			this.exchangeService = exchangeService;
      this.sellService = sellService;
			this.fidelityService = fidelityService;
    }

    public String buy(Long productId, Long user, boolean ft) {

			Product p1 = productService.getProductById(productId, ft);

			double exchangeValue = exchangeService.getExchangeValue(ft);
			
			fidelityService.addBonus(user,(int) Math.round(p1.getPrice()), ft);

			String transactionId = sellService.sellProduct(productId, ft);

			return transactionId;
    }
}
