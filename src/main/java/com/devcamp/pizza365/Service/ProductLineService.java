package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.iProductLineRespository;
import com.devcamp.pizza365.entity.ProductLine;

@Service
public class ProductLineService {
    
    @Autowired
    iProductLineRespository iProductLine ;

      // tìm  all productLine
      public ResponseEntity <List<ProductLine>> getProductLinesList() {
        try {
			List<ProductLine> productLine = new ArrayList<ProductLine>();
			iProductLine.findAll().forEach(productLine::add);
            return new ResponseEntity<>(productLine, HttpStatus.OK);
    }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // tìm bằng id productLine 
    public ResponseEntity<Object> getProductLinesById(Integer id) {
        
			Optional<ProductLine> productLine = iProductLine.findById(id);
			
		if(productLine.isPresent()){
            return new ResponseEntity<>(productLine.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

	
    }

    // tạo mới productLine cách đơn giản
    public ResponseEntity<Object> createProductLine(ProductLine productLine) {
        try {
            Optional<ProductLine> newProductLine = iProductLine.findById(productLine.getId());
			
           if(newProductLine.isPresent()){
            return ResponseEntity.unprocessableEntity().body(" ProductLine already exsit  ");
           }
           else {
                return new ResponseEntity<>(iProductLine.save(productLine) , HttpStatus.CREATED) ;
           }
        } catch (Exception e) {
            System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
				.body("Failed to Create specified Album: " + e.getCause().getCause().getMessage());

        }
    }


    // update productLine 
    public ResponseEntity<Object> updateProductLine(int id , ProductLine productLine) {
      
        try {
            Optional<ProductLine> newProductLine = iProductLine.findById(id);
            if(newProductLine.isPresent()){
                ProductLine updateProductLine = newProductLine.get() ;
                    updateProductLine.setDescription(productLine.getDescription());
                    updateProductLine.setProductLine(productLine.getProductLine());
             
                    return new ResponseEntity<>(iProductLine.save(updateProductLine) , HttpStatus.OK) ;
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


    // delete productLine
    public ResponseEntity<Object> deleteProductLine (int id ){
        try {
            iProductLine.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
