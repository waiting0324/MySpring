package com.lagou.edu.utils;

import java.sql.SQLException;

/**
 * @author 6550
 * @date 2020/1/6 上午 08:42
 * @description
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void beginTransaction() throws SQLException {
        connectionUtils.getCurConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtils.getCurConnection().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getCurConnection().rollback();
    }
}
