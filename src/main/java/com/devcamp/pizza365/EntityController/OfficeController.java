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

import com.devcamp.pizza365.Service.OfficeService;
import com.devcamp.pizza365.entity.Office;

@RestController
@RequestMapping("office")
@CrossOrigin
public class OfficeController {
    
    @Autowired
    OfficeService officeSer ;

    /* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách office
	@GetMapping("service/all")
	public ResponseEntity <List<Office>> getAllOffice(){
		ResponseEntity <List<Office>> resul = officeSer.getOfficesList();
			return resul ;
	}

	// tìm office by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getOfficeById(@PathVariable Integer id){
		ResponseEntity <Object> resul = officeSer.getOfficesById(id);
        return resul;
	}

	// tạo mới office
	@PostMapping("service/create")
	public ResponseEntity <Object> createOfficeByService(@RequestBody Office office){
	    ResponseEntity <Object> resul = officeSer.createOffice(office);
        return resul;
	}

	// update office 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateOfficeByService(@PathVariable int id  , @RequestBody Office office){
		ResponseEntity <Object> resul = officeSer.updateOffice( id,office);
        return resul;
    }

	// delete office
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteOfficeByService(@PathVariable int id){
		ResponseEntity <Object> resul = officeSer.deleteOffice( id);
        return resul;
	}
}
