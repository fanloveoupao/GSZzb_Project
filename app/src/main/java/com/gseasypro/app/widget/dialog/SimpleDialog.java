package com.gseasypro.app.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.gseasypro.app.R;

import app.gseasypro.com.utils.ScreenUtils;

/**
 * Created by fan-gk on 2017/3/14.
 */

public class SimpleDialog extends Dialog {
    private Context mContext;

    public SimpleDialog(Context context) {
        super(context, R.style.dialog_style);
        mContext = context;
    }

    public SimpleDialog(Context context, int style) {
        super(context, style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getWindowsWidth(mContext) * 0.85);
        getWindow().setAttributes(params);


    }
}