package com.gome.designpattersdemos;

import android.util.Log;

import com.gome.designpattersdemos.decorator.Butterfly;
import com.gome.designpattersdemos.decorator.Captain;
import com.gome.designpattersdemos.decorator.Equipment;
import com.gome.designpattersdemos.decorator.Hero;
import com.gome.designpattersdemos.decorator.Lion;
import com.gome.designpattersdemos.decorator.Wand;

/**
 * Created by chenhang01 on 2017/3/3.
 */

public class DesignTest {

    /**
     * 装饰者模式
     */
    public void decorator(){

        Hero lion = new Lion();
//        Hero captain = new Captain();

        Equipment butterfly = new Butterfly(lion);
        Equipment wand = new Wand(butterfly);


        System.out.println("lion`s describtion: " + wand.describtion());
        System.out.println("lion`s cost: " + wand.cost() + "");
        System.out.println("lion`s power: " + wand.attackPower() + "");
        System.out.println("lion`s defense : " + wand.defense() + "");

//        System.out.println("captain`s describtion : " + wand.describtion());
//        System.out.println("captain`s cost: " + wand.cost() + "");
//        System.out.println("captain`s power: " + captain.attackPower() + "");
//        System.out.println("captain`s defense: " + captain.defense() + "");




    }

    public static void main(String[] args){
        DesignTest designTest = new DesignTest();

        designTest.decorator();



    }


}
