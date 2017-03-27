package com.gome.designpattersdemos.floatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;


/**
 * Created by chenhang01 on 2017/2/20.
 */

public class BaseManagerWindow {


    private FloatingWindow floatingWindow;
    private int top;

    public BaseManagerWindow(int top) {
        this.top = top;
    }

    public void setFloatingWindow(FloatingWindow floatingWindow){
        this.floatingWindow = floatingWindow;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void showFloatingWindow(){

        WindowManager windowManager = getWindowManager(floatingWindow.getContext());

        WindowManager.LayoutParams layoutParams = null;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        if (null != floatingWindow){
            if (null == layoutParams){
                layoutParams = new WindowManager.LayoutParams();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                }else {
                    layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                }
                layoutParams.format = PixelFormat.RGBA_8888;
                layoutParams.width = floatingWindow.getCurrentViewWidth();
                layoutParams.height = floatingWindow.getCurrentViewHeight();
                layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
                layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                layoutParams.x = 0;
                layoutParams.y = top;
            }

            floatingWindow.setWindowManager(windowManager);
            floatingWindow.setParams(layoutParams);
            floatingWindow.setTopHeight(top);
            floatingWindow.setScreenWidth(screenWidth);
            floatingWindow.setScreenHeight(screenHeight);
            windowManager.addView(floatingWindow,layoutParams);
        }
    }


    public FloatingWindow getFloatingWindow() {
        return floatingWindow;
    }

    public void removeWindowView(){
        if (null != floatingWindow){
            if (floatingWindow.getWindowToken() != null){
                floatingWindow.removeView();
                floatingWindow = null;
            }
        }
    }

    public boolean isWindowShow(){
        if (null == floatingWindow){
            return false;
        }else {
            return true;
        }
    }

    private WindowManager getWindowManager(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager;
    }
}
