package com.vcib.ecommerce.services;

import com.vcib.ecommerce.client.StoreClient;
import com.vcib.ecommerce.entities.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class SellService {

    public final StoreClient storeClient;
    private static final long SELL_FAILURE_STATE_TIME = 5 * 1000;

    public SellService(StoreClient storeClient) {
        this.storeClient = storeClient;
    }

    public String sellProduct(Long id, Boolean ft) {
        if(ft) {
            return sellProductWithRetry(id);
        }

        return sellProductWithoutRetry(id);
    }

    public String sellProductWithRetry(Long id) {
        int maxAttempts = 2;
        int attempt = 0;

        while (attempt < maxAttempts) {
            try {
                return storeClient.sell(id).getBody();
            } catch (Exception e) {
                attempt++;
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());

                try {
                    Thread.sleep(SELL_FAILURE_STATE_TIME); // Sleep to simulate omission failure
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted during omission failure");
                }

                if (attempt >= maxAttempts) {
                    throw new RuntimeException("O serviço de vendas está indisponível. Tente novamente depois!", e);
                }
            }
        }
        throw new RuntimeException("Unexpected error");
    }

    public String sellProductWithoutRetry(Long id) {
        return storeClient.sell(id).getBody();
    }

}
