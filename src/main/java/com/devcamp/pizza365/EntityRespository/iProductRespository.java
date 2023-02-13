package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.Product;

public interface iProductRespository extends JpaRepository <Product , Integer> {
    
}
