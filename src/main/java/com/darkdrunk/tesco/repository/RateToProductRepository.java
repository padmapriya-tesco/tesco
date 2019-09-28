package com.darkdrunk.tesco.repository;

import com.darkdrunk.tesco.model.RateToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateToProductRepository extends JpaRepository<RateToProduct, Long> {

    List<RateToProduct> findByProductNameAndRating(String productName, int rating);
}
