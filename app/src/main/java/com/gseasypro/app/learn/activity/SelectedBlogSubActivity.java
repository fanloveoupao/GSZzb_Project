package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.SelectedBlogSubPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedBlogSubActivity extends PresenterActivity<SelectedBlogSubPresenter, SelectedBlogSubPresenter.SelectedBlogSubIView>
        implements SelectedBlogSubPresenter.SelectedBlogSubIView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_selected_blog_sub);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        mTitleBar.setBackClick(this);
        mTitleBar.setLeftText("订阅管理");
    }

}
