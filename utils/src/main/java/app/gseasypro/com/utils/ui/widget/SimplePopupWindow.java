package app.gseasypro.com.utils.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class SimplePopupWindow extends android.widget.PopupWindow {
    public SimplePopupWindow(Context context, @LayoutRes int resource){
        this(LayoutInflater.from(context).inflate(resource, null));
    }

    public SimplePopupWindow(Context context, @LayoutRes int resource, int width, int height){
        this(LayoutInflater.from(context).inflate(resource, null), width, height);
    }

    public SimplePopupWindow(View view, int width, int height){
        super(view, width, height, true);
        final ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        setBackgroundDrawable(dw);
        setOutsideTouchable(true);

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }



    public SimplePopupWindow(View view){
        this(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void showAsDropDown(View anchor) {
        showAsDropDown(anchor, anchor.getWidth(), 0);
    }
}
