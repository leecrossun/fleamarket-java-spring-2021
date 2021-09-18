package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "FCATEGORY")
@NamedQueries({ @NamedQuery(name = "getCategoryByCateId", query = "SELECT c FROM Cate c WHERE c.cateId=:id"),

		@NamedQuery(name = "getCategoryBySupplierId", query = "SELECT c FROM Cate c WHERE c.supplierId=:id"),

		@NamedQuery(name = "getCategoryByCateName", query = "SELECT c FROM Cate c WHERE c.cateName=:name") })
public class Cate implements Serializable {

	@Id
	@Column(name = "CATEID")
	String cateId;

	@Column(name = "CATENAME")
	String cateName;

	@Column(name = "SUPPLIERID")
	String supplierId; // 외래키

	String sns;

	String phone; // 문의전화

	String img; // 동아리 대표 이미지
}
