package com.devcamp.pizza365.EntityController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.pizza365.Service.OrderService;
import com.devcamp.pizza365.entity.Order;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {
    
    @Autowired
    OrderService orderSer ;

    /* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách order
	@GetMapping("service/all")
	public ResponseEntity <List<Order>> getAllOrder(){
		ResponseEntity <List<Order>> resul = orderSer.getOrdersList();
			return resul ;
	}

	// tìm order by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getOrderById(@PathVariable Integer id){
		ResponseEntity <Object> resul = orderSer.getOrderById(id);
        return resul;
	}

	// tìm order by customer id
	@GetMapping("service/customerId/{id}")
	public ResponseEntity<List<Order>> getOrderByIdCustomer(@PathVariable Integer id){
		ResponseEntity<List<Order>> resul = orderSer.getOrdersByCustomerId(id);
        return resul;
	}

	// tìm order by lastname 
	@GetMapping("service/customer/{lastname}")
	public ResponseEntity<List<Order>> findOrderByLastNameCustomer(@PathVariable String lastname ){
		ResponseEntity<List<Order>> resul = orderSer.findOrderByLastName(lastname );
        return resul;
	}

	// tìm order by firstname 
	@GetMapping("service/customer1/{firstname}")
	public ResponseEntity<List<Order>> findOrderByFirstNameCustomer(@PathVariable String firstname ){
		ResponseEntity<List<Order>> resul = orderSer.findOrderByFirstName(firstname);
        return resul;
	}

	// tìm order by lastname và firstname
	@GetMapping("service/customer2/{lastname}/{firstname}")
	public ResponseEntity<List<Order>> findOrderByLastNameAndFirstNameCustomer(@PathVariable String lastname ,@PathVariable String firstname ){
		ResponseEntity<List<Order>> resul = orderSer.findOrderByLastNameAndFirstName(lastname , firstname);
        return resul;
	}
	
	// tạo mới order
	@PostMapping("service/create/{id}")
	public ResponseEntity <Object> createOrderByService(@PathVariable int id , @RequestBody Order Order){
	    ResponseEntity <Object> resul = orderSer.createOrder(id , Order);
        return resul;
	}

	// update order 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateOrderByService(@PathVariable int id  , @RequestBody Order Order){
		ResponseEntity <Object> resul = orderSer.updateOrder( id,Order);
        return resul;
	}
	
	// delete order
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteOrderByService(@PathVariable int id){
		ResponseEntity <Object> resul = orderSer.deleteOrder( id);
        return resul;
	}
}
