package com.afei.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.afei.model.Expenditure;

public interface ExpenditureMapper {

	List<Expenditure> selectAllExpenditures(HashMap map);

	int updateByPrimaryKeySelective(Expenditure record);

	int insert(Expenditure record);
	
	List<Map> searchLast12Expenditure(Map map);
	
	List<Map> selectExpenditureGroupByItemName(Map map);
	
	List<Map> selectAllExpenditureGroupByItemName(Map map);

	////////////////////////////////////////////////////////////////

	int deleteByPrimaryKey(Integer id);

	int insertSelective(Expenditure record);

	Expenditure selectByPrimaryKey(Integer id);
}