package com.gseasypro.app.widget.dialog;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.github.florent37.singledateandtimepicker.widget.WheelMinutePicker;

import java.util.Date;

import app.gseasypro.com.utils.BaseDialogFragment;

/**
 * Created by fan-gk on 2017/3/14.
 */

public abstract class BaseChooseTimeDialog extends BaseDialogFragment {
    public static final int DEFAULT_ITEM_COUNT_MODE_CURVED = 7;
    public static final int DEFAULT_ITEM_COUNT_MODE_NORMAL = 5;

    @Nullable
    private boolean isDisplaying;
    @ColorInt
    protected Integer backgroundColor = null;

    @Nullable
    @ColorInt
    protected Integer mainColor = null;

    @Nullable
    @ColorInt
    protected Integer titleTextColor = null;

    protected boolean okClicked = false;
    protected boolean curved = false;
    protected boolean mustBeOnFuture = false;
    protected int minutesStep = WheelMinutePicker.STEP_MINUTES_DEFAULT;

    @Nullable
    protected Date minDate;
    @Nullable
    protected Date maxDate;
    @Nullable
    protected Date defaultDate;


    public void display() {
        this.isDisplaying = true;
    }

    public void close() {
        this.isDisplaying = false;
    }

    public boolean isDisplaying() {
        return isDisplaying;
    }

    public void setBackgroundColor(@ColorInt Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setMainColor(@ColorInt Integer mainColor) {
        this.mainColor = mainColor;
    }

    public void setTitleTextColor(@NonNull @ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    protected void onClose() {
        this.isDisplaying = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, app.gseasypro.com.utils.R.style.TranslucentDialogFragment);
    }
}
