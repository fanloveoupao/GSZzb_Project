package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.presenter.PublicRepairSuccessPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublicRepairSuccessActivity extends PresenterActivity<PublicRepairSuccessPresenter, PublicRepairSuccessPresenter.PublicRepairSuccessView>
        implements PublicRepairSuccessPresenter.PublicRepairSuccessView {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_public_repair_success);
        ButterKnife.bind(this);
        titlebar.setLeftText("发布成功");
        titlebar.setBackClick(this);
    }


    @OnClick({R.id.btn_connect, R.id.btn_look})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                break;
            case R.id.btn_look:
                break;
        }
    }
}
