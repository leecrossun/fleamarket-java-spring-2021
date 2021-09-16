package com.yanado.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Setter
@Getter
public class ComKey implements Serializable {
	
	@Column(name = "PRODUCTID")
	String productId;
	
	@Column(name = "USERID")
	String userId;
}
