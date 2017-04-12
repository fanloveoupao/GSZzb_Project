package com.gseasypro.app.school.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.presenter.ExperiencePredecessorsPresenter;
import com.example.resources.bean.ExperiencePredecessorsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ExperiencePredecessorsAdapter;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ScreenUtils;
import app.gseasypro.com.utils.ToastUtils;
import app.gseasypro.com.utils.utils.ViewUtils;
import app.gseasypro.com.utils.widget.DisableableAppBarLayoutBehavior;
import app.gseasypro.com.utils.widget.MRelativelayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ExperiencePredecessorsActivity extends PresenterActivity<ExperiencePredecessorsPresenter, ExperiencePredecessorsPresenter.ExperiencePredecessorsView>
        implements ExperiencePredecessorsPresenter.ExperiencePredecessorsView {
    @BindView(R.id.btn_back1)
    RelativeLayout mBtnBack1;
    @BindView(R.id.btn_search1)
    LinearLayout mBtnSearch1;
    @BindView(R.id.btn_back2)
    RelativeLayout mBtnBack2;
    @BindView(R.id.btn_search2)
    LinearLayout mBtnSearch2;
    @BindView(R.id.collaps_bar)
    CollapsingToolbarLayout mCollapsBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.rv_certification_project)
    RecyclerView mRvCertificationProject;
    @BindView(R.id.title_bar)
    LinearLayout mTitleBar;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.rl_header_certification)
    MRelativelayout mRlHeaderCertification;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    @BindView(R.id.btn_reload_data)
    Button mBtnReload;
    @BindView(R.id.ll_error_network)
    LinearLayout mLlNetErrorError;
    @BindView(R.id.coodinator_layout)
    CoordinatorLayout mCoodinatorLayout;
    @BindView(R.id.tv_has_look_count1)
    TextView mTvHasLookCount1;
    @BindView(R.id.tv_has_look_count2)
    TextView mTvHasLookCount2;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.flabutton_publish)
    FloatingActionButton mFlabuttonPublish;

    private ExperiencePredecessorsAdapter mProjectAdapter;
    private View[] mStateViews;
    private DisableableAppBarLayoutBehavior mBehavior;


    private enum ViewState {
        CONTENT(0),
        EMPTY(1),
        ERROR(2);

        private int state;

        ViewState(int state) {
            this.state = state;
        }
    }


    private enum CollapsingToolbarLayoutState {
        EXPANED,//展开
        COLLAPSED,//折叠
        INTERNEDIATE//中间
    }

    private CollapsingToolbarLayoutState state;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_experience_ofsister);
        ButterKnife.bind(this);
        mStateViews = new View[]{mSwipeLayout, mLlEmptyView, mLlNetErrorError};
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        if (Build.VERSION.SDK_INT >= 21) {
            ViewUtils.setViewHeight(mCollapsBar, mCollapsBar.getLayoutParams().height + ScreenUtils.getStausBarHeight());
        }

        CoordinatorLayout.LayoutParams mLayoutParams = (CoordinatorLayout.LayoutParams) mAppbarLayout.getLayoutParams();
        mBehavior = (DisableableAppBarLayoutBehavior) mLayoutParams.getBehavior();

        List<ExperiencePredecessorsBean> list = new ArrayList<>();

        mRvCertificationProject.setLayoutManager(new LinearLayoutManager(this));
        mProjectAdapter = new ExperiencePredecessorsAdapter(this, list);
        mProjectAdapter.openLoadMore(getPresenter().getLimit());
        mRvCertificationProject.setAdapter(mProjectAdapter);
        initListener();
        getPresenter().refreshData(false);
    }

    private void initListener() {
        mAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANED) {
                        state = CollapsingToolbarLayoutState.EXPANED;//修改状态为展开
                    }

                    mToolbar.setVisibility(GONE);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mTitleBar.setVisibility(VISIBLE);
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                    }
                    mToolbar.setVisibility(VISIBLE);
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            mTitleBar.setVisibility(VISIBLE);
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }

                    mToolbar.setVisibility(VISIBLE);
                }
            }
        });

        mProjectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().loadMoreData();
            }
        });
        mRvCertificationProject.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(-1)) {
                    mAppbarLayout.setExpanded(true);
                }
                if (dy < 0) { //上滑

                } else if (dy > 0) {//下滑

                }
            }
        });
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refreshData(true);
            }
        });


    }

    public void setViewState(ViewState state) {
        for (int i = 0; i < mStateViews.length; i++) {
            if (i == state.state)
                mStateViews[i].setVisibility(VISIBLE);
            else mStateViews[i].setVisibility(GONE);

        }
    }
    @OnClick({R.id.btn_back1,  R.id.btn_back2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back1:
            case R.id.btn_back2:
                finish();
                break;

        }
    }
    @Override
    public void onNetWorkExceptionToast() {
        ToastUtils.showShort(this, getString(R.string.network_errror));
    }

    @Override
    public void onNetWorkExceptionError() {

    }

    @Override
    public void onRefreshDataSuccess(List<ExperiencePredecessorsBean> list) {
        mSwipeLayout.setRefreshing(false);
        setViewState(ViewState.CONTENT);
        mProjectAdapter.setNewData(list);
    }


    @Override
    public void onRefreshDataEmpty() {
        setViewState(ViewState.EMPTY);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onNoMoreData() {
        mSwipeLayout.setRefreshing(false);
        mProjectAdapter.showLoadMoreFailedView();
    }

    @Override
    public void onLoadMoreSuccess(List<ExperiencePredecessorsBean> list) {
        mProjectAdapter.addData(list);
    }

    @Override
    public void onLoadMoreDataError() {
        mProjectAdapter.showLoadMoreFailedView();
    }


}
