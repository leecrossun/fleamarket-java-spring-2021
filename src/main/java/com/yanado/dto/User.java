package com.yanado.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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
@NamedQueries({
		@NamedQuery(name = "getUserByUserId", query = "SELECT m FROM User m WHERE m.userId=:id"),
		@NamedQuery(name = "login", query = "SELECT m FROM User m WHERE m.userId=:id and m.password = :password"),
		@NamedQuery(name = "getUserByUserName", query = "SELECT m FROM User m WHERE m.userName=:userName")
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

//	@Column(name = "AUTH")
//	private int auth; // 셀러 or 구매자 -> 0(셀러), 1(구매자)

	@Column(name = "CERTIFIED")
	private String certified; // 이메일 인증 코드

	@Column(nullable = false, name = "AUTH")
	@Enumerated(value = EnumType.STRING)
	private UserRole auth;

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

	// 회원 가입 시 사용
	public User(String userId, String password, String userName, String address, String phone, String email, String account, String bank, String accName) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.auth = UserRole.USER; // 등급은 0 ㅑ고정 (seller는 관리자가 직접 가입)
		this.account = account;
		this.bank = bank;
		this.accName = accName;
	}
}
