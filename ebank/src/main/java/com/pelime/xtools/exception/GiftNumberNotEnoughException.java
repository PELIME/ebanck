package com.pelime.xtools.exception;


public class GiftNumberNotEnoughException extends RuntimeException{

    public GiftNumberNotEnoughException(){
        super("礼品数量不足");
    }

}
