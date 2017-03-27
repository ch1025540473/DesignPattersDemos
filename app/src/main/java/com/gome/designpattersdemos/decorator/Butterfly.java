package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * 蝴蝶
 */

public class Butterfly extends Equipment {

    private Hero hero;
    private Double price = 500d; // 价格
    private int power = 100;
    private int defense = 20;

    public Butterfly(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Double cost() {
        return price;
    }

    @Override
    public String describtion() {
        return hero.describtion() + ", and i am useing butterfly";
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
