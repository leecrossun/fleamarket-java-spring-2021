package com.yanado.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "FMEMBER")
@NamedQueries({ @NamedQuery(name = "getUserByUserId", query = "SELECT m FROM User m WHERE m.userId=:id"),
	@NamedQuery(name = "login", query = "SELECT m FROM User m WHERE m.userId=:id and m.password = :password")
})

public class User implements Serializable {

	@Id
	@Column(name = "USERID")
	private String userId; // 아이디

	@Column(name = "PASSWORD")
	private String password; // 비밀번호

	@Column(name = "USERNAME")
	private String userName; // 이름

	@Column(name = "ADDRESS")
	private String address; // 주소

	@Column(name = "PHONE")
	private String phone; // 전화번호

	@Column(name = "EMAIL")
	private String email; // 이메일

	@Column(name = "AUTH")
	private int auth; // 셀러 or 구매자 -> 0(셀러), 1(구매자)

	@Column(name = "ACCOUNT")
	private String account; // 계좌

	@Column(name = "BANK")
	private String bank; // 은행명

	@Column(name = "ACCNAME")
	private String accName; // 계좌주 이름 (실명)

	@Transient
	@OneToMany(mappedBy = "user")
	List<Order> order;

	@Transient
	@OneToMany(mappedBy = "user")
	List<Cart> cart;

	// 마이페이지에서 정보 수정 시 사용
	public User(String password, String address, String phoneNumber) {
		this.password = password;
		this.address = address;
		this.phone = phoneNumber;
	}

}
