package com.apicokeshop.cokeshop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apicokeshop.cokeshop.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategory(String category);
}
