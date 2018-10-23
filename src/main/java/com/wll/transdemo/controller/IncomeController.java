package com.wll.transdemo.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wll.transdemo.mapper.income.IncomeMapper;
import com.wll.transdemo.mapper.user.UserMapper;
import com.wll.transdemo.model.Income;
import com.wll.transdemo.model.User;

/**
 * Created by WLL on 2018/10/23 10:56
 */
@RestController
@RequestMapping("/income")
public class IncomeController {

    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_FAILED = "failed";

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/addincome/1")
    @Transactional
    public String addIncome1(@RequestParam("name") String name, @RequestParam("amount") float amount) {

        try {
            User user = new User();
            user.setName(name);
            userMapper.insertSelective(user);

            Income income = new Income();
            income.setUserId(user.getId());
            income.setAmount(amount);
            income.setOperateDate(new Timestamp(System.currentTimeMillis()));
            incomeMapper.insertSelective(income);

            return RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return RESULT_FAILED + ":" + e.getMessage();
        }
    }

    @GetMapping("/addincome/2")
    @Transactional
    public String addIncome2(@RequestParam("name") String name, @RequestParam("amount") float amount) {
        try {

            User user = new User();
            user.setName(name);
            userMapper.insertSelective(user);

            this.throwRuntimeException();

            Income income = new Income();
            income.setUserId(user.getId());
            income.setAmount(amount);
            income.setOperateDate(new Timestamp(System.currentTimeMillis()));
            incomeMapper.insertSelective(income);

            return RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // return RESULT_FAILED + ":" + e.getMessage();
        }
    }

    @GetMapping("/addincome/3")
    @Transactional
    public String addIncome3(@RequestParam("name") String name, @RequestParam("amount") float amount) {
        try {

            User user = new User();
            user.setName(name);
            userMapper.insertSelective(user);

            this.throwRuntimeException();

            return RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // return RESULT_FAILED + ":" + e.getMessage();
        }
    }

    public void throwRuntimeException() {
        throw new RuntimeException("User defined exceptions");
    }
}

