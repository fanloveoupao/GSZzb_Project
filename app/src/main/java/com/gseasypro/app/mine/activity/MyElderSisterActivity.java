package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.presenter.MyElderSisterPresenter;
import com.example.resources.bean.MyElderSisterBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.mine.MyEideSisterAdapter;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.ui.widget.WaveView;
import app.gseasypro.com.utils.widget.ZoomOutPageTransformer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyElderSisterActivity extends PresenterActivity<MyElderSisterPresenter, MyElderSisterPresenter.MyElderSisterIView>
        implements MyElderSisterPresenter.MyElderSisterIView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.whew_view)
    WaveView mWhewView;
    @BindView(R.id.tv_project_count)
    TextView mTvProjectCount;
    @BindView(R.id.ll_network_error)
    LinearLayout mLlNetworkError;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.ll_no_project)
    LinearLayout mLlNoProject;
    @BindView(R.id.ll_none_publish)
    LinearLayout mLlNonePublish;
    @BindView(R.id.ll_no_suit)
    LinearLayout mLlNoSuit;
    @BindView(R.id.ll_use_finsh)
    LinearLayout mLlUseFinsh;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    @BindView(R.id.ll_hongniang_project_show)
    RelativeLayout mLlHongniangProjectShow;
    private MyEideSisterAdapter mAdapter;
    private List<View> mViewList;
    private final int STATE_SEARCH = 0;
    private final int STATE_SHOW = 1;
    private final int STATE_EMPTY = 2;
    private final int STATE_NOPUBLISH = 3;
    private final int STATE_NETWORKERROR = 4;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {


        setContentView(R.layout.activity_my_hongniang);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("师兄师姐");
        mTitleBar.setRightText("设置");
        mTitleBar.setBackClick(this);
        mViewList = new ArrayList<View>();
        mViewList.add(mLlSearch);
        mViewList.add(mLlHongniangProjectShow);
        mViewList.add(mLlEmpty);
        mViewList.add(mLlNonePublish);
        mViewList.add(mLlNetworkError);
        mTitleBar.mRightTextView.setVisibility(View.INVISIBLE);
        getPresenter().getSearchCacheTime();

    }

    private void initUi() {
        mTitleBar.mRightTextView.setVisibility(View.VISIBLE);
        mViewpager.setPageTransformer(true, new ZoomOutPageTransformer());
        mAdapter = new MyEideSisterAdapter(this, new ArrayList<MyElderSisterBean>());
        mViewpager.setAdapter(mAdapter);

    }

    private void initListener() {
        mTvProjectCount.setText((1) + "/" + (mAdapter.getCount()));
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvProjectCount.setText((position + 1) + "/" + (mAdapter.getCount()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onCanRefresh(boolean canRefresh) {
        if (canRefresh) {
            setViewState(STATE_SEARCH);
            searchProject();
        } else {
            getPresenter().initMyElderSisterData();
        }

    }

    public void setViewState(int state) {
        for (int i = 0; i < mViewList.size(); i++) {
            if (state == i)
                mViewList.get(i).setVisibility(View.VISIBLE);
            else
                mViewList.get(i).setVisibility(View.GONE);
        }
        if (state == STATE_SEARCH) {
            mWhewView.start();
            ImageLoader.loadIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg", mIvHead, true, true);
        }
    }


    private void searchProject() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().initMyElderSisterData();
            }
        }, 4000);
    }

    @Override
    public void onInitData(List<MyElderSisterBean> data) {
        initUi();
        mAdapter.setNewData(data);
        initListener();
        setViewState(STATE_SHOW);
    }

    @Override
    public void onNetWorkError() {
        setViewState(STATE_NETWORKERROR);
    }

    @Override
    public void onNonePublishProject() {
        setViewState(STATE_NOPUBLISH);
        mTitleBar.mRightTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProjectEmpty() {
        setViewState(STATE_EMPTY);
        mTitleBar.mRightTextView.setVisibility(View.VISIBLE);


    }

}
