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
import com.devcamp.pizza365.Service.ProductLineService;
import com.devcamp.pizza365.entity.ProductLine;

@RestController
@RequestMapping("productline")
@CrossOrigin
public class ProductLineController {
    
    @Autowired
    ProductLineService productLineSer ;

    
	/* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách productline
	@GetMapping("service/all")
	public ResponseEntity <List<ProductLine>> getAllProductLine(){
		ResponseEntity <List<ProductLine>> resul = productLineSer.getProductLinesList();
			return resul ;
	}

	// tìm productline by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getProductLineById(@PathVariable Integer id){
		ResponseEntity <Object> resul = productLineSer.getProductLinesById(id);
        return resul;
	}

	// tạo mới productline
	@PostMapping("service/create")
	public ResponseEntity <Object> createProductLineByService(@RequestBody ProductLine productLine){
	    ResponseEntity <Object> resul = productLineSer.createProductLine(productLine);
        return resul;
	}

	// update productline 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateProductLineByService(@PathVariable int id  , @RequestBody ProductLine productLine){
		ResponseEntity <Object> resul = productLineSer.updateProductLine( id,productLine);
        return resul;
	}
	
	// delete productline
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteProductLineByService(@PathVariable int id){
		ResponseEntity <Object> resul = productLineSer.deleteProductLine( id);
        return resul;
	}
}


