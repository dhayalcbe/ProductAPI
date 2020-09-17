package com.zirius.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zirius.product.repository.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
