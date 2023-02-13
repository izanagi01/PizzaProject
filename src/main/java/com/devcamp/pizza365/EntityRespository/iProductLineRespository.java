package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.ProductLine;

public interface iProductLineRespository extends JpaRepository <ProductLine , Integer> {
    
}
