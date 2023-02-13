package com.devcamp.pizza365.EntityRespository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.entity.Employee;

public interface iEmployeeRespository extends JpaRepository <Employee , Integer> {
    
}
