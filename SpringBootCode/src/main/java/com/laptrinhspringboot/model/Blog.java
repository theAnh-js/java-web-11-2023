package com.laptrinhspringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "topic")
	private String topic;

	@Column(name = "author")
	private String author;

	@ManyToOne(targetEntity = ECommerce.class)
	@JoinColumn(name = "ecommerce_id") 
	private ECommerce eCommerce;

	public Blog() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ECommerce geteCommerce() {
		return eCommerce;
	}

	public void seteCommerce(ECommerce eCommerce) {
		this.eCommerce = eCommerce;
	}

}
