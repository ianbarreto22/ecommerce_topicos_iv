package com.vcib.store.services;

import com.vcib.store.entities.Product;
import com.vcib.store.entities.exceptions.ResourceNotFoundException;
import com.vcib.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public String sellProduct() {

        return UUID.randomUUID().toString();
    }

}
