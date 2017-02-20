package com.gseasypro.app.school.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.lang.StringUtil;
import com.example.presenter.GsCommentsPresenter;
import com.example.resources.bean.GsCommentsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.GsCommentsAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.SendCommentButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GsCommentsActivity extends PresenterActivity<GsCommentsPresenter, GsCommentsPresenter.GsCommentsView>
        implements GsCommentsPresenter.GsCommentsView {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_common_list)
    RecyclerView mRvCommonList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    @BindView(R.id.etComment)
    EditText metComment;
    @BindView(R.id.btnSendComment)
    SendCommentButton mbtnSendComment;
    private GsCommentsAdapter gsCommentsAdapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_gs_comments);
        ButterKnife.bind(this);
        mTitleBar.setBackClick(this);
        mTitleBar.setLeftText("评论");
        gsCommentsAdapter = new GsCommentsAdapter(new ArrayList<GsCommentsBean>());
        mRvCommonList.setLayoutManager(new LinearLayoutManager(this));
        mRvCommonList.setAdapter(gsCommentsAdapter);
        getPresenter().fillCommentList();
        initListener();
    }

    private void initListener() {
        mRvCommonList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyBoard();
                return false;
            }
        });
        mbtnSendComment.setOnSendClickListener(new SendCommentButton.OnSendClickListener() {
            @Override
            public void onSendClickListener(View v) {
                if (StringUtil.isNullOrEmpty(metComment.getText().toString())) {
                    mbtnSendComment.startAnimation(AnimationUtils.loadAnimation(GsCommentsActivity.this, R.anim.shake_error));
                } else {
                    getPresenter().sendComments(metComment.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void initCommentsDatas(List<GsCommentsBean> list) {
        gsCommentsAdapter.setNewData(list);
    }

    @Override
    public void sendCommentSuccess() {
        mRvCommonList.smoothScrollBy(0, mRvCommonList.getChildAt(0).getHeight() * gsCommentsAdapter.getItemCount());
        metComment.setText(null);
        mbtnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
    }

    @Override
    public void sendCommentFail() {

    }

}
