package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.ICustomerRepository;
import com.devcamp.pizza365.entity.Customer;



@Service
public class CustomerService {
    
    @Autowired
    ICustomerRepository iCustomer ;

    // tìm  all customer
    public ResponseEntity <List<Customer>> getCustomersList() {
        try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			iCustomer.findAll().forEach(vCustomers::add);
            return new ResponseEntity<>(vCustomers, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm bằng id customer 

    public ResponseEntity<Object> getCustomersById(Integer id) {
        
			Optional<Customer> vCustomers = iCustomer.findById(id);
			
		if(vCustomers.isPresent()){
            return new ResponseEntity<>(vCustomers.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

	
    }

    // tạo mới customer cách đơn giản
    public ResponseEntity<Object> createCustomer(Customer customer) {
        try {
            Optional<Customer> newCustomer = iCustomer.findById(customer.getId());
			
           if(newCustomer.isPresent()){
            return ResponseEntity.unprocessableEntity().body(" customer already exsit  ");
           }
           else {
                return new ResponseEntity<>(iCustomer.save(customer) , HttpStatus.CREATED) ;
           }
        } catch (Exception e) {
            System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
				.body("Failed to Create specified Album: " + e.getCause().getCause().getMessage());

        }
    }


    // update customer 
    public ResponseEntity<Object> updateCustomer(int id , Customer customer) {
      
        try {
            Optional<Customer> newCustomer = iCustomer.findById(id);
            if(newCustomer.isPresent()){
                Customer updateCustomer = newCustomer.get() ;
                    updateCustomer.setLastName(customer.getLastName());
                    updateCustomer.setFirstName(customer.getFirstName());
                    updateCustomer.setPhoneNumber(customer.getPhoneNumber());
                    updateCustomer.setAddress(customer.getAddress());
                    updateCustomer.setCity(customer.getCity());
                    updateCustomer.setState(customer.getCity());
                    updateCustomer.setPostalCode(customer.getPostalCode());
                    updateCustomer.setCountry(customer.getCountry());
                    updateCustomer.setSalesRepEmployeeNumber(customer.getSalesRepEmployeeNumber());
                    updateCustomer.setCreditLimit(customer.getCreditLimit());
        
                    return new ResponseEntity<>(iCustomer.save(updateCustomer) , HttpStatus.OK) ;
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

    // delete customer
    public ResponseEntity<Object> deleteCustomer (Integer id ){
        try {
            iCustomer.deleteById(id);
        
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
