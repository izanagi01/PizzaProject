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
import com.devcamp.pizza365.Service.ProductService;
import com.devcamp.pizza365.entity.Product;


@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {
    

    @Autowired
    ProductService productSer;


    
	/* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách product
	@GetMapping("service/all")
	public ResponseEntity <List<Product>> getAllProduct(){
		ResponseEntity <List<Product>> resul = productSer.getProductsList();
			return resul ;
	}

	// tìm product by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getProductById(@PathVariable Integer id){
		ResponseEntity <Object> resul = productSer.getProductsById(id);
        return resul;
	}

	// tìm product by product line id 
	@GetMapping("service/productlineId/{id}")
	public ResponseEntity<List<Product>> getProductByIdProductLine(@PathVariable Integer id){
		ResponseEntity<List<Product>> resul = productSer.getProductByProductLineId(id);
		return resul;
	}

	// tạo mới product
	@PostMapping("service/create/{id}")
	public ResponseEntity <Object> createProductByService(@PathVariable Integer id , @RequestBody Product product){
	    ResponseEntity <Object> resul = productSer.createProduct( id , product);
        return resul;
	}

	// update product 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateProductByService(@PathVariable int id  , @RequestBody Product product){
		ResponseEntity <Object> resul = productSer.updateProduct( id,product);
        return resul;
	}
	
	// delete product
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteProductByService(@PathVariable int id){
		ResponseEntity <Object> resul = productSer.deleteProduct( id);
        return resul;
	}
}
