package com.gseasypro.app.school.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.GsBestPresenter;
import com.example.resources.bean.ItemGsBestBean;
import com.gseasypro.app.R;
import com.gseasypro.app.activity.BasePresenterActivity;
import com.gseasypro.app.adapter.school.GsBestAdapter;
import com.gseasypro.app.ioc.compoents.PresenterComponent;
import com.gseasypro.app.school.fragment.GsBestDetailFragment;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GsBestActivity extends BasePresenterActivity<GsBestPresenter, GsBestPresenter.GsBestIView>
        implements GsBestPresenter.GsBestIView, BaseQuickAdapter.RequestLoadMoreListener {

    private final static String FRAGMENT_TAG_GSBEST = "fragment_tag_detail";
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_best_list)
    RecyclerView mRvBestList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private GsBestAdapter adapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_gs_best);
        ButterKnife.bind(this);
        mTitleBar.setBackClick(this);
        mTitleBar.setLeftText("广师最佳");
        initViews();
        initListener();
    }


    private void initViews() {
        adapter = new GsBestAdapter(new ArrayList<ItemGsBestBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        adapter.openLoadMore(getPresenter().getLimit());
        adapter.setOnLoadMoreListener(this);
        mRvBestList.setLayoutManager(new LinearLayoutManager(this));
        mRvBestList.setAdapter(adapter);
        getPresenter().initGsBestData();

    }

    private void initListener() {
        mRvBestList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_GSBEST) == null)
                    GsBestDetailFragment.createInstance()
                            .show(getSupportFragmentManager(), FRAGMENT_TAG_GSBEST);
            }
        });
    }


    @Override
    public void onInitGsBestSuccess(List<ItemGsBestBean> data) {
        adapter.setNewData(data);
    }

    @Override
    public void onLoadSuccess(List<ItemGsBestBean> data) {

    }

    @Override
    public void onNoMoreData() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    protected void injectPresenter(PresenterComponent component, GsBestPresenter preseneter) {
        component.inject(preseneter);
    }
}
