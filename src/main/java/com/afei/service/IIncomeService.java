package com.afei.service;

import java.util.Date;
import java.util.List;

import com.afei.model.Income;

public interface IIncomeService {
	
	/**
     * 加载全部income
     * @return
     */
	List<Income> loadIncomes(int userId);
	
	/**
	 * 增加收入
	 * @param item
	 * @param money
	 * @param remark
	 */
	void addIncome(int userId, String date, int item, float money, String moneyType, String remark);
	
	/**
	 * 修改收入
	 * @param incomeId
	 * @param money
	 * @param itemId
	 * @param remark
	 */
	void changeIncome(int incomeId,float money, String moneyType, int itemId ,String remark, Date date);
	
	/**
	 * 删除收入
	 * @param incomeId
	 * @param itemId
	 */
	void deleIncome(int incomeId);
}
