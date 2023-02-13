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
import com.devcamp.pizza365.Service.EmployeeService;
import com.devcamp.pizza365.entity.Employee;

@RestController
@RequestMapping("employee")
@CrossOrigin
public class EmployeesController {

    @Autowired
    EmployeeService employSer;

   	/* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách employee
	@GetMapping("service/all")
	public ResponseEntity <List<Employee>> getAllEmployee(){
		ResponseEntity <List<Employee>> resul = employSer.getEmployeesList();
			return resul ;
	}

	// tìm employee by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getEmployeeById(@PathVariable Integer id){
		ResponseEntity <Object> resul = employSer.getEmployeesById(id);
        return resul;
	}

	// tạo mới employee
	@PostMapping("service/create")
	public ResponseEntity <Object> createEmployeeByService(@RequestBody Employee employee){
	    ResponseEntity <Object> resul = employSer.createEmployee(employee);
        return resul;
	}

	// update employee 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateEmployeeByService(@PathVariable int id  , @RequestBody Employee employee){
		ResponseEntity <Object> resul = employSer.updateEmployee( id,employee);
        return resul;
	}
    
	// delete employee
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteEmployeeByService(@PathVariable int id){
		ResponseEntity <Object> resul = employSer.deleteEmployee( id);
        return resul;
	}
}
