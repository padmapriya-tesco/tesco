package com.darkdrunk.tesco.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RateToProduct {

    @Id
    @GeneratedValue
    private Long id;

    private int productId;
    private String originalName;
    private String superDepartment;

    private String productName;
    private int rating;
}
