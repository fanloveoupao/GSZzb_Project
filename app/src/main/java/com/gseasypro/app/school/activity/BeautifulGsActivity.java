package com.gseasypro.app.school.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.presenter.BeautifulGsPresenter;
import com.example.resources.bean.BeautifulGsItemBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.BeautifulGsAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeautifulGsActivity extends PresenterActivity<BeautifulGsPresenter, BeautifulGsPresenter.BeautifulGsView>
        implements BeautifulGsPresenter.BeautifulGsView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_gs_list)
    RecyclerView mRvGsRecyclerList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private BeautifulGsAdapter gsAdapter;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_beautiful_gs);
        ButterKnife.bind(this);
        mTitleBar.setCenterText("最美广师");
        mTitleBar.setBackClick(this);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        gsAdapter = new BeautifulGsAdapter(new ArrayList<BeautifulGsItemBean>());
        mRvGsRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mRvGsRecyclerList.setAdapter(gsAdapter);
        initDatas();
    }

    private void initDatas() {
        getPresenter().requestData();
    }

    @Override
    public void requestSuccess(List<BeautifulGsItemBean> datas) {
        gsAdapter.setNewData(datas);
    }
}
