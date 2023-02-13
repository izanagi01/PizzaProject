package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.OrderDetail;

public interface iOrderDetailRespository extends JpaRepository <OrderDetail , Integer> {
    

}
