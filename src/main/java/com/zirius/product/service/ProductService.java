package com.zirius.product.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.zirius.product.dto.ProductDTO;
import com.zirius.product.dto.ProductDetailsDTO;
import com.zirius.product.dto.ProductDetailsResponse;
import com.zirius.product.dto.ProductResponse;
import com.zirius.product.dto.ProductReviewDTO;
import com.zirius.product.dto.ProductReviewResponse;
import com.zirius.product.dto.ProductReviewsDTO;
import com.zirius.product.error.ErrorDetails;
import com.zirius.product.mapper.ProductMapper;
import com.zirius.product.repository.ProductRepository;
import com.zirius.product.repository.model.Product;
import com.zirius.product.review.dto.AverageRatingResponse;
import com.zirius.product.review.dto.ReviewDTO;
import com.zirius.product.review.dto.ReviewResponse;
import com.zirius.product.review.dto.ReviewsDTO;
import com.zirius.product.reviewgroup.dto.ReviewGroup;
import com.zirius.product.reviewgroup.dto.ReviewGroupResponse;
import com.zirius.product.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public ResponseEntity<ProductResponse> createProduct(ProductDTO productDTO) {
		log.info("START:> createProduct");
		ProductResponse productResponse;
		log.debug("START of Rest template:> Calling Review API");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ReviewGroupResponse> reviewGroupResponseEntity = restTemplate.postForEntity(
				Constants.REVIEW_GROUP_ENDPOINT, ReviewGroup.builder().topic(productDTO.getName()).build(),
				ReviewGroupResponse.class);
		ReviewGroupResponse reviewGroupResponse = reviewGroupResponseEntity.getBody();
		log.debug("END of Rest template:> Review API");
		if (null != reviewGroupResponse && reviewGroupResponseEntity.getStatusCodeValue() == 201) {
			Product product = productMapper.toProduct(productDTO);
			product.setReviewGroupId(reviewGroupResponse.getReviewGroupId());
			Product savedProduct = productRepository.save(product);
			productResponse = setProductResponse(HttpStatus.CREATED, Constants.PRODUCT_CREATED_SUCCESSFULLY,
					productMapper.toProductDTO(savedProduct), null);
		} else {
			productResponse = setProductResponse(HttpStatus.INTERNAL_SERVER_ERROR, Constants.PRODUCT_CREATION_FAILED,
					null, ErrorDetails.builder().errorMessage(Constants.FAILED_TO_CREATE_REVIEW_GROUP).build());
		}
		log.info("END:> createProduct");
		return ResponseEntity.status(productResponse.getStatus()).body(productResponse);
	}

	@Transactional
	public ResponseEntity<ProductResponse> updateProduct(Long id, ProductDTO productDTO) {
		log.info("START:> updateProduct");
		ProductResponse productResponse;
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productMapper.toProduct(product.get(), productDTO);
			Product savedProduct = productRepository.save(product.get());
			productResponse = setProductResponse(HttpStatus.OK, Constants.PRODUCT_UPDATED_SUCCESSFULLY,
					productMapper.toProductDTO(savedProduct), null);
		} else {
			productResponse = setProductResponse(HttpStatus.BAD_REQUEST, Constants.PRODUCT_UPDATE_FAILED, null,
					ErrorDetails.builder().errorMessage(Constants.NO_PRODUCT_FOUND_FOR_GIVEN_ID).build());
		}
		log.info("END:> updateProduct");
		return ResponseEntity.status(productResponse.getStatus()).body(productResponse);
	}

	@Transactional
	public ResponseEntity<ProductReviewResponse> createReviews(Long id, ProductReviewsDTO productReviewsDTO) {
		log.info("START:> createReviews");
		ProductReviewResponse productReviewResponse;
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			List<ReviewDTO> reviewDTOs = productMapper.toReviewDTOs(product.get().getReviewGroupId(),
					productReviewsDTO.getReviews());
			ReviewsDTO reviewsDTO = ReviewsDTO.builder().reviews(reviewDTOs).build();
			log.debug("START of rest template:> Create reviews using Review API");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ReviewResponse> reviewResponseEntity = restTemplate.postForEntity(Constants.REVIEW_ENDPOINT,
					reviewsDTO, ReviewResponse.class);
			log.debug("END of rest template:> Response received from Review API");
			ReviewResponse reviewResponse = reviewResponseEntity.getBody();
			if (null != reviewResponse && reviewResponseEntity.getStatusCodeValue() == 201) {
				productReviewResponse = setProductReviewResponse(HttpStatus.CREATED,
						Constants.REVIEWS_CREATED_SUCCESSFULLY,
						productMapper.toProductReviewDTOs(reviewResponse.getReviews()), null);
			} else {
				productReviewResponse = setProductReviewResponse(HttpStatus.INTERNAL_SERVER_ERROR,
						Constants.FAILED_TO_CREATE_REVIEWS, null,
						ErrorDetails.builder().errorMessage(Constants.FAILED_TO_CREATE_REVIEWS).build());
			}

		} else {
			productReviewResponse = setProductReviewResponse(HttpStatus.BAD_REQUEST, Constants.FAILED_TO_CREATE_REVIEWS,
					null, ErrorDetails.builder().errorMessage(Constants.NO_PRODUCT_FOUND_FOR_GIVEN_ID).build());
		}
		log.info("END:> createReviews");
		return ResponseEntity.status(productReviewResponse.getStatus()).body(productReviewResponse);

	}

	public ResponseEntity<ProductDetailsResponse> getProductDetails(Long id) {
		log.info("START:> getProductDetails");
		ProductDetailsResponse productDetailsResponse;
		ProductDetailsDTO productDetailsDTO = null;
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productDetailsDTO = productMapper.toProductDetailsDTO(product.get());
			Long reviewGroupId = product.get().getReviewGroupId();
			ReviewResponse reviewResponse = retrieveReviews(reviewGroupId);
			productDetailsDTO.setReviews(productMapper.toProductReviewDTOs(reviewResponse.getReviews()));
			AverageRatingResponse averageRatingResponse = retrieveAverageRating(reviewGroupId);
			productDetailsDTO.setAverageRating(averageRatingResponse.getAverageRating());
			productDetailsResponse = setProductDetailsResponse(HttpStatus.OK,
					Constants.PRODUCT_DETAILS_RETRIEVED_SUCCESSFULLY, productDetailsDTO, null);
		} else {
			productDetailsResponse = setProductDetailsResponse(HttpStatus.BAD_REQUEST,
					Constants.FAILED_TO_RETRIEVE_PRODUCT_DETAILS, null,
					ErrorDetails.builder().errorMessage(Constants.NO_PRODUCT_FOUND_FOR_GIVEN_ID).build());
		}
		log.info("END:> getProductDetails");
		return ResponseEntity.status(productDetailsResponse.getStatus()).body(productDetailsResponse);
	}

	private ReviewResponse retrieveReviews(Long reviewGroupId) {
		log.debug("START of Rest Template:> Retrieving reviews from Review API");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder reviewURIBuilder = UriComponentsBuilder.fromHttpUrl(Constants.REVIEW_ENDPOINT)
				.queryParam(Constants.REVIEW_GROUP_ID, reviewGroupId);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<ReviewResponse> reviewResponseEntity = restTemplate.exchange(reviewURIBuilder.toUriString(),
				HttpMethod.GET, entity, ReviewResponse.class);
		log.debug("END of Rest Template:> Got response from Review API");
		return reviewResponseEntity.getBody();
	}

	private AverageRatingResponse retrieveAverageRating(Long reviewGroupId) {
		log.debug("START of Rest Template:> Retrieving average rating from Review API");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder reviewURIBuilder = UriComponentsBuilder.fromHttpUrl(Constants.AVERAGE_REVIEW_ENDPOINT)
				.queryParam(Constants.REVIEW_GROUP_ID, reviewGroupId);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<AverageRatingResponse> reviewResponseEntity = restTemplate
				.exchange(reviewURIBuilder.toUriString(), HttpMethod.GET, entity, AverageRatingResponse.class);
		log.debug("END of Rest Template:> Got response from Review API");
		return reviewResponseEntity.getBody();
	}

	private ProductResponse setProductResponse(HttpStatus status, String message, ProductDTO products,
			ErrorDetails errors) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setStatus(status);
		productResponse.setMessage(message);
		productResponse.setProduct(products);
		productResponse.setErrors(errors);
		return productResponse;
	}

	private ProductReviewResponse setProductReviewResponse(HttpStatus status, String message,
			List<ProductReviewDTO> reviews, ErrorDetails errors) {
		ProductReviewResponse productReviewResponse = new ProductReviewResponse();
		productReviewResponse.setStatus(status);
		productReviewResponse.setMessage(message);
		productReviewResponse.setReviews(reviews);
		productReviewResponse.setErrors(errors);
		return productReviewResponse;
	}

	private ProductDetailsResponse setProductDetailsResponse(HttpStatus status, String message,
			ProductDetailsDTO productDetailsDTO, ErrorDetails errors) {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
		productDetailsResponse.setStatus(status);
		productDetailsResponse.setMessage(message);
		productDetailsResponse.setProducts(productDetailsDTO);
		productDetailsResponse.setErrors(errors);
		return productDetailsResponse;
	}
}
