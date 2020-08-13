package com.interview.product.client;


import com.interview.review.models.ReviewModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class ReviewClientImpl implements ReviewClient {

    @Value("${review.url}")
    private String reviewUrl;

    @Override
    public void addReview(int groupId, List<ReviewModel> reviewModel){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(reviewUrl+"/addreview"+"/"+groupId, reviewModel,String.class);
    }

    @Override
    public double getAverage(int groupId){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URI.create(reviewUrl+"/average"+"/"+groupId),Double.class);

    }

}
