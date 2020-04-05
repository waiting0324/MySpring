package com.lagou.edu.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 6550
 * @date 2020/1/6 上午 08:36
 * @description
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getCurConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null) {
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
