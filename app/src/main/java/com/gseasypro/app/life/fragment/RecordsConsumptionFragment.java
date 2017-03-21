package com.gseasypro.app.life.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.RecordsConsumptionPresenter;
import com.example.resources.bean.RecordsConsumptionBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.life.RecordsConsumptionAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/3/16.
 */
public class RecordsConsumptionFragment extends DialogPresenterFragment<RecordsConsumptionPresenter, RecordsConsumptionPresenter.HtmlAnalyzerView>
        implements RecordsConsumptionPresenter.HtmlAnalyzerView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.iv_date_img)
    ImageView mIvDateImg;
    @BindView(R.id.ll_head_show)
    LinearLayout mLlHeadShow;
    @BindView(R.id.rv_rc_list)
    RecyclerView mRvRcList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private RecordsConsumptionAdapter adapter;

    public static RecordsConsumptionFragment creatInstance() {
        return new RecordsConsumptionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_records_consumption, container, false);
        ButterKnife.bind(this, mView);
        mTitleBar.setLeftText("消费记录");
        mTitleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        adapter = new RecordsConsumptionAdapter(new ArrayList<RecordsConsumptionBean>());
        mRvRcList.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        adapter.openLoadMore(getPresenter().getLimit());
        adapter.openLoadAnimation();
        adapter.setOnLoadMoreListener(this);
        mRvRcList.setAdapter(adapter);
        getPresenter().loadMoreData();
        initListener();
        return mView;
    }

    private void initListener() {
        mRvRcList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });

    }


    @Override
    public void onLoadMoreDataSuccess(List<RecordsConsumptionBean> data) {
        adapter.addData(data);
    }

    @Override
    public void onLoadError() {

    }

    @Override
    public void onNoMoreData() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
