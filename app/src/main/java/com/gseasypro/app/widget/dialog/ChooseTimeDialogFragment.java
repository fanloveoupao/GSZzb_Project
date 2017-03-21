package com.gseasypro.app.widget.dialog;


import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.widget.WheelMinutePicker;
import com.gseasypro.app.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseTimeDialogFragment extends BaseChooseTimeDialog {


    @BindView(R.id.picker)
    SingleDateAndTimePicker picker;
    @BindView(R.id.buttonCancel)
    TextView mBtnCancel;
    @BindView(R.id.buttonOk)
    TextView mBtnOk;
    private Listener listener;
    @Nullable
    private String title;
    private View mView;

    public static ChooseTimeDialogFragment createInstance() {
        return new ChooseTimeDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_choose_time_dialog, container, false);
        ButterKnife.bind(this, mView);
        init(mView);
        return mView;
    }

    private void init(View view) {

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okClicked = true;
                close();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if (mainColor != null) {
            mBtnOk.setTextColor(mainColor);
            mBtnCancel.setTextColor(mainColor);
        }
        final View sheetContentLayout = view.findViewById(R.id.sheetContentLayout);
        if (sheetContentLayout != null) {
            sheetContentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            if (backgroundColor != null) {
                sheetContentLayout.setBackgroundColor(backgroundColor);
            }
        }

        final TextView titleTextView = (TextView) view.findViewById(R.id.sheetTitle);
        if (titleTextView != null) {
            titleTextView.setText(title);

            if (titleTextColor != null) {
                titleTextView.setTextColor(titleTextColor);
            }
        }

        final View pickerTitleHeader = view.findViewById(R.id.pickerTitleHeader);
        if (mainColor != null && pickerTitleHeader != null) {
            pickerTitleHeader.setBackgroundColor(mainColor);
        }

        if (curved) {
            picker.setCurved(true);
            picker.setVisibleItemCount(7);
        } else {
            picker.setCurved(false);
            picker.setVisibleItemCount(5);
        }
        picker.setMustBeOnFuture(mustBeOnFuture);

        picker.setStepMinutes(minutesStep);

        if (mainColor != null) {
            picker.setSelectedTextColor(mainColor);
        }

        if (minDate != null) {
            picker.setMinDate(minDate);
        }

        if (maxDate != null) {
            picker.setMaxDate(maxDate);
        }

        if (defaultDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(defaultDate);
            picker.selectDate(calendar);
        }
    }

    public ChooseTimeDialogFragment setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public ChooseTimeDialogFragment setCurved(boolean curved) {
        this.curved = curved;
        return this;
    }

    public ChooseTimeDialogFragment setMinutesStep(int minutesStep) {
        this.minutesStep = minutesStep;
        return this;
    }

    public ChooseTimeDialogFragment setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    public ChooseTimeDialogFragment setMustBeOnFuture(boolean mustBeOnFuture) {
        this.mustBeOnFuture = mustBeOnFuture;
        return this;
    }

    public ChooseTimeDialogFragment setMinDateRange(Date minDate) {
        this.minDate = minDate;
        return this;
    }

    public ChooseTimeDialogFragment setMaxDateRange(Date maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public ChooseTimeDialogFragment setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
        return this;
    }

    @Override
    public void display() {
        super.display();
    }

    @Override
    public void close() {
        super.close();
        if (listener != null && okClicked) {
            listener.onDateSelected(picker.getDate());
        }
        dismiss();
    }

    public interface Listener {
        void onDateSelected(Date date);
    }

    public static class Builder {
        @Nullable
        private Listener listener;

        @Nullable
        private String title;

        private boolean bottomSheet;

        private boolean curved;
        private boolean mustBeOnFuture;
        private int minutesStep = WheelMinutePicker.STEP_MINUTES_DEFAULT;

        @ColorInt
        @Nullable
        private Integer backgroundColor = null;

        @ColorInt
        @Nullable
        private Integer mainColor = null;

        @ColorInt
        @Nullable
        private Integer titleTextColor = null;

        @Nullable
        private Date minDate;
        @Nullable
        private Date maxDate;
        @Nullable
        private Date defaultDate;

        public Builder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        public Builder bottomSheet() {
            this.bottomSheet = true;
            return this;
        }

        public Builder curved() {
            this.curved = true;
            return this;
        }

        public Builder mustBeOnFuture() {
            this.mustBeOnFuture = true;
            return this;
        }

        public Builder minutesStep(int minutesStep) {
            this.minutesStep = minutesStep;
            return this;
        }

        public Builder listener(@Nullable Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder titleTextColor(@NonNull @ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder backgroundColor(@NonNull @ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder mainColor(@NonNull @ColorInt int mainColor) {
            this.mainColor = mainColor;
            return this;
        }

        public Builder minDateRange(Date minDate) {
            this.minDate = minDate;
            return this;
        }

        public Builder maxDateRange(Date maxDate) {
            this.maxDate = maxDate;
            return this;
        }

        public Builder defaultDate(Date defaultDate) {
            this.defaultDate = defaultDate;
            return this;
        }


        public ChooseTimeDialogFragment build() {
            final ChooseTimeDialogFragment dialog = new ChooseTimeDialogFragment()
                    .setTitle(title)
                    .setListener(listener)
                    .setCurved(curved)
                    .setMinutesStep(minutesStep)
                    .setMaxDateRange(maxDate)
                    .setMinDateRange(minDate)
                    .setDefaultDate(defaultDate)
                    .setMustBeOnFuture(mustBeOnFuture);

            if (mainColor != null) {
                dialog.setMainColor(mainColor);
            }

            if (backgroundColor != null) {
                dialog.setBackgroundColor(backgroundColor);
            }

            if (titleTextColor != null) {
                dialog.setTitleTextColor(titleTextColor);
            }

            return dialog;
        }


    }

    private static final String tag = "SelectedContactFragment";

    public void show(FragmentActivity fragmentActivity) {
        show(fragmentActivity.getSupportFragmentManager());
    }

    public static void dismiss(FragmentActivity fragmentActivity) {
        dismiss(fragmentActivity.getSupportFragmentManager());
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, tag);
    }

    public static void dismiss(FragmentManager fragmentManager) {
        final Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null && fragment instanceof ChooseTimeDialogFragment) {
            ((ChooseTimeDialogFragment) fragment).dismiss();
        }
    }
}
