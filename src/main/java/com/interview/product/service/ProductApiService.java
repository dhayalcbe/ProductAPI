package com.interview.product.service;

import com.interview.product.model.ProductRequestModel;
import com.interview.product.model.ProductResponeModel;
import com.interview.review.models.ReviewModel;

import java.util.List;

public interface ProductApiService {

    void createProduct(ProductRequestModel productModel);

    void editProduct(int productId,ProductRequestModel productModel);

    void addProductReview(int productId,List<ReviewModel> reviews);

    ProductResponeModel getProductDetails(int productId);


}
