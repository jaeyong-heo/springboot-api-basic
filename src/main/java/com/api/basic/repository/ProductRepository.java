package com.api.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.basic.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
