package com.yanado.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
@Table(name = "FPRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({ @NamedQuery(name = "getProductByCategory", query = "SELECT p FROM Product p WHERE p.cate.cateId=:id"),
		@NamedQuery(name = "getProductBySupplierId", query = "SELECT p FROM Product p WHERE p.supplierId=:id"),
		@NamedQuery(name = "getProductList", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "getProductByProductId", query = "SELECT p FROM Product p WHERE p.productId=:id"),
		@NamedQuery(name = "updateStockByProductId", query = "UPDATE Product p SET p.stock = p.stock-1 WHERE p.productId=:id AND p.stock > 0"),
		@NamedQuery(name = "getProductByProductName", query = "SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%',:productName,'%')") })
public class Product implements Serializable {

	@Id
	@Column(name = "PRODUCTID")
	@GeneratedValue(generator = "PRODUCT_GEN")
	@GenericGenerator(name = "PRODUCT_GEN", strategy = "uuid")
	String productId; // product ID

	@Column(name = "PRODUCTNAME")
	@NotNull
	String productName;

	@Column(name = "SUPPLIERID")
	@NotNull
	String supplierId;

	@ManyToOne(targetEntity = Cate.class)
	@JoinColumn(name = "CATEID", nullable = false)
	Cate cate;

	@NotNull
	@Positive
	int stock;

	// @NotNull
	String content;

	/*
	 * @Transient String contentString;
	 */
	@NotNull
	@Positive
	int price;

	@NotNull
	@Positive
	int delivery;

	@Temporal(TemporalType.DATE)
	@Column(name = "REGDATE")
	Date regDate;

	@Transient
	@OneToMany(mappedBy = "product")
	List<Image> imageList;


	/*
	 * public void setContentString(String contentString) { if (contentString !=
	 * null) { this.contentString = contentString; this.content =
	 * contentString.getBytes(); } }
	 * 
	 * public void setContent(byte[] content) { if (content != null) { this.content
	 * = content; this.contentString = new String(content); } }
	 */
}
