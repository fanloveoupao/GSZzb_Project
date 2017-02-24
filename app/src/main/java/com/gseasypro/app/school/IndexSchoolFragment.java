package com.gseasypro.app.school;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lang.StringUtil;
import com.example.presenter.IndexSchoolPresenter;
import com.example.resources.bean.AdvertisementsBean;
import com.example.resources.bean.ProjectBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ProjectListAdapter;
import com.gseasypro.app.widget.CommonDataFilterView;
import com.gseasypro.app.widget.LocalImageHolderView;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterFragment;
import app.gseasypro.com.utils.ToastUtils;
import app.gseasypro.com.utils.ui.widget.AdvertisementBanner;
import app.gseasypro.com.utils.ui.widget.DotProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexSchoolFragment extends PresenterFragment<IndexSchoolPresenter, IndexSchoolPresenter.IndexSchoolView>
        implements IndexSchoolPresenter.IndexSchoolView, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.province_line)
    TextView mProvinceLine;
    @BindView(R.id.alltype_line)
    TextView mAlltypeLine;
    @BindView(R.id.allstage_line)
    TextView mAllstageLine;
    @BindView(R.id.title_line_layout)
    LinearLayout mTitleLineLayout;
    @BindView(R.id.province_text)
    TextView mProvinceText;
    @BindView(R.id.image_province)
    ImageView mImageProvince;
    @BindView(R.id.ll_projetct_area)
    LinearLayout mLlProjetctArea;
    @BindView(R.id.alltype_text)
    TextView mAlltypeText;
    @BindView(R.id.image_type)
    ImageView mImageType;
    @BindView(R.id.ll_project_type)
    LinearLayout mLlProjectType;
    @BindView(R.id.allstage_text)
    TextView mAllstageText;
    @BindView(R.id.image_stage)
    ImageView mImageStage;
    @BindView(R.id.ll_project_stage)
    LinearLayout mLlProjectStage;
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.rl_top_filter)
    RelativeLayout mRlTopFilter;
    @BindView(R.id.rc_project_list)
    RecyclerView mRcProjectList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    @BindView(R.id.tv_best_choose)
    ImageView mTvBestChoose;
    @BindView(R.id.iv_red_point)
    ImageView mIvRedPoint;
    @BindView(R.id.tv_hongniang_count)
    TextView mTvHongniangCount;
    @BindView(R.id.rl_hongniang)
    RelativeLayout mRlHongniang;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.progressbar)
    DotProgressBar mProgressBar;
    @BindView(R.id.commondata_filterview)
    CommonDataFilterView mCommondataFilterView;
    @BindView(R.id.tv_all_project_count)
    TextView mTvAllProjectCount;
    @BindView(R.id.ll_all_project_count)
    LinearLayout mLlAllProjectCount;
    @BindView(R.id.ll_parent)
    RelativeLayout mLlParent;
    @BindView(R.id.line_all_project_count)
    View mLineAllProjectCount;
    private final int TAG_AREA = 0x111;
    private final int TAG_TYPE = 0x112;
    private final int TAG_STAGE = 0x113;
    private List<String> mTypeNos = new ArrayList<>();
    private List<String> mStageNos = new ArrayList<>();
    private final List<String> mAreaNos = new ArrayList<>();
    private List<ProjectBean> mProjectBeanList = new ArrayList<>();

    private ProjectListAdapter mProjectListAdapter;
    private AdvertisementBanner mBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_index_school, container, false);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    private void initView() {
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRcProjectList.setLayoutManager(manager);
        mProjectListAdapter = new ProjectListAdapter(mProjectBeanList);
        mProjectListAdapter.openLoadMore(getPresenter().getLimit());
        mProjectListAdapter.setOnLoadMoreListener(this);
        mProjectListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRcProjectList.setAdapter(mProjectListAdapter);
        initHeader();
        initListener();
        getPresenter().refresh(false, false);//加载网络数据
        getPresenter().getHeaderListBean();//加载头部轮播图
        mCommondataFilterView.loadData(getPresenter());//加载地区等数据
    }

    private void initHeader() {
        View mHeader = LayoutInflater.from(getActivity()).inflate(R.layout.circle_page_header, null, false);
        mBanner = (AdvertisementBanner) mHeader.findViewById(R.id.banner);
        mProjectListAdapter.addHeaderView(mHeader);
    }


    private void initListener() {
        mRcProjectList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.btn_reload).setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData(true);
            }
        });

        mCommondataFilterView.setOnSubmitListener(new CommonDataFilterView.OnFilterListener() {
            @Override
            public void submit(List<String> areaList, List<String> typeList, List<String> stageList, String areaStr, String typeStr,
                               String stageStr) {
                mAreaNos.clear();
                mAreaNos.addAll(areaList);
                mTypeNos.clear();
                mTypeNos.addAll(typeList);
                mStageNos.clear();
                mStageNos.addAll(stageList);
                mProvinceText.setText(StringUtil.isNullOrEmpty(areaStr) ? "专业" : areaStr);
                mAlltypeText.setText(StringUtil.isNullOrEmpty(typeStr) ? "类型" : typeStr);
                mAllstageText.setText(StringUtil.isNullOrEmpty(stageStr) ? "校区" : stageStr);
                refreshData(true);
            }

            @Override
            public void viewGone() {
                setColor(TAG_AREA, false);
                setColor(TAG_TYPE, false);
                setColor(TAG_STAGE, false);
            }
        });
    }

    private void refreshData(final boolean reset) {
        if (reset) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mSwipeLayout.setRefreshing(true);
        }
        getPresenter().refresh(reset, true);
    }

    @OnClick(R.id.ll_projetct_area)
    public void chooseArea() {

        if (mCommondataFilterView.getVisibility() == View.VISIBLE) {
            if (mCommondataFilterView.getViewState() == mCommondataFilterView.AREA) {
                mCommondataFilterView.setVisibility(GONE);
                setColor(TAG_AREA, false);
            } else {
                mCommondataFilterView.setViewState(mCommondataFilterView.AREA);
                setColor(TAG_AREA, true);
            }
        } else {
            mCommondataFilterView.setVisibility(View.VISIBLE);
            mCommondataFilterView.setViewState(mCommondataFilterView.AREA);
            setColor(TAG_AREA, true);
        }
    }

    @OnClick(R.id.ll_project_type)
    public void chooseType() {

        if (mCommondataFilterView.getVisibility() == View.VISIBLE) {
            if (mCommondataFilterView.getViewState() == mCommondataFilterView.TYPE) {
                mCommondataFilterView.setVisibility(GONE);
                setColor(TAG_TYPE, false);
            } else {
                mCommondataFilterView.setViewState(mCommondataFilterView.TYPE);
                setColor(TAG_TYPE, true);
            }
        } else {
            mCommondataFilterView.setVisibility(View.VISIBLE);
            mCommondataFilterView.setViewState(mCommondataFilterView.TYPE);
            setColor(TAG_TYPE, true);
        }
    }

    @OnClick(R.id.ll_project_stage)
    public void chooseStage() {

        if (mCommondataFilterView.getVisibility() == View.VISIBLE) {
            if (mCommondataFilterView.getViewState() == mCommondataFilterView.STAGE) {
                mCommondataFilterView.setVisibility(GONE);
                setColor(TAG_STAGE, false);
            } else {
                mCommondataFilterView.setViewState(mCommondataFilterView.STAGE);
                setColor(TAG_STAGE, true);
            }
        } else {
            mCommondataFilterView.setVisibility(View.VISIBLE);
            mCommondataFilterView.setViewState(mCommondataFilterView.STAGE);
            setColor(TAG_STAGE, true);
        }
    }


    private void setColor(int tag, boolean selected) {
        switch (tag) {
            case TAG_AREA:
                if (selected) {
                    mTitleLineLayout.setVisibility(View.VISIBLE);
                    mProvinceLine.setVisibility(View.VISIBLE);
                    mImageProvince.setBackgroundResource(R.mipmap.sprinner_orange_up);
                    mProvinceText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.orange_ff5500));

                    mAlltypeLine.setVisibility(View.INVISIBLE);
                    mImageType.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAlltypeText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));

                    mAllstageLine.setVisibility(View.INVISIBLE);
                    mImageStage.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAllstageText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                } else {
                    mTitleLineLayout.setVisibility(GONE);
                    mProvinceLine.setVisibility(View.INVISIBLE);
                    mImageProvince.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mProvinceText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                }
                break;
            case TAG_TYPE:
                if (selected) {
                    mTitleLineLayout.setVisibility(View.VISIBLE);
                    mAlltypeLine.setVisibility(View.VISIBLE);
                    mImageType.setBackgroundResource(R.mipmap.sprinner_orange_up);
                    mAlltypeText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.orange_ff5500));

                    mProvinceLine.setVisibility(View.INVISIBLE);
                    mImageProvince.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mProvinceText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));

                    mAllstageLine.setVisibility(View.INVISIBLE);
                    mImageStage.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAllstageText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                } else {
                    mTitleLineLayout.setVisibility(GONE);
                    mAlltypeLine.setVisibility(View.INVISIBLE);
                    mImageType.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAlltypeText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                }
                break;
            case TAG_STAGE:
                if (selected) {
                    mTitleLineLayout.setVisibility(View.VISIBLE);
                    mAllstageLine.setVisibility(View.VISIBLE);
                    mImageStage.setBackgroundResource(R.mipmap.sprinner_orange_up);
                    mAllstageText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.orange_ff5500));

                    mProvinceLine.setVisibility(View.INVISIBLE);
                    mImageProvince.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mProvinceText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));

                    mAlltypeLine.setVisibility(View.INVISIBLE);
                    mImageType.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAlltypeText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                } else {
                    mTitleLineLayout.setVisibility(GONE);
                    mAllstageLine.setVisibility(View.INVISIBLE);
                    mImageStage.setBackgroundResource(R.mipmap.sprinner_gray_down);
                    mAllstageText.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.black_555555));
                }
                break;
        }
    }

    @Override
    public List<String> getAreaNos() {
        return mAreaNos;
    }

    @Override
    public List<String> getTypeNos() {
        return mTypeNos;
    }

    @Override
    public List<String> getStageNos() {
        return mStageNos;
    }

    @Override
    public void onLoadDataException(String e) {
        ToastUtils.showShort(getActivity(), e);
    }

    @Override
    public List<ProjectBean> getMProjectList() {
        return mProjectBeanList;
    }

    @Override
    public void onRefreshDataSuccess(boolean isShowAdvertisement) {
        if (isShowAdvertisement) {
            mBanner.setVisibility(View.VISIBLE);
        } else {
            mBanner.setVisibility(View.GONE);
        }
        mSwipeLayout.setRefreshing(false);
        mProjectListAdapter.notifyDataSetChanged();
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);

    }

    @Override
    public void onProgressBarGone() {
        mProgressBar.setVisibility(GONE);

    }

    @Override
    public void onSetViewEmpty() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void onSetViewContent() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void onSetNetworkError() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onSetContentError() {
        mSwipeLayout.setRefreshing(false);
        ToastUtils.showShort(getActivity(), "网络异常，请检查网络");
    }

    @Override
    public void onLoadMoreSuccess(List<ProjectBean> projectBeanList) {
        mProjectListAdapter.addData(projectBeanList);
    }

    @Override
    public void onGetAdevertiseSuccess(List<AdvertisementsBean> advertisementsBeens) {
        mBanner.setVisibility(View.VISIBLE);
        mBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, advertisementsBeens);
        mBanner.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mBanner.startTurning(5000);
    }

    @Override
    public void onRefresh() {
        refreshData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().loadMoreData();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null)
            mBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (mBanner != null)
            mBanner.stopTurning();
    }
}
