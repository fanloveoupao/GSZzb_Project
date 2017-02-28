package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.CollegeTwoLeavePresenter;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.ParentBean;
import com.example.resources.bean.SubsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.widget.DoubleRecyclerView;
import com.kennyc.view.MultiStateView;

import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CollegeTwoLeaveActivity extends PresenterActivity<CollegeTwoLeavePresenter, CollegeTwoLeavePresenter.CollegeTwoLeaveIView>
        implements CollegeTwoLeavePresenter.CollegeTwoLeaveIView {


    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.xueyuan_view)
    DoubleRecyclerView mDbRecyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_college_two_leave);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("选择学院");
        mTitleBar.setBackClick(this);
        getPresenter().initData();
    }

    @Override
    public void onInitSuccess(List<ParentBean<BaseClassBean, SubsBean<BaseClassBean>>> mAreaParents) {
        mDbRecyclerView.initData(mAreaParents, null, false);
    }

    @Override
    public void onNoData() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }
}
