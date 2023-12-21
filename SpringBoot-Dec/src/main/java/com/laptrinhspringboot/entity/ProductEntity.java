package com.laptrinhspringboot.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String name;
	private String email;
	private double price;
	
	@Column(name="create_date")
	private Date  createdDate;
	
	private Integer available;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity categoryEntity;
	
	@OneToMany(mappedBy = "productEntity",fetch = FetchType.LAZY)
	private List<OrderDetailEntity> orderDetailEntityList;
	
}
