package com.zirius.productAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zirius.productAPI.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

}
