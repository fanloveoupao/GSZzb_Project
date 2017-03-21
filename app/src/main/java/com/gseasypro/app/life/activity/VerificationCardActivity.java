package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lang.StringUtil;
import com.example.presenter.VerificationCardPresenter;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.pinlockview.IndicatorDots;
import app.gseasypro.com.utils.widget.pinlockview.PinLockListener;
import app.gseasypro.com.utils.widget.pinlockview.PinLockView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationCardActivity extends PresenterActivity<VerificationCardPresenter, VerificationCardPresenter.VerificationCardView>
        implements VerificationCardPresenter.VerificationCardView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.profile_image)
    ImageView mIvProfileImage;
    @BindView(R.id.profile_name)
    TextView mTvprofileName;
    @BindView(R.id.indicator_dots)
    IndicatorDots mIndicatorDots;
    @BindView(R.id.pin_lock_view)
    PinLockView mPinLockView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_verification);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("输入密码");
        mTitleBar.setBackClick(this);
        ImageLoader.loadIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg", mIvProfileImage, true, true);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLength(6);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white_ffffff));
        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
        initListener();
    }

    private void initListener() {
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                mTvprofileName.setText("Welcome");
                if (StringUtil.equals(pin, "123456")) {
                    launch(OneCardSolutionActivity.class, false);
                    finish();
                } else {
                    mTvprofileName.setText("密码错误");
                }

            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {

            }
        });
    }
}
