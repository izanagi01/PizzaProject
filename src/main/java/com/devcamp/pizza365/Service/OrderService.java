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
import com.devcamp.pizza365.EntityRespository.iOrderRespository;
import com.devcamp.pizza365.entity.Customer;
import com.devcamp.pizza365.entity.Order;

@Service
public class OrderService {
    
    @Autowired
    iOrderRespository iOrder ;

    @Autowired
    ICustomerRepository iCus ;

      // tìm  all order
      public ResponseEntity <List<Order>> getOrdersList() {
        try {
			List<Order> order = new ArrayList<Order>();
			iOrder.findAll().forEach(order::add);
            return new ResponseEntity<>(order, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm bằng id order 
    public ResponseEntity<Object> getOrderById(Integer id) {
        
			Optional<Order> order = iOrder.findById(id);
			
		if(order.isPresent()){
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

	
    }

    // tạo mới order theo customer id
    public ResponseEntity<Object> createOrder( int id ,Order order) {
        Optional<Customer> cusData = iCus.findById(id);
        Optional<Order> orderFound = iOrder.findById(order.getId());

        if(cusData.isPresent()){
            try {
                if(orderFound.isPresent()){
                    return ResponseEntity.unprocessableEntity().body(" order already exsit  ");
                }
        
        Customer cusNew = cusData.get();
        order.setCustomer(cusNew);
        order.setOrderDate(new Date());
        order.setRequiredDate(new Date());
        order.setShippedDate(null);       
        Order orderSave = iOrder.save(order);

            return new ResponseEntity<Object>(orderSave, HttpStatus.CREATED);
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

    // tìm order bằng customer id
    public ResponseEntity<List<Order>> getOrdersByCustomerId(int id){
        try {
            Optional<Customer> cus = iCus.findById(id);
            if(cus.isPresent()){
                return new ResponseEntity<List<Order>>(cus.get().getOrders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    // update order 
    public ResponseEntity<Object> updateOrder(int id , Order order) {
      
        try {
            Optional<Order> newOrder = iOrder.findById(id);
            if(newOrder.isPresent()){
                Order updateOrder = newOrder.get() ;

                    updateOrder.setStatus(order.getStatus());
                    updateOrder.setComments(order.getComments());
                    updateOrder.setRequiredDate(order.getRequiredDate());
                    updateOrder.setShippedDate(order.getShippedDate());       

                    return new ResponseEntity<>(iOrder.save(updateOrder) , HttpStatus.OK) ;
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

    // delete order
    public ResponseEntity<Object> deleteOrder (int id ){
        try {
            iOrder.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm order bằng last name
    public ResponseEntity<List<Order>> findOrderByLastName(String lastName){
        try {
            Optional<Customer> cus = iCus.findByLastName(lastName);
            if(cus.isPresent()){
                return new ResponseEntity<List<Order>>(cus.get().getOrders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     // tìm order bằng first name
     public ResponseEntity<List<Order>> findOrderByFirstName(String firstName){
        try {
            Optional<Customer> cus = iCus.findByFirstName(firstName);
            if(cus.isPresent()){
                return new ResponseEntity<List<Order>>(cus.get().getOrders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

      // tìm order bằng last name và first name
      public ResponseEntity<List<Order>> findOrderByLastNameAndFirstName(String lastName , String firstName){
        try {
            Optional<Customer> cus = iCus.findByLastNameAndFirstName(lastName ,firstName);
            if(cus.isPresent()){
                return new ResponseEntity<List<Order>>(cus.get().getOrders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
