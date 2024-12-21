package com.vcib.ecommerce.services;

import com.vcib.ecommerce.client.ExchangeClient;
import feign.FeignException;
import org.springframework.stereotype.Service;


@Service
public class ExchangeService {

    private final ExchangeClient exchangeClient;

    private double exchangeLastValue = 6.00;

    public ExchangeService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public double getExchangeValue(boolean ft) {
        if (ft) {
            return getExchangeOrLastValue();
        }

        return getExchangeWithoutLastValue();
    }

    private double getExchangeWithoutLastValue() {
        return exchangeClient.getExchangeRate();
    }

    private double getExchangeOrLastValue() {

        try {
            double exchangeResponse = exchangeClient.getExchangeRate();
            setExchangeLastValue(exchangeResponse);

            return exchangeResponse;
        } catch (FeignException e) {
            System.out.println("FeignException: " + e.getMessage());

            return getExchangeLastValue();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());

            return getExchangeLastValue();
        }
    }

    public double getExchangeLastValue() {
        return this.exchangeLastValue;
    }

    public void setExchangeLastValue(double value) {
        this.exchangeLastValue = value;
    }
}
