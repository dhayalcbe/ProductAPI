package com.india.product.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.india.product.entity.Product;
import com.india.product.repository.ProductRepository;
import com.india.product.service.ProductService;
import com.india.product.service.dto.ProductDTO;
import com.india.product.service.dto.ResponseCustomDTO;
import com.india.product.service.dto.ReviewDTO;
import com.india.product.service.dto.ReviewGroupDTO;
import com.india.product.service.mapper.ProductMapper;

@Service
public class ProductImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	RestTemplate restTemplate;

	@Value("${review.create}")
	private String reviewSaveAPIURL;

	@Value("${review.grp.create}")
	private String reviewSaveGroupAPIURL;

	@Value("${review.get}")
	private String reviewGETAPIURL;

	/*
	 * Save And Update if id is null then its inserted new if id is not null then it
	 * was updated coressponding id dataO
	 */
	@Override
	public Product save(MultipartFile file, ProductDTO productDTO) throws IOException {
		try {
			productDTO.setImage(file.getBytes());
			return productRepository.save(productMapper.toEntity(productDTO));
		} catch (IOException e) {
			throw new IOException("Could not store file " + file.getOriginalFilename() + ". Please try again!", e);

		}
	}

	@Override
	public boolean restCallToReviewService(ProductDTO productDTO) {
		try {
			Product product = productRepository.findById(productDTO.getId()).orElse(null);
			long reviewGroupId = 0;
			if (product != null) {
				if (product.getReviewGroupId() == null)
					reviewGroupId = restTemplate.postForObject(reviewSaveGroupAPIURL,
							new ReviewGroupDTO(0L, product.getName(), product.getName()), long.class);
				else
					reviewGroupId = product.getReviewGroupId();
				if (reviewGroupId > 0) {
					restTemplate.postForObject(reviewSaveAPIURL,
							new ReviewDTO(0L, productDTO.getComments(), productDTO.getStarRating(), reviewGroupId),
							long.class);
					product.setReviewGroupId(reviewGroupId);
					productRepository.save(product);
					return true;
				}
			}
			return false;
		} catch (RestClientException e) {
			throw new RestClientException("Please Try Again !" + e);
		}
	}

	@Override
	public ProductDTO getProductDetails(Long productId) {
		try {
			Product product = productRepository.findById(productId).orElse(null);
			if (product != null) {
				Long reviewGrpId = product.getReviewGroupId();
				reviewGETAPIURL = reviewGETAPIURL + reviewGrpId;
				ResponseEntity<ResponseCustomDTO> result = restTemplate.exchange(reviewGETAPIURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ResponseCustomDTO>() {
						});
				result.getBody();
				return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getPrice(),
						product.getImage(), product.getReviewGroupId(), null, 0, result.getBody());
			}
		} catch (RestClientException e) {
			throw new RestClientException("Please Try Again !" + e);
		}
		return null;
	}
}
