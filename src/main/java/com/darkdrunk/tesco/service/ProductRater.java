package com.darkdrunk.tesco.service;

import com.darkdrunk.tesco.model.Grocery;
import com.darkdrunk.tesco.model.RateToProduct;
import com.darkdrunk.tesco.repository.RateToProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductRater {

    private GroceryFetcher fetcher;
    private RateToProductRepository rateToProductRepository;

    public RateToProduct rateProduct(String name, int rating) throws IOException {
        Grocery grocery = fetcher.fetchByProductName(name);

        RateToProduct rateToProduct = RateToProduct.builder()
                .productId(grocery.getId())
                .originalName(grocery.getName())
                .superDepartment(grocery.getSuperDepartment())
                .productName(name)
                .rating(rating)
                .build();

        return rateToProductRepository.save(rateToProduct);
    }
}
