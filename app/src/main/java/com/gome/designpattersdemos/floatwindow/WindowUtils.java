package com.gome.designpattersdemos.floatwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;

import java.lang.reflect.Field;

/**
 * Created by chenhang01 on 2017/2/17.
 */

public class WindowUtils {

    public static int getWindowX(Context context){
        Rect rect = new Rect();
        Window window = ((Activity)context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return window.findViewById(Window.ID_ANDROID_CONTENT).getTop() + rect.top;
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

}
