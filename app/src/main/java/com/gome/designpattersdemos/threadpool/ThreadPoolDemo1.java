package com.gome.designpattersdemos.threadpool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by chenhang01 on 2017/7/10.
 */

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread ta = new MyThread();
        Thread tb = new MyThread();
        Thread tc = new MyThread();
        Thread td = new MyThread();
        Thread te = new MyThread();

        pool.submit(ta);
        pool.execute(tb);
        pool.execute(tc);
        pool.execute(td);
        pool.execute(te);

        pool.shutdown();

        Integer a = 1;
        Integer b = 2;
        Long g = 3L;
        System.out.println(g == a + b);
    }
}

class MyThread extends Thread {
      @Override
      public void run() {
          System.out.println(Thread.currentThread().getName()+ " is running.");
      }
}