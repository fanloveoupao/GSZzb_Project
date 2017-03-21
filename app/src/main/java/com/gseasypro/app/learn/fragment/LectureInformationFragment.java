package com.gseasypro.app.learn.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.LectureInformationFragmentPresenter;
import com.example.resources.bean.ProjectBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.mine.MyLectureAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class LectureInformationFragment extends PresenterFragment<LectureInformationFragmentPresenter,
        LectureInformationFragmentPresenter.LectureInformationFragmentView> implements
        LectureInformationFragmentPresenter.LectureInformationFragmentView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_lecture_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private MyLectureAdapter mAdapter;

    public static LectureInformationFragment createInstance() {
        return new LectureInformationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_lecture_information, container, false);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mAdapter = new MyLectureAdapter(new ArrayList<ProjectBean>());
        mAdapter.openLoadMore(getPresenter().getLimit());
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);
        getPresenter().initLectureInformation();
        initListener();
    }

    private void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {

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
