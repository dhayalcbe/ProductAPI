package com.zirius.productAPI.service;

import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zirius.productAPI.dao.ProductDao;
import com.zirius.productAPI.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao pdao;


	RestTemplate restTemplate = new RestTemplate();

	public void addUpdateProduct(Product p){

		pdao.save(p);

	}

	public void addProductReview(Integer productId, HashMap reviewDetails){

		try{

			Product p = pdao.findById(productId).get();
			Integer groupId = p.getReviewGroupId();
			if( groupId != null){
				((HashMap) reviewDetails.get("reviewGroup")).put("groupId",groupId);
			}

			URI postReviewsUrl = null;

			postReviewsUrl = new URI("http://localhost:8080/addReview");

			Integer response
			= restTemplate.postForObject(postReviewsUrl, reviewDetails, Integer.class);

			p.setReviewGroupId(response);
			pdao.save(p);

		}
		catch(Exception e){

			e.printStackTrace();
		}
	}

	public HashMap<String,Object> getProductInfo(Integer productId){

		HashMap<String, Object> obj = new HashMap<String, Object>();

		try {
			Product p = pdao.findById(productId).get();
			obj.put("product", p);
			Integer reviewGroupId = p.getReviewGroupId();
			if(reviewGroupId != null){
				URI getReviewsUrl = new URI("http://localhost:8080/getAllReviews/"+ p.getReviewGroupId());;

				HashMap<?, ?> response
				= restTemplate.getForObject(getReviewsUrl , HashMap.class);

				obj.put("reviews", response);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
