package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FIMAGE")
@NamedQueries({ @NamedQuery(name = "getImageByProductId", query = "SELECT i FROM Image i WHERE i.productId=:id"),
		@NamedQuery(name = "getImageByImageId", query = "SELECT i FROM Image i WHERE i.imageId=:id") }) // productDAO에 존재
public class Image implements Serializable {

	@Id
	@Column(name = "IMAGEID")
	@GeneratedValue(generator = "IMAGE_GEN")
	@GenericGenerator(name = "IMAGE_GEN", strategy = "uuid")
	String imageId; // product ID

	@JoinColumn(name = "PRODUCTID")
	@NotNull
	String productId;
	
	String image;
}
