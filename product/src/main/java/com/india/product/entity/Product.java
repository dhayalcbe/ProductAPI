package com.india.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "price")
	private double price;

	@Lob
	private byte[] image; // Generally Use AWS S3 as link(https://url.png)

	@Column(name = "reviewGroupId")
	private Long reviewGroupId;
}
