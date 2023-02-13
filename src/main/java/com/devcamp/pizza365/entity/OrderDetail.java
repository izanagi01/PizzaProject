package com.devcamp.pizza365.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Transient
	private int product_id;

	public int getProduct_id() {
		return getProduct().getId();
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	

	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity_order")
	private int quantityOrder;

	@Column(name = "price_each")
	private BigDecimal priceEach;

	public OrderDetail() {

	}

	public OrderDetail(int id, int quantityOrder, BigDecimal priceEach) {
		super();
		this.id = id;
		this.quantityOrder = quantityOrder;
		this.priceEach = priceEach;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

}
