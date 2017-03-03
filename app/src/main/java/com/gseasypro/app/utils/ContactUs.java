package com.gseasypro.app.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.gseasypro.com.utils.R;
import app.gseasypro.com.utils.utils.ShowDialog;

/**
 * Created by fan-gk on 2017/3/3.
 */


public class ContactUs extends RelativeLayout {
    private FragmentActivity mActivity;


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_contact_num:
                    String tel = ((TextView) findViewById(R.id.tv_contact_num)).getText().toString();
                    ShowDialog.call(mActivity, "联系我们：" + tel, tel);
                    break;
            }
        }
    };

    public ContactUs(Context context) {
        super(context);
        init(context);
    }


    public ContactUs(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ContactUs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mActivity = (FragmentActivity) context;
        LayoutInflater.from(this.getContext()).inflate(R.layout.widget_contact_us, this, true);
        findViewById(R.id.tv_contact_num).setOnClickListener(clickListener);
    }
}
