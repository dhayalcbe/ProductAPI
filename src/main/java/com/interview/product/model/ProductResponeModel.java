package com.interview.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import com.interview.review.models.ReviewModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponeModel {

    private String name;

    private String type;

    private int price;

    private String imageUrl;

    private double averageRating;

    private List<ProductReviewModel> reviewModels;
}
