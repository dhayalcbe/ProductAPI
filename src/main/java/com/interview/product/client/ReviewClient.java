package com.interview.product.client;

import com.interview.review.models.ReviewModel;

import java.util.List;

public interface ReviewClient {

    void addReview(int groupId,List<ReviewModel> reviewModel);

    double getAverage(int groupId);
}
