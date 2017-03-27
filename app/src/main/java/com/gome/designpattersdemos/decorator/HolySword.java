package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * 圣剑
 */

public class HolySword extends Equipment {

    private Hero hero;
    private Double price = 1000d; // 价格
    private int power = 200;
    private int defense = 50;

    public HolySword(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Double cost() {
        return price;
    }

    @Override
    public String describtion() {
        return hero.describtion() + ",and i am using HolySword";
    }

    @Override
    public int attackPower() {
        return hero.attackPower() + power;
    }

    @Override
    public int defense() {
        return hero.defense() + defense;
    }
}
