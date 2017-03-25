package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * 船长coco
 */

public class Captain implements Hero {

    private String describtion = "i am coco";

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
