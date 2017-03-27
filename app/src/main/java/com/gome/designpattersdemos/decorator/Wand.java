package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * 魔杖
 */

public class Wand extends Equipment {

    private Hero hero;
    private Double price = 200d; // 价格
    private int power = 20;
    private int defense = 10;

    public Wand(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Double cost() {
        return price;
    }

    @Override
    public String describtion() {
        return hero.describtion() + ",and i am using Wand";
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
