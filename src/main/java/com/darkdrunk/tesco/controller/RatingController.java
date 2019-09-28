package com.darkdrunk.tesco.controller;

import com.darkdrunk.tesco.model.Grocery;
import com.darkdrunk.tesco.model.RateToProduct;
import com.darkdrunk.tesco.repository.RateToProductRepository;
import com.darkdrunk.tesco.service.GroceryFetcher;
import com.darkdrunk.tesco.service.ProductRater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private ProductRater productRater;

    @Autowired
    private RateToProductRepository repository;

    @GetMapping("/add/{name}/{rating}")
    public RateToProduct rateByName(@PathVariable("name") String productId, @PathVariable("rating") int rating) throws IOException {
        return productRater.rateProduct(productId, rating);
    }

    @GetMapping("/search/{name}/{rating}")
    public List<RateToProduct> search(@PathVariable("name") String name, @PathVariable("rating") int rating) {
        return repository.findByProductNameAndRating(name, rating);
    }

}
