package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.MyLecturePresenter;
import com.example.resources.bean.ProjectBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.mine.MyLectureAdapter;
import com.gseasypro.app.mine.fragment.SeatTableViewFragment;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLectureActivity extends PresenterActivity<MyLecturePresenter, MyLecturePresenter.MyLectureIView>
        implements MyLecturePresenter.MyLectureIView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private MyLectureAdapter mAdapter;
    private String SEATABLE = "seatable_view";

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_lecture);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("我的讲座");
        mTitleBar.setBackClick(this);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyLectureAdapter(new ArrayList<ProjectBean>());
        mAdapter.openLoadMore(getPresenter().getLimit());
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);
        getPresenter().initMyLectureData();
        initListener();
    }

    private void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (getSupportFragmentManager().findFragmentByTag(SEATABLE) == null)
                    SeatTableViewFragment.createInstance().show(getSupportFragmentManager(), SEATABLE);
            }
        });
    }

    @Override
    public void oninitSuccess(List<ProjectBean> data) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mAdapter.setNewData(data);
    }

    @Override
    public void onLoadMoreData(List<ProjectBean> data) {

    }

    @Override
    public void onNoMoreData() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onNetErro() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
