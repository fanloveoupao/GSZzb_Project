package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.presenter.SelectedBlogSubPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectedBlogSubActivity extends PresenterActivity<SelectedBlogSubPresenter, SelectedBlogSubPresenter.SelectedBlogSubIView>
        implements SelectedBlogSubPresenter.SelectedBlogSubIView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.project_area_layout)
    RelativeLayout mBlogXyLayout;
    @BindView(R.id.project_type_layout)
    RelativeLayout mBlogTypeLayout;
    @BindView(R.id.project_stage_layout)
    RelativeLayout mBlogStageLayout;
    @BindView(R.id.project_keywords_layout)
    RelativeLayout mBlogKeywordsLayout;


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

    @OnClick({R.id.project_area_layout, R.id.project_type_layout, R.id.project_stage_layout, R.id.project_keywords_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.project_area_layout:
                //学院
                launch(CollegeTwoLeaveActivity.class, false);
                break;
            case R.id.project_type_layout:
                break;
            case R.id.project_stage_layout:
                break;
            case R.id.project_keywords_layout:
                break;
        }
    }
}
