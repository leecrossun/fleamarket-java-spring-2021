package com.yanado.dto;

import java.sql.Date;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Range;

public class Alarm {

	String alarmId; // 알람 ID
	String userId; // 알람을 받는 회원의 ID
	
	String commonId = null; // 해당 알람이 생성된 공동구매 ID
	
	String aucId = null; // 해당 알람이 생성된 경매 ID

	@Range(min = 1, max = 3)
	int type; // 알람의 용도 1 : result, 2 : notice, 3 : etc
	
	@PositiveOrZero
	int price; // 해당 상품의 가격
	
	String message; // 알람 내용
	Date sendDate; // 보낸 날짜
	Date deadline; // 상품 결제 마감일

	public Alarm() {
	}

	public Alarm(String alarmId, String userId, String commonId, String aucId, int type, int price, String message,
			Date sendDate, Date deadline) {

		this.alarmId = alarmId;
		this.userId = userId;
		this.commonId = commonId;
		this.aucId = aucId;
		this.type = type;
		this.price = price;
		this.message = message;
		this.sendDate = sendDate;
		this.deadline = deadline;
	}

	public Alarm(String userId, String commonId, String aucId, int type, int price, String message, Date sendDate,
			Date deadline) {

		this.userId = userId;
		this.commonId = commonId;
		this.aucId = aucId;
		this.type = type;
		this.price = price;
		this.message = message;
		this.sendDate = sendDate;
		this.deadline = deadline;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	public String getAucId() {
		return aucId;
	}

	public void setAucId(String aucId) {
		this.aucId = aucId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
