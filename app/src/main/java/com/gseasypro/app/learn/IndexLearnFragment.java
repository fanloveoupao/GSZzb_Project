package com.gseasypro.app.learn;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.presenter.IndexLearnPresenter;
import com.example.resources.bean.ProjectBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ProjectListAdapter;
import com.gseasypro.app.learn.activity.SelectedBlogSubActivity;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/2/9.
 */
public class IndexLearnFragment extends PresenterFragment<IndexLearnPresenter, IndexLearnPresenter.IndexLearnIView>
        implements IndexLearnPresenter.IndexLearnIView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rvGsblogList)
    RecyclerView mRvGsblogList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private ProjectListAdapter mBlogListAdapter;
    private View mHeadView;
    private TextView mTvtextSub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_index_learn, container, false);
        mHeadView = inflater.inflate(R.layout.layout_learn_head, container, false);
        ButterKnife.bind(this, mView);
        initViews();
        getPresenter().initBlogList();
        initListener();
        return mView;
    }


    private void initViews() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mBlogListAdapter = new ProjectListAdapter(new ArrayList<ProjectBean>());
        mRvGsblogList.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mBlogListAdapter.addHeaderView(mHeadView);
        mBlogListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mBlogListAdapter.openLoadMore(getPresenter().getLimit());
        mBlogListAdapter.setOnLoadMoreListener(this);
        mRvGsblogList.setAdapter(mBlogListAdapter);
        mTvtextSub = (TextView) mHeadView.findViewById(R.id.text_sub);
    }

    private void initListener() {
        mTvtextSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().launch(SelectedBlogSubActivity.class, false);
            }
        });
    }

    @Override
    public void onInitBlogListSuccess(List<ProjectBean> data) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mBlogListAdapter.setNewData(data);
    }

    @Override
    public void onLoadMoreDataSuccess(List<ProjectBean> data) {
        mBlogListAdapter.addData(data);
    }

    @Override
    public void onNoMoreData() {

    }

    @Override
    public void onLoadMoreDataFailed() {

    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().loadMoreData(mBlogListAdapter.getItemCount());
    }

}
