package com.devcamp.pizza365.EntityController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.devcamp.pizza365.EntityRespository.ICustomerRepository;
import com.devcamp.pizza365.Service.CustomerService;
import com.devcamp.pizza365.entity.Customer;


@RestController
@CrossOrigin
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@GetMapping("/last-name/{lastName}")
	public ResponseEntity<List<Customer>> getCustomersByLastNameLike(@PathVariable String lastName) {
		try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			customerRepository.findByLastNameLike(lastName).forEach(vCustomers::add);
			return new ResponseEntity<>(vCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/first-name/{firstName}")
	public ResponseEntity<List<Customer>> getCustomersByFirstNameLike(@PathVariable String firstName) {
		try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			customerRepository.findByFirstNameLike(firstName).forEach(vCustomers::add);
			return new ResponseEntity<>(vCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> getCustomersByCityLike(@PathVariable String city,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
		try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			customerRepository.findByCityLike(city, PageRequest.of(page, 2)).forEach(vCustomers::add);
			return new ResponseEntity<>(vCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/state/{state}")
	public ResponseEntity<List<Customer>> getCustomersByStateLike(@PathVariable String state,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
		try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			customerRepository.findByStateLike(state, PageRequest.of(page, 2)).forEach(vCustomers::add);
			return new ResponseEntity<>(vCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> getCustomersByCountryLike(@PathVariable String country,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
		try {
			List<Customer> vCustomers = new ArrayList<Customer>();
			customerRepository.findByCountryLike(country, PageRequest.of(page, 6)).forEach(vCustomers::add);
			return new ResponseEntity<>(vCustomers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{country}")
	public ResponseEntity<Object> updateCountry(@PathVariable String country) {
		try {
			int vCustomer = customerRepository.updateCountry(country);
			return new ResponseEntity<>(vCustomer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/* -----------------------------------SERVICE----------------------------------------------- */

	// lấy danh sách customer
	@GetMapping("service/all")
	public ResponseEntity <List<Customer>> getAllCustomer(){
		ResponseEntity <List<Customer>> resul = customerService.getCustomersList();
			return resul ;
	}

	// tìm customer by id 
	@GetMapping("service/detail/{id}")
	public ResponseEntity <Object> getCustomerById(@PathVariable Integer id){
		ResponseEntity <Object> resul = customerService.getCustomersById(id);
        return resul;
	}

	// tạo mới customer
	@PostMapping("service/create")
	public ResponseEntity <Object> createCustomerByService(@RequestBody Customer customer){
	    ResponseEntity <Object> resul = customerService.createCustomer(customer);
        return resul;
	}

	// update customer 
	@PutMapping("service/update/{id}")
	public ResponseEntity <Object> updateCustomerByService(@PathVariable int id  , @RequestBody Customer customer){
		ResponseEntity <Object> resul = customerService.updateCustomer( id,customer);
        return resul;
	}
	
	// delete customer
	@DeleteMapping("service/delete/{id}")
	public ResponseEntity <Object> deleteCustomerByService(@PathVariable int id){
		ResponseEntity <Object> resul = customerService.deleteCustomer(id);
        return resul;
	}
}
