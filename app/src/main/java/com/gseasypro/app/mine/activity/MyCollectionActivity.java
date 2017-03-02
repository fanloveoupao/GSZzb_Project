package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.MyCollectionPresenter;
import com.example.resources.bean.CollectionProjectBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.mine.CollectionProjectAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.dialog.BaseListDialogFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectionActivity extends PresenterActivity<MyCollectionPresenter, MyCollectionPresenter.MyCollectionIView>
        implements MyCollectionPresenter.MyCollectionIView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_collection)
    RecyclerView mRvCollection;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;

    private List<CollectionProjectBean> mCollectionList = new ArrayList<CollectionProjectBean>();
    private CollectionProjectAdapter mAdapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("我的收藏");
        mTitleBar.setBackClick(this);
        getPresenter().getCollectionData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvCollection.setLayoutManager(manager);
        mAdapter = new CollectionProjectAdapter(mCollectionList);
        mAdapter.openLoadMore(getPresenter().getLimit());
        mAdapter.setOnLoadMoreListener(this);
        mRvCollection.setAdapter(mAdapter);
        initListener();
    }

    private void initListener() {
        mRvCollection.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                super.onItemLongClick(adapter, view, position);
                DialogUtil.softNotitleListDialog(MyCollectionActivity.this, new String[]{"取消收藏"}, new BaseListDialogFragment
                        .DialogItemClickListener() {

                    @Override
                    public void itemClickCallBack(BaseListDialogFragment dialogFragment, String itemText, final int position) {
                        if (position == 0) {
                            getPresenter().deleteCollection(mCollectionList.get(position).cid, position);
                        }
                    }
                });

            }
        });
    }


    @Override
    public void onNoMoreData() {
        mAdapter.removeAllFooterView();
        View view = LayoutInflater.from(this).inflate(R.layout.layout_no_more_data,
                (ViewGroup) mRvCollection.getParent(), false);
        mAdapter.addFooterView(view);
    }

    @Override
    public void onNoCollection() {

    }

    @Override
    public void onLoadMoreDataError() {

    }

    @Override
    public void onLoadDataSuccess(List<CollectionProjectBean> projectBeanList) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mAdapter.addData(projectBeanList);
    }

    @Override
    public void onDeleteSuccess(int position) {

    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().getCollectionData();
    }
}
