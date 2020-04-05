package com.lagou.edu.service;

/**
 * @author 6550
 * @date 2020/1/6 上午 09:26
 * @description
 */
public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}
