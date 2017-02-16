package app.gseasypro.com.utils.ui.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class ViewUtils {
    private ViewUtils() {

    }

    public static void clearCompoundDrawable(TextView textView) {
        textView.setCompoundDrawables(null, null, null, null);
    }

    public static void setLeftDrawable(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setRightDrawable(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setTopDrawable(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public static void setBottomDrawable(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }
}
