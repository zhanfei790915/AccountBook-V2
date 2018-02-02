package com.afei.model;

import java.util.Date;

public class Balance {
    private Integer id;

    private Integer userId;

    private Date month;

    private Float actualBalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

	public Float getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(Float actualBalance) {
        this.actualBalance = actualBalance;
    }
    
    @Override
  	public String toString() {
  		return "Balance [id=" + id + ", userId=" + userId + ", month=" + month + ", actualBalance=" + actualBalance
  				+ "]";
  	}
}