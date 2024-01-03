package com.springboot.entity;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unit_price")
	private double unitPrice;
	
	@Column(name = "image")
	private String image;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "date")
	private Date date;
	
	@Column(name = "available")
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<OrderDetails> orderDetailsList;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Column(name = "special")
	private Boolean special;
}
