package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ProductReviewComposite;

public interface ProductCompositeRepo  extends JpaRepository<ProductReviewComposite, Long> {
	@Query("SELECT c.reviewId FROM ProductReviewComposite AS c where c.productID=:productID" )
	List<Integer> findReviewIdByproductID( Long productID);
}
