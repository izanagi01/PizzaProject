package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.Office;

public interface iOfficeRespository extends JpaRepository <Office , Integer> {
    
}
