package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 6550
 * @date 2020/1/6 上午 08:31
 * @description
 */
public class DruidUtils {

    private DruidUtils() {}

    private static DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/bank");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }
}
