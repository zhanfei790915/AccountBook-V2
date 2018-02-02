package com.afei.model;

import java.util.Date;

public class Income {

	private int id;

	private int itemId;
	
	private String itemName;

	private int userId;

	private Float money;
	
	private String type_of_money;
	
	private Date date;

	private String remark;

	private int dele;
	
	private String payMethodName;

	private int payMethodId;
	
	public int getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(int payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public int getItemId() {
		return itemId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDele() {
		return dele;
	}

	public void setDele(int dele) {
		this.dele = dele;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getType_of_money() {
		return type_of_money;
	}

	public void setType_of_money(String type_of_money) {
		this.type_of_money = type_of_money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}