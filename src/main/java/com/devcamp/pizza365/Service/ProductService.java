package com.devcamp.pizza365.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devcamp.pizza365.EntityRespository.iProductLineRespository;
import com.devcamp.pizza365.EntityRespository.iProductRespository;
import com.devcamp.pizza365.entity.Product;
import com.devcamp.pizza365.entity.ProductLine;

@Service
public class ProductService {

    @Autowired
    iProductRespository iProduct;

    @Autowired
    iProductLineRespository iProductLine;

    // tìm all product
    public ResponseEntity<List<Product>> getProductsList() {
        try {
            List<Product> product = new ArrayList<Product>();
            iProduct.findAll().forEach(product::add);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tìm bằng id product
    public ResponseEntity<Object> getProductsById(Integer id) {

        Optional<Product> vProducts = iProduct.findById(id);

        if (vProducts.isPresent()) {
            return new ResponseEntity<>(vProducts.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // tìm id bằng product line id
    public ResponseEntity<List<Product>> getProductByProductLineId(Integer productLineId) {

        Optional<ProductLine> productLine = iProductLine.findById(productLineId);
        try {
            if (productLine.isPresent()) {
                return new ResponseEntity<List<Product>>(productLine.get().getProducts(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tạo mới product theo productLine
    public ResponseEntity<Object> createProduct(int id, Product product) {

        Optional<ProductLine> productLineData = iProductLine.findById(id);
        Optional<Product> productFound = iProduct.findById(product.getId());

        if (productLineData.isPresent()) {
            try {
                if (productFound.isPresent()) {
                    return ResponseEntity.unprocessableEntity().body(" product already exsit  ");
                }

                ProductLine productLineNew = productLineData.get();
                product.setProductLine(productLineNew);

                Product productSave = iProduct.save(product);

                return new ResponseEntity<Object>(productSave, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity()
                        .body("Can not execute operation about this Entity " + e.getCause().getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Failed to get specified id: " + id);
        }
    }

    // update product
    public ResponseEntity<Object> updateProduct(int id, Product product) {

        try {
            Optional<Product> newProduct = iProduct.findById(id);
            if (newProduct.isPresent()) {
                Product updateProduct = newProduct.get();
                updateProduct.setProductCode(product.getProductCode());
                updateProduct.setProductName(product.getProductName());
                updateProduct.setProductDescripttion(product.getProductDescripttion());
                updateProduct.setProductScale(product.getProductScale());
                updateProduct.setProductVendor(product.getProductVendor());
                updateProduct.setQuantityInStock(product.getQuantityInStock());
                updateProduct.setBuyPrice(product.getBuyPrice());

                return new ResponseEntity<>(iProduct.save(updateProduct), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // delete product
    public ResponseEntity<Object> deleteProduct(int id) {
        try {
            iProduct.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
