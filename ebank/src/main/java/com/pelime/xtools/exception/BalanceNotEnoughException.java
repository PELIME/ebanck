package com.pelime.xtools.exception;


public class BalanceNotEnoughException extends RuntimeException{

    public BalanceNotEnoughException(){
        super("余额不足");
    }

}
