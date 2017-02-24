package com.gseasypro.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.presenter.SplashPresenter;
import com.gseasypro.app.IndexActivity;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.widget.LauncherView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends PresenterActivity<SplashPresenter, SplashPresenter.SplashIView>
        implements SplashPresenter.SplashIView {


    @BindView(R.id.launcherView)
    LauncherView mLauncherView;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mLauncherView.setListener(new LauncherView.launcherDone() {
            @Override
            public void animEnd() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        launch(IndexActivity.class, true);
                    }
                }, 800);

            }
        });

    }


}
