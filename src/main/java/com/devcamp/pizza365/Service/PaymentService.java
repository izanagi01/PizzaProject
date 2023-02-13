package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.ICustomerRepository;
import com.devcamp.pizza365.EntityRespository.iPaymentRespository;
import com.devcamp.pizza365.entity.Customer;
import com.devcamp.pizza365.entity.Payment;

@Service
public class PaymentService {
    
    @Autowired 
    iPaymentRespository iPay ;

    @Autowired
    ICustomerRepository iCus;

     // tìm  all payments
     public ResponseEntity <List<Payment>> getPaymentsList() {
        try {
			List<Payment> payments = new ArrayList<Payment>();
			iPay.findAll().forEach(payments::add);
            return new ResponseEntity<>(payments, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm bằng id payments 

    public ResponseEntity<Object> getPaymentById(Integer id) {
        
			Optional<Payment> payments = iPay.findById(id);
			
		if(payments.isPresent()){
            return new ResponseEntity<>(payments.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // tìm payments bằng customer id
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(int id){
        try {
            Optional<Customer> cus = iCus.findById(id);
            if(cus.isPresent()){
                return new ResponseEntity<List<Payment>>(cus.get().getPayments(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // tạo mới payments theo customer id
    public ResponseEntity<Object> createPayment( int id ,Payment payment) {
        Optional<Customer> cusData = iCus.findById(id);
        Optional<Payment> payFound = iPay.findById(payment.getId());

        if(cusData.isPresent()){
            try {
                if(payFound.isPresent()){
                    return ResponseEntity.unprocessableEntity().body(" order already exsit  ");
                }
        
        Customer cusNew = cusData.get();
        payment.setCustomer(cusNew);
        payment.setPaymentDate(new Date());
        
        Payment paySave = iPay.save(payment);

            return new ResponseEntity<Object>(paySave, HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body("Can not execute operation about this Entity " +e.getCause().getMessage());
        }
    }    
        else {
                 return ResponseEntity.badRequest().body("Failed to get specified id: " + id);
             }
    }


    // update payment 
    public ResponseEntity<Object> updatePayment(int id , Payment payment) {
      
        try {
            Optional<Payment> newPay = iPay.findById(id);
            if(newPay.isPresent()){
                Payment updatePay = newPay.get() ;
                    updatePay.setCheckNumber(payment.getCheckNumber());
                    updatePay.setAmmount(payment.getAmmount());
                    
                    return new ResponseEntity<>(iPay.save(updatePay) , HttpStatus.OK) ;
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

    // delete payment
    public ResponseEntity<Object> deletePayment (int id ){
        try {
            iPay.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
