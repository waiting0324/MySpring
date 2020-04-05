package com.lagou.edu.dao;

import com.lagou.edu.pojo.Account;

/**
 * @author 6550
 * @date 2020/1/6 上午 09:31
 * @description
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;

}
