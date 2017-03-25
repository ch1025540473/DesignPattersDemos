package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * 恶魔巫师lion
 */

public class Lion implements Hero{

    private String describtion = "i am lion";

    @Override
    public String describtion() {
        return describtion;
    }

    @Override
    public int attackPower() {
        return initPower;
    }

    @Override
    public int defense() {
        return initDefense;
    }
}
