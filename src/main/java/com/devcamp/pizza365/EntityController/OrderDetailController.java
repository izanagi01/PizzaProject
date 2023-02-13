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

import com.devcamp.pizza365.Service.OrderDetailService;
import com.devcamp.pizza365.entity.OrderDetail;

@RestController
@RequestMapping("orderDetail")
@CrossOrigin
public class OrderDetailController {
    
    @Autowired
    OrderDetailService orderDetailSer ;

        /* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách order detail 
	@GetMapping("service/all")
	public ResponseEntity <List<OrderDetail>> getAllOrderDetails(){
		ResponseEntity <List<OrderDetail>> resul = orderDetailSer.getOrderDetailList();
			return resul ;
	}

	// tìm order detail by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getOrderDetailsById(@PathVariable Integer id){
		ResponseEntity <Object> resul = orderDetailSer.getOrderDetailById(id);
        return resul;
	}

	// tìm order detail by order id 
	@GetMapping("service/orderId/{id}")
	public ResponseEntity<List<OrderDetail>> getOrderDetailsByIdOrder(@PathVariable Integer id){
		ResponseEntity<List<OrderDetail>> resul = orderDetailSer.getOrderDetailByOrderId(id);
		return resul;
	}

	// tìm order detail by product id 
	@GetMapping("service/productId/{id}")
	public ResponseEntity<List<OrderDetail>> getOrderDetailsByIdProduct(@PathVariable Integer id){
		ResponseEntity<List<OrderDetail>> resul = orderDetailSer.getOrderDetailByProductId(id);
		return resul;
	}

	// tạo mới order detail
	@PostMapping("service/create/{orderId}/{productId}")
	public ResponseEntity <Object> createOrderDetailByService(@PathVariable int orderId , @PathVariable int productId , @RequestBody OrderDetail orderDetail){
	    ResponseEntity <Object> resul = orderDetailSer.createOrderDetail(orderId , productId , orderDetail);
        return resul;
	}
 
	// update order  detail
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateOrderDetailByService(@PathVariable int id  , @RequestBody OrderDetail orderDetail){
		ResponseEntity <Object> resul = orderDetailSer.updateOrderDetail( id,orderDetail);
        return resul;
	}
	
	// delete order detail
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteOrderDetailByService(@PathVariable int id){
		ResponseEntity <Object> resul = orderDetailSer.deleteOrderDetail( id);
        return resul;
	}
}
