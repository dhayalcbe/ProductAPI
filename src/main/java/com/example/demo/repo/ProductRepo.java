package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public interface  ProductRepo extends JpaRepository<Product, Long>{

}
