package com.gseasypro.app.school.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.SearchNewsPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchNewsActivity extends PresenterActivity<SearchNewsPresenter, SearchNewsPresenter.SearchNewsIView>
        implements SearchNewsPresenter.SearchNewsIView {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_news);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("搜索新闻");
        mTitleBar.setBackClick(this);
    }

}
