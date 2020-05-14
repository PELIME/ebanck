package com.pelime.xtools.exception;

public class CommodityNumberNotEnoughException extends RuntimeException{

    public CommodityNumberNotEnoughException(){
        super("商品数量不足");
    }

}
