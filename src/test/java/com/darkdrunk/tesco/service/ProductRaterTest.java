package com.darkdrunk.tesco.service;

import com.darkdrunk.tesco.model.Grocery;
import com.darkdrunk.tesco.model.RateToProduct;
import com.darkdrunk.tesco.repository.RateToProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
public class ProductRaterTest {

    private GroceryFetcher fetcher;

    @Autowired
    private RateToProductRepository repository;

    private ProductRater rater;

    @Before
    public void setUp() throws Exception {
        fetcher = mock(GroceryFetcher.class);
        when(fetcher.fetchByProductName("chicken")).thenReturn(
                Grocery.builder()
                        .id(1)
                        .name("Very good chicken")
                        .superDepartment("Kaja")
                        .build()
        );

        rater = new ProductRater(fetcher, repository);
    }

    @Test
    public void rateProduct() throws IOException {
        RateToProduct chicken = rater.rateProduct("chicken", 5);
        assertThat(chicken).hasFieldOrPropertyWithValue("originalName", "Very good chicken")
                            .hasFieldOrPropertyWithValue("productName", "chicken");
    }
}