package com.devcamp.pizza365.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_lines")
public class ProductLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Input product line")
	@Size(min = 2, message = "Input product line at least 2 characters")
	@Column(name = "product_line", unique = true)
	private String productLine;

	@NotEmpty(message = "Input description")
	private String description;

	@OneToMany(mappedBy = "productLine" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;

	public ProductLine() {

	}

	public ProductLine(int id, String productLine, String description, List<Product> products) {
		this.id = id;
		this.productLine = productLine;
		this.description = description;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
