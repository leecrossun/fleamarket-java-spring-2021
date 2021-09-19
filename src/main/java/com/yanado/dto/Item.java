package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NamedQueries({
	@NamedQuery
	(
			name = "getItemByItemId",
			query = "SELECT i FROM Item i WHERE i.itemId=:id"
	),
	@NamedQuery(name = "getItemByOrderId", query = "SELECT i FROM Item i WHERE i.order.orderId=:id")
	
}) // OrderDAO에 존재
@Table(name = "FITEM")
public class Item implements Serializable{
	
	@Id
	@GeneratedValue(generator = "ITEM_GEN")
	@GenericGenerator(name = "ITEM_GEN", strategy = "uuid")
	@Column(name="ITEMID")
	String itemId;
	
	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name="ORDERID")
	Order order;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name="PRODUCTID", nullable = false)
	@NotNull
	Product product;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="BUYER", nullable = false)
	@NotNull
	User buyer;
	
	int unitcost;
	int quantity;
	
	public Item(String itemId, Product product, User buyer, int unitcost, int quantity) {
		this.itemId = itemId;
		this.product = product;
		this.buyer = buyer;
		this.unitcost = unitcost;
		this.quantity = quantity;
	}
	
	
}
