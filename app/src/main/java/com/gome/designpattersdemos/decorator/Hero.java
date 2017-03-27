package com.gome.designpattersdemos.decorator;

/**
 * Created by chenhang01 on 2017/3/3.
 * dota 英雄类接口
 */

public interface Hero {
    public int initPower = 30; // 初始攻击力
    public int initDefense = 10; // 初始防御力

    /**
     * 描述
     * @return
     */
    public String describtion();

    /**
     * 攻击力
     * @return
     */
    public int attackPower();

    /**
     * 防御力
     * @return
     */
    public int defense();
}
