package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.iOrderDetailRespository;
import com.devcamp.pizza365.EntityRespository.iOrderRespository;
import com.devcamp.pizza365.EntityRespository.iProductRespository;
import com.devcamp.pizza365.entity.Order;
import com.devcamp.pizza365.entity.OrderDetail;
import com.devcamp.pizza365.entity.Product;

@Service
public class OrderDetailService {
    
    @Autowired
    iOrderRespository iOrder ;

    @Autowired
    iProductRespository iProduct ;

    @Autowired
    iOrderDetailRespository iOrderDetail ;

       // tìm  all order detail
       public ResponseEntity <List<OrderDetail>> getOrderDetailList() {
        try {
			List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
			iOrderDetail.findAll().forEach(orderDetail::add);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm bằng id order detail 
    public ResponseEntity<Object> getOrderDetailById(Integer id) {
        
			Optional<OrderDetail> order = iOrderDetail.findById(id);
			
		if(order.isPresent()){
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // tìm id bằng order id 
    public ResponseEntity<List<OrderDetail>> getOrderDetailByOrderId( Integer orderId){

        Optional<Order> order = iOrder.findById(orderId);
    try{
        if(order.isPresent()){
            return new ResponseEntity<List<OrderDetail>>(order.get().getOrderDetails(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    } 
    catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // tìm id bằng product id 
    public ResponseEntity<List<OrderDetail>> getOrderDetailByProductId( Integer productId){

        Optional<Product> product = iProduct.findById(productId);
    try{
        if(product.isPresent()){
            return new ResponseEntity<List<OrderDetail>>(product.get().getOrderDetails(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    } 
    catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // tạo mới order detail theo orderId và productId
    public ResponseEntity<Object> createOrderDetail(int orderId ,int productId,OrderDetail orderDetail) {
        Optional<Order> orderData = iOrder.findById(orderId);
        Optional<Product> productData = iProduct.findById(productId);
        Optional<OrderDetail> orderDetailFound = iOrderDetail.findById(orderDetail.getId());

        if(orderData.isPresent() && productData.isPresent()) {
            try {
                if(orderDetailFound.isPresent()){
                    return ResponseEntity.unprocessableEntity().body(" order already exsit  ");
                }
        
        Order orderNew = orderData.get();
        Product productNew = productData.get();
        orderDetail.setProduct(productNew);
        orderDetail.setOrder(orderNew);
          
        OrderDetail orderDetailSave = iOrderDetail.save(orderDetail);

            return new ResponseEntity<Object>(orderDetailSave, HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body("Can not execute operation about this Entity " +e.getCause().getMessage());
        }
    }    
        else {
                 return ResponseEntity.badRequest().body("Failed to get specified id: " + orderId + productId);
             }
    }


    // update order detail
    public ResponseEntity<Object> updateOrderDetail(int id , OrderDetail orderDetail) {
      
        try {
            Optional<OrderDetail> newOrderDetail = iOrderDetail.findById(id);
            if(newOrderDetail.isPresent()){
                OrderDetail updateOrderDetail = newOrderDetail.get() ;
                        updateOrderDetail.setPriceEach(orderDetail.getPriceEach());
                        updateOrderDetail.setQuantityOrder(orderDetail.getQuantityOrder());
                        
                    return new ResponseEntity<>(iOrderDetail.save(updateOrderDetail) , HttpStatus.OK) ;
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

    // delete order detail
    public ResponseEntity<Object> deleteOrderDetail (int id ){
        try {
            iOrderDetail.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
