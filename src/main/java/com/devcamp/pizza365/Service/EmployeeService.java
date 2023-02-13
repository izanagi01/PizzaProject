package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.iEmployeeRespository;
import com.devcamp.pizza365.entity.Employee;

@Service
public class EmployeeService {
    
    @Autowired
    iEmployeeRespository iEmployee ;

     // tìm  all employee
     public ResponseEntity <List<Employee>> getEmployeesList() {
        try {
			List<Employee> employee = new ArrayList<Employee>();
			iEmployee.findAll().forEach(employee::add);
            return new ResponseEntity<>(employee, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }


    // tìm bằng id employee 
    public ResponseEntity<Object> getEmployeesById(Integer id) {
        
			Optional<Employee> employee = iEmployee.findById(id);
			
		if(employee.isPresent()){
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

	
    }

    
    // tạo mới employee cách đơn giản
    public ResponseEntity<Object> createEmployee(Employee employee) {
        try {
            Optional<Employee> newEmployee = iEmployee.findById(employee.getId());
			
           if(newEmployee.isPresent()){
            return ResponseEntity.unprocessableEntity().body(" employee already exsit  ");
           }
           else {
                return new ResponseEntity<>(iEmployee.save(employee) , HttpStatus.CREATED) ;
           }
        } catch (Exception e) {
            System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
				.body("Failed to Create specified Album: " + e.getCause().getCause().getMessage());

        }
    }


    // update employee 
    public ResponseEntity<Object> updateEmployee(int id , Employee employee) {
      
        try {
            Optional<Employee> newEmployee = iEmployee.findById(id);
            if(newEmployee.isPresent()){
                Employee updateEmployee = newEmployee.get() ;
                    updateEmployee.setFirstName(employee.getFirstName());
                    updateEmployee.setLastName(employee.getLastName());
                    updateEmployee.setExtension(employee.getExtension());
                    updateEmployee.setEmail(employee.getEmail());
                    updateEmployee.setOfficeCode(employee.getOfficeCode());
                    updateEmployee.setJobTitle(employee.getJobTitle());
                    updateEmployee.setReportTo(employee.getReportTo());
        
                    return new ResponseEntity<>(iEmployee.save(updateEmployee) , HttpStatus.OK) ;
               }
               else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
               }
            } 
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }         
    }

    // delete employee
    public ResponseEntity<Object> deleteEmployee (int id ){
        try {
            iEmployee.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
