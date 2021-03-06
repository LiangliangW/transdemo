package com.wll.transdemo.mapper.income;

import org.springframework.stereotype.Repository;

import com.wll.transdemo.model.Income;

@Repository
public interface IncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);
}