package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.Order;

public interface iOrderRespository extends JpaRepository <Order , Integer> {
    
}
