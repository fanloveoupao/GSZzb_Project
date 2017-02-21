package com.gseasypro.app.life;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.IndexLifePresenter;
import com.example.resources.bean.ItemLifeBean;
import com.gseasypro.app.R;
import com.gseasypro.app.activity.H5Activity;
import com.gseasypro.app.adapter.life.IndexLifeAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterFragment;
import app.gseasypro.com.utils.utils.TypedValueUtil;
import app.gseasypro.com.utils.widget.GridItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexLifeFragment extends PresenterFragment<IndexLifePresenter, IndexLifePresenter.IndexLifeIView>
        implements IndexLifePresenter.IndexLifeIView {


    @BindView(R.id.rvLifelist)
    RecyclerView mRvLifelist;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private IndexLifeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_index_life, container, false);
        ButterKnife.bind(this, mView);
        initViews();
        return mView;
    }

    private void initViews() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mRvLifelist.setLayoutManager(new GridLayoutManager(getBaseActivity(), 2));
        mRvLifelist.addItemDecoration(new GridItemDecoration(TypedValueUtil.fromDip(10f)));
        adapter = new IndexLifeAdapter(new ArrayList<ItemLifeBean>());
        mRvLifelist.setAdapter(adapter);
        getPresenter().initLifeItem();
        mRvLifelist.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                H5Activity.launch(getBaseActivity(), "www.gdin.edu.cn");
            }
        });
    }

    @Override
    public void onDataSuccess(List<ItemLifeBean> lifeBeanList) {
        List<ItemLifeBean> beanList = new ArrayList<>();
        ItemLifeBean lifeBean = new ItemLifeBean();
        lifeBean.imageId = R.mipmap.life_one;
        lifeBean.tag = "one";
        lifeBean.itemTag = "INBOX";
        lifeBean.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.blue_2277dd);
        //
        ItemLifeBean lifeBean1 = new ItemLifeBean();
        lifeBean1.imageId = R.mipmap.life_two;
        lifeBean1.tag = "two";
        lifeBean1.itemTag = "FLAGGED";
        lifeBean1.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.orange_dd9900);
        //
        ItemLifeBean lifeBean2 = new ItemLifeBean();
        lifeBean2.imageId = R.mipmap.life_three;
        lifeBean2.tag = "three";
        lifeBean2.itemTag = "TODAY";
        lifeBean2.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.green_00aa33);
        //
        ItemLifeBean lifeBean3 = new ItemLifeBean();
        lifeBean3.imageId = R.mipmap.life_four;
        lifeBean3.tag = "four";
        lifeBean3.itemTag = "7 DAYS";
        lifeBean3.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.black_000000);
        //
        ItemLifeBean lifeBean4 = new ItemLifeBean();
        lifeBean4.imageId = R.mipmap.life_five;
        lifeBean4.tag = "five";
        lifeBean4.itemTag = "SCHEDOLD";
        lifeBean4.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.yellow_b67c00);
        //
        ItemLifeBean lifeBean5 = new ItemLifeBean();
        lifeBean5.imageId = R.mipmap.life_six;
        lifeBean5.tag = "six";
        lifeBean5.itemTag = "OVERDUE";
        lifeBean5.tagColors = ContextCompat.getColor(getBaseActivity(), R.color.red_990000);

        beanList.add(lifeBean);
        beanList.add(lifeBean1);
        beanList.add(lifeBean2);
        beanList.add(lifeBean3);
        beanList.add(lifeBean4);
        beanList.add(lifeBean5);
        adapter.setNewData(beanList);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }
}

