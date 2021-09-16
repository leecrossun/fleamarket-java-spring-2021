package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FBUYER")

@NamedQueries({

		@NamedQuery(name = "getBuyerByProductId", query = "SELECT b FROM Buyer b WHERE b.productId=:id"),

		@NamedQuery(name = "getProductByBuyerId", query = "SELECT b FROM Buyer b WHERE b.userId=:id"),

		@NamedQuery(name = "updatePayment", query = "UPDATE Buyer b SET b.payment = :payment WHERE b.userId=:userId AND b.productId=:productId") })

@IdClass(ComKey.class)
public class Buyer implements Serializable {

	@Id
	@Column(name = "PRODUCTID")
	String productId;

	@Id
	@Column(name = "USERID")
	String userId;

	@Transient
	@ManyToOne
	@JoinColumn(name = "PRODUCTID")
	@NotNull
	Product product;

	@Transient
	@ManyToOne
	@JoinColumn(name = "USERID")
	@NotNull
	User user;

	int payment;

	public String getProductId() {
		return product.getProductId();
	}

	public void setUserId(String userId) {
		this.user.setUserId(userId);
	}

	public String getUserId() {
		return user.getUserId();
	}

	public void setProductId(String productId) {
		this.product.setProductId(productId);
	}

	public Buyer(@NotNull Product product, @NotNull User user, int payment) {
		this.product = product;
		this.user = user;
		this.payment = payment;
		this.userId = user.getUserId();
		this.productId = product.getProductId();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}
