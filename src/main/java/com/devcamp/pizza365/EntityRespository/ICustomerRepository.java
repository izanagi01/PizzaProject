package com.devcamp.pizza365.EntityRespository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.pizza365.entity.Customer;


public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByLastName(String lastName);

	Optional<Customer> findByFirstName(String firstName);

	Optional<Customer> findByLastNameAndFirstName(String lastName , String firstName);
	// Customer getOrderByCustomerID(int id) ;
	// chọn table điều kiện là tên cột giống tên thuộc tính
	@Query(value = "SELECT * FROM customers WHERE last_name LIKE :lastName%", nativeQuery = true)
	List<Customer> findByLastNameLike(@Param("lastName") String lastName);

	@Query(value = "SELECT * FROM customers WHERE first_name LIKE :firstName%", nativeQuery = true)
	List<Customer> findByFirstNameLike(@Param("firstName") String firstName);

	@Query(value = "SELECT * FROM customers WHERE city LIKE :city%", nativeQuery = true)
	List<Customer> findByCityLike(@Param("city") String city, Pageable pageable);

	@Query(value = "SELECT * FROM customers WHERE state LIKE :state%", nativeQuery = true)
	List<Customer> findByStateLike(@Param("state") String state, Pageable pageable);

	@Query(value = "SELECT * FROM customers WHERE country = :country ORDER BY first_name ASC", nativeQuery = true)
	List<Customer> findByCountryLike(@Param("country") String country, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE customers SET country = :country  WHERE country IS null", nativeQuery = true)
	int updateCountry(@Param("country") String country);
	
}
