package com.yanado.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@NamedQueries({ @NamedQuery(name = "getOrderByOrderId", query = "SELECT o FROM Order o WHERE o.orderId=:id"),
		@NamedQuery(name = "getOrderByUserId", query = "SELECT o FROM Order o WHERE o.buyer.userId=:id"),
		@NamedQuery(name = "getOrderBySupplierId", query = "SELECT o FROM Order o WHERE o.supplier.userId=:id")
		

})
@Table(name = "FORDER")
public class Order implements Serializable {

	@Id
	@GeneratedValue(generator = "ORDER_GEN")
	@GenericGenerator(name = "ORDER_GEN", strategy = "uuid")
	@Column(name = "ORDERID")
	String orderId;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "SUPPLIER")
	User supplier;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "BUYER")
	User buyer;

	@Column(name = "BILLNAME")
	String billName;

	@Column(name = "BILLPHONE")
	String billPhone;

	@Column(name = "BILLADDRESS")
	String billAddress;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@Transient
	List<Item> item;

	@Column(name = "TOTALPRICE")
	int totalPrice;

	@Column(name = "ORDERDATE")
	@Temporal(TemporalType.DATE)
	Date orderDate;

	int payment;

	int status;
}
