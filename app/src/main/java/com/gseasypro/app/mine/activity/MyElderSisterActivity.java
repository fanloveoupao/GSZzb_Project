package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.MyElderSisterPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyElderSisterActivity extends PresenterActivity<MyElderSisterPresenter, MyElderSisterPresenter.MyElderSisterIView>
        implements MyElderSisterPresenter.MyElderSisterIView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_elder_sister);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("师兄师姐");
        mTitleBar.setRightText("设置");
        mTitleBar.setBackClick(this);
    }


}
