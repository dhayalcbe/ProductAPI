package com.interview.product.service;

import com.interview.product.client.ReviewClient;
import com.interview.product.entity.ProductEntity;
import com.interview.review.dto.Review;
import com.interview.review.dto.ReviewGroup;
import com.interview.review.dto.ReviewGroupDetails;
import com.interview.review.models.ReviewModel;
import com.interview.product.model.ProductRequestModel;
import com.interview.product.model.ProductResponeModel;
import com.interview.product.model.ProductReviewModel;
import com.interview.product.repository.ProductRepository;
import com.interview.review.repo.ReviewGroupRepository;
import com.interview.review.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductApiServiceImpl implements ProductApiService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewGroupRepository reviewGroupRepository;

    @Autowired
    ReviewClient reviewClient;
    
    @Autowired
    ReviewService reviewSevice;

    @Override
    public void createProduct(ProductRequestModel productModel){
    	if(Objects.nonNull(productRepository.findByReviewGroupId(productModel.getGroupId()))
    		|| !reviewGroupRepository.findById(productModel.getGroupId()).isPresent()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid group id");    		
    	}
    	
        Optional<ReviewGroup> reviewGroup = reviewGroupRepository.findById(productModel.getGroupId());
        ProductEntity product = convertProductModelToDto(productModel);
        product.setReviewGroup(reviewGroup.get());
        productRepository.save(product);
    }

    @Override
    public void editProduct(int productId,ProductRequestModel productModel){
        Optional<ProductEntity> productOp =  productRepository.findById(productId);
        checkProductExists(productOp);
        ProductEntity editEntity = convertProductModelToDto(productModel);
        editEntity.setId(productId);
        productRepository.save(editEntity);
    }

    @Override
    public void addProductReview(int productId, List<ReviewModel> reviews){
        Optional<ProductEntity> productOp = productRepository.findById(productId);
        checkProductExists(productOp);
        reviewClient.addReview(productOp.get().getReviewGroup().getId(),reviews);

    }

    @Override
    public ProductResponeModel getProductDetails(int productId){
        Optional<ProductEntity> productOp = productRepository.findById(productId);
        checkProductExists(productOp);
        return convertProductDtoToModel(productOp.get(),reviewClient.getAverage(productOp.get().getReviewGroup().getId()));

    }

    private static ProductEntity convertProductModelToDto(ProductRequestModel productModel){
        return ProductEntity.builder().name(productModel.getName())
                .type(productModel.getType())
                .imageUrl(productModel.getImageUrl())
                .price(productModel.getPrice())
                .build();

    }

    private static ProductResponeModel convertProductDtoToModel(ProductEntity productEntity ,double average){
       return ProductResponeModel.builder()
                .name(productEntity.getName())
                .type(productEntity.getType())
                .imageUrl(productEntity.getImageUrl())
                .averageRating(average)
               .price(productEntity.getPrice())
                .reviewModels(buildReviewList(productEntity.getReviewGroup().getReviewGroupDetails()))
                .build();

    }

    private static List<ProductReviewModel> buildReviewList(List<ReviewGroupDetails> reviewDetails){
        List<ProductReviewModel> reviewList = new ArrayList<>();
        reviewDetails.stream().forEach(reviewGroupDetails ->
                reviewGroupDetails.getReview().stream().forEach(review ->
                    reviewList.add(buildReview(review))
                ));
        return reviewList;

    }
    private static ProductReviewModel buildReview(Review review){
        return ProductReviewModel.builder()
                .comments(review.getComments())
                .rating(review.getRating()).build();
    }
    
    private static void checkProductExists(Optional<ProductEntity> productOp) {
    	 if(!productOp.isPresent()){
         	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid product id");
        }
    }

}
