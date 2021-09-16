package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "FCART")
@IdClass(ComKey.class)
@NamedQueries({ @NamedQuery(name = "getCartByUserId", query = "SELECT c FROM Cart c WHERE c.userId=:id") })
public class Cart implements Serializable {

	@Id
	@JoinColumn(name="USERID")
	@NotNull
	String userId; // 외래키

	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	@NotNull
	Product product; // 외래키
	
	int quantity;
	int cost;
	
	
	@Id
	public String getProductId() {
		return product.getProductId();
	}
	
	public void setProductId(String productId) {
		this.product.setProductId(productId);
	}


}
