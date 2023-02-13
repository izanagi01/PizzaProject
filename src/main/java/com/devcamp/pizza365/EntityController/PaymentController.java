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

import com.devcamp.pizza365.Service.PaymentService;
import com.devcamp.pizza365.entity.Payment;

@RestController
@RequestMapping("payment")
@CrossOrigin
public class PaymentController {
    
    @Autowired 
    PaymentService payService ;

    /* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách payment
	@GetMapping("service/all")
	public ResponseEntity <List<Payment>> getAllPayment(){
		ResponseEntity <List<Payment>> resul = payService.getPaymentsList();
			return resul ;
	}

	// tìm payment by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getPaymentById(@PathVariable Integer id){
		ResponseEntity <Object> resul = payService.getPaymentById(id);
        return resul;
	}

	// tìm payment by customer id
	@GetMapping("service/customerId/{id}")
	public ResponseEntity<List<Payment>> getPaymentByIdCustomer(@PathVariable Integer id){
		ResponseEntity<List<Payment>> resul = payService.getPaymentsByCustomerId(id);
		return resul;
	}

	// tạo mới payment
	@PostMapping("service/create/{id}")
	public ResponseEntity <Object> createPaymentByService( @PathVariable int id, @RequestBody Payment payment){
	    ResponseEntity <Object> resul = payService.createPayment( id,payment);
        return resul;
	}

	// update payment 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updatePaymentByService(@PathVariable int id  , @RequestBody Payment payment){
		ResponseEntity <Object> resul = payService.updatePayment( id,payment);
        return resul;
	}

	// delete payment
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deletePaymentByService(@PathVariable int id){
		ResponseEntity <Object> resul = payService.deletePayment( id);
        return resul;
	}
}
