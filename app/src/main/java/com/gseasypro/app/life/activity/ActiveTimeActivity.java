package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.presenter.ActiveTimePresenter;
import com.example.resources.bean.ActiveBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.life.ActiveTimeAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveTimeActivity extends PresenterActivity<ActiveTimePresenter, ActiveTimePresenter.ActiveTimeView>
        implements ActiveTimePresenter.ActiveTimeView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_activit_list)
    RecyclerView mRecyclerList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private ActiveTimeAdapter adapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_activie_time);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("活动优惠");
        mTitleBar.setBackClick(this);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        adapter = new ActiveTimeAdapter(new ArrayList<ActiveBean>());
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerList.setAdapter(adapter);
        initDatas();
        initListener();
    }

    private void initDatas() {
        getPresenter().requestData();
    }

    private void initListener() {

    }

    @Override
    public void requestSuccess(List<ActiveBean> datas) {
        adapter.setNewData(datas);
    }
}
