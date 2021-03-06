package com.gseasypro.app.learn.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presenter.BlogStagePresenter;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.SubsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ProjectTypeAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;


public class BlogStageFragment extends DialogPresenterFragment<BlogStagePresenter, BlogStagePresenter.BlogStageIView>
        implements BlogStagePresenter.BlogStageIView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_blog_type)
    RecyclerView mRvBlogType;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private ProjectTypeAdapter mTypeAdapter;

    public static BlogStageFragment createInstance() {
        return new BlogStageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FragmentActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_blog_stage, container, false);
        ButterKnife.bind(this, mView);
        mTitleBar.setLeftText("博客类型");
        mTitleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTitleBar.setRightText("确定");
        mTitleBar.mRightTextView.setVisibility(View.VISIBLE);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mRvBlogType.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mTypeAdapter = new ProjectTypeAdapter(new ArrayList<SubsBean<BaseClassBean>>());
        mRvBlogType.setAdapter(mTypeAdapter);
        getPresenter().initBlogStage();
        return mView;
    }


    @Override
    public void initBlogStageSuccess(List<SubsBean<BaseClassBean>> mStageBeanList) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mTypeAdapter.setNewData(mStageBeanList);
    }
}
