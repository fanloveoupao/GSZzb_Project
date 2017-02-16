package app.gseasypro.com.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class ScreenUtils {
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getWindowsWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getWindowsHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels - getStausBarHeight();
    }

    public static int getStausBarHeight() {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int height = Integer.parseInt(field.get(obj).toString());
            if (height < 90) {
                return TypedValueUtil.fromDip(height);
            }
        } catch (Exception e) {
        }

        return TypedValueUtil.fromDip(25);
    }
}
