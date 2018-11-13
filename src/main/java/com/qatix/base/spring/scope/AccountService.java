package com.qatix.base.spring.scope;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 9:34 PM
 */
public class AccountService {

    public AccountService() {
        System.out.println("AccountService Constructing...");
    }

    private String accountHolder;

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

}
