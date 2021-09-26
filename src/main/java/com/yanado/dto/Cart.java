package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.*;

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
@Table(name = "FCART")
@NamedQueries({ @NamedQuery(name = "getCartByUserId", query = "SELECT c FROM Cart c WHERE c.user.userId=:id"),
		@NamedQuery(name = "getSupplierList", query = "select c from Cate c where c.supplierId in (select c1.product.supplierId from Cart c1 where c1.user.userId=:id)") })

public class Cart implements Serializable {

	@Id
	@GeneratedValue(generator = "CART_GEN")
	@GenericGenerator(name = "CART_GEN", strategy = "uuid")
	@Column(name = "CARTID")
	private String CartId;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "USERID")
	private User user; // 외래키

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCTID")
	private Product product; // 외래키

	int quantity;
	int cost;

	@Transient
	private String supplierId;

	public void setSupplierId() {
		this.supplierId = product.getSupplierId();
	}

	public String getSupplierId() {
		return product.getSupplierId();
	}
}
