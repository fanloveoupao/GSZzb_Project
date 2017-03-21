package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.ScoreQueryPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreQueryActivity extends PresenterActivity<ScoreQueryPresenter, ScoreQueryPresenter.ScoreQueryView>
        implements ScoreQueryPresenter.ScoreQueryView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_score_query);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("成绩查询");
        mTitleBar.setBackClick(this);
    }

}
