package com.vcib.ecommerce.services;

import com.vcib.ecommerce.client.StoreClient;
import com.vcib.ecommerce.utils.CircuitBreaker;

import org.springframework.stereotype.Service;

@Service
public class SellService {

    public final StoreClient storeClient;
    private final CircuitBreaker circuitBreaker;
    private static final long SELL_FAILURE_STATE_TIME = 5 * 1000;

    public SellService(StoreClient storeClient) {
        this.storeClient = storeClient;
        this.circuitBreaker = new CircuitBreaker(3, SELL_FAILURE_STATE_TIME, 2);
    }

    public String sellProduct(Long id, Boolean ft) {
        if(ft) {
            return processSellWithRetry(id);
        }

        return processSellWithoutRetry(id);
    }

    public String processSellWithRetry(Long id) {
        if (!circuitBreaker.allowRequest()) {
            throw new RuntimeException("Serviço de vendas indisponível. Tente novamente mais tarde.");
        }

        int maxAttempts = 2;
        int attempt = 0;

        while (attempt < maxAttempts) {
            try {
                String response = storeClient.sell(id).getBody();
                circuitBreaker.onSuccess();
                return response;
            }
            catch (Exception e) {
                attempt++;
                circuitBreaker.onFailure();

                try {
                    Thread.sleep(SELL_FAILURE_STATE_TIME);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Erro ao processar a venda. Tente novamente mais tarde.", ie);
                }

                if (attempt >= maxAttempts) {
                    throw new RuntimeException("Erro ao processar a venda. Tente novamente mais tarde.", e);
                }
            }
        }

        return "Erro ao processar a venda. Tente novamente mais tarde.";
    }

    public String processSellWithoutRetry(Long id) {
        return storeClient.sell(id).getBody();
    }

}
