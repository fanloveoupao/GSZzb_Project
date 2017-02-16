package app.gseasypro.com.utils.ui.widget;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.gseasypro.com.utils.R;
import app.gseasypro.com.utils.ui.KeyBoardUtils;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class TitleBar extends LinearLayout {

    TextView mLeftTextView;
    TextView mCenterTextView;
    public TextView mRightTextView;
    public View mBackView;
    TextView mRightLine;
    TextView mLeftLine;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.titlebar_view, this);

        mLeftTextView = (TextView) findViewById(R.id.leftTextView);
        mCenterTextView = (TextView) findViewById(R.id.centerTextView);
        mRightTextView = (TextView) findViewById(R.id.rightTextView);
        mBackView = findViewById(R.id.backView);
        mRightLine = (TextView) findViewById(R.id.right_line);
        mLeftLine = (TextView) findViewById(R.id.left_line);
    }


    public void setBackView(int drawableId) {
        ViewUtils.setLeftDrawable(mLeftTextView, getResources().getDrawable(drawableId));
    }

    public void setBackViewIsBack() {
        setBackView(R.drawable.arrow_white_left);
    }

    public void setBackViewIsClose() {
        setBackView(R.drawable.icon_close);
    }

    public void setBackViewOnClickListener(OnClickListener listener) {
        mBackView.setOnClickListener(listener);
    }

    public void setBackViewIsGone() {
        mBackView.setVisibility(View.GONE);
        mLeftLine.setVisibility(View.GONE);
    }

    public void setLeftLineVisible(boolean isVisible) {
        mLeftLine.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setRightLineVisible(boolean isVisible) {
        mRightLine.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setBackClick(final Activity activity) {
        mBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.hideKeyBoard(activity);
                activity.finish();
            }
        });
    }

    public void setBackClick(final DialogFragment fragment) {
        mBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.hideKeyBoard(fragment.getActivity());
                fragment.dismiss();
            }
        });
    }


    public void setLeftText(String text) {
        mLeftTextView.setText(text);
        mLeftTextView.setVisibility(VISIBLE);
        mCenterTextView.setVisibility(GONE);
        ViewUtils.clearCompoundDrawable(mLeftTextView);
    }

    public void setCenterText(String text) {
        mCenterTextView.setText(text);
        mCenterTextView.setVisibility(VISIBLE);
        mLeftTextView.setVisibility(GONE);
        ViewUtils.clearCompoundDrawable(mCenterTextView);
    }

    public void setLeftViewOnClickListener(OnClickListener listener) {
        mLeftTextView.setOnClickListener(listener);
    }

    public void setLeftViewIsGone() {
        mLeftTextView.setVisibility(View.INVISIBLE);
    }


    public void setRightText(String text) {
        mRightTextView.setText(text);
        ViewUtils.clearCompoundDrawable(mRightTextView);
    }

    public void setRightView(@DrawableRes int drawableId) {
        ViewUtils.setLeftDrawable(mRightTextView, getResources().getDrawable(drawableId));
    }

    public void setRightView(String text, int drawableId) {
        mRightTextView.setText(text);
        ViewUtils.setLeftDrawable(mRightTextView, getResources().getDrawable(drawableId));
    }

    public void setRightViewOnClickListener(OnClickListener listener) {
        mRightTextView.setOnClickListener(listener);
    }

    public void setRightViewIsGone() {
        mRightTextView.setVisibility(View.INVISIBLE);
        mRightLine.setVisibility(View.INVISIBLE);
    }

    public void setRightViewIsVisible() {
        mRightTextView.setVisibility(View.VISIBLE);
        mRightLine.setVisibility(View.VISIBLE);
    }

    public void setRightViewClickable(boolean clickable) {
        mRightTextView.setClickable(clickable);
    }
}

