package com.wll.transdemo.model;

import lombok.Data;
import java.util.Date;

@Data
public class Income {
    private Integer id;

    private Integer userId;

    private Float amount;

    private Date operateDate;
}