package com.gome.designpattersdemos.floatwindow;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;



/**
 * Created by chenhang01 on 2017/2/16.
 * 悬浮窗父类
 */

public abstract class FloatingWindow extends LinearLayout {

    private Context context;

    private int topHeight;

    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;

    private float x,y; // 距离悬浮窗口窗口左上角x,y
    private float rawX, rawY; // 距离屏幕左上角的坐标
    private float viewX, viewY;
    private float endX,endY;
    private int screenWidth; // 手机屏幕宽搞
    private int screenHeight;

    private int currentViewWidth;
    private int currentViewHeight;

    private float animStart,animEnd;

    private String orentation = "x";
    private long lastClickTime = 0L;
    private static final int MAX_TIME = 3000;
    private long annimationDuration = 200L;



    public FloatingWindow(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                rawX = event.getRawX();
                rawY = event.getRawY();
                this.clearAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                float positonX = event.getRawX();
                float posioonY = event.getRawY();

                viewX = positonX - x;
                viewY = posioonY - y;
                if (posioonY > topHeight + y){
                    updatePosition();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getRawX() - rawX) < 10 && Math.abs(event.getRawY() - rawY) < 10){
                    // 单击事件
                    onClick();
                    return true;
                }
                endX = event.getRawX();
                endY = event.getRawY();
                if (endY -y < topHeight) // 滚动的位置超过状态栏和标题栏的高度时不做判断
                    return true;
                // 判断动画滚动的方向
                countXY();
                // 执行动画
                startAnimation();
                break;
        }
        return super.onTouchEvent(event);
    }

    public abstract void onClick();

    boolean isInterruptedClick(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime < MAX_TIME){
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

    private void countXY(){
        // 计算屏幕正中心的坐标点
        int centerX = screenWidth / 2;
        int centerY = (screenHeight - topHeight) / 2 + topHeight;

        // 划分成四个区域，规划悬浮窗的停靠位置
        if (endX < centerX && endY < centerY){// 第一象限
            if (endY - topHeight < endX){
                animStart = endY - y;
                animEnd = topHeight;
                orentation = "y";
            }else {
                animStart = endX - x;
                animEnd = 0;
                orentation = "x";
            }
        }else if (endX > centerX && endY < centerY){ // 第二象限
            if (endY - topHeight < screenWidth - endX){
                animStart = endY - y;
                animEnd = topHeight;
                orentation = "y";
            }else {
                animStart = endX - x;
                animEnd = screenWidth - currentViewWidth;
                orentation = "x";
            }
        }else if (endX < centerX && endY > centerY){ // 第三象限
            if (screenHeight - endY < endX){
                animStart = endY - y;
                animEnd = screenHeight -currentViewHeight;
                orentation = "y";
            }else {
                animStart = endX - x;
                animEnd = 0;
                orentation = "x";
            }
        }else if (endX > centerX && endY > centerY){ // 第四象限
            if (screenHeight - endY < screenWidth - endX){
                animStart = endY - y;
                animEnd = screenHeight - currentViewHeight;
                orentation = "y";
            }else {
                animStart = endX - x;
                animEnd = screenWidth - currentViewWidth;
                orentation = "x";
            }
        }

    }

    public void startAnimation(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layoutParams,orentation,animStart,animEnd);
        objectAnimator.setDuration(annimationDuration);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (orentation.equals("x")){
                    viewX = (float) animation.getAnimatedValue();
                }else {
                    viewY = (float) animation.getAnimatedValue();
                }
                updatePosition();
            }

        });

        if (endY < topHeight + y){
            viewY = topHeight;
        }
        objectAnimator.start();
    }

    private void updatePosition(){
        layoutParams.x = (int) viewX;
        layoutParams.y = (int) viewY;
        if (null != windowManager && null !=this.getWindowToken()) windowManager.updateViewLayout(this,layoutParams);
    }

    public void removeView(){
        windowManager.removeView(this);
    }

    abstract void startActivity();


    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setParams(WindowManager.LayoutParams layoutParams){
        this.layoutParams = layoutParams;
    }

    /**
     *
     * @param topHeight 界面的顶部高度，包括了状态栏和标题栏的高度值
     */
    public void setTopHeight(int topHeight) {
        this.topHeight = topHeight;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getCurrentViewWidth() {
        return currentViewWidth;
    }

    public int getCurrentViewHeight() {
        return currentViewHeight;
    }

    public void setCurrentViewWidth(int currentViewWidth) {
        this.currentViewWidth = currentViewWidth;
    }

    public void setCurrentViewHeight(int currentViewHeight) {
        this.currentViewHeight = currentViewHeight;
    }

    public long getAnnimationDuration() {
        return annimationDuration;
    }

    public void setAnnimationDuration(long annimationDuration) {
        this.annimationDuration = annimationDuration;
    }
}
