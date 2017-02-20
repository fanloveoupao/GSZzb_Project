package com.gseasypro.app.school.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.presenter.BeautifulGsPresenter;
import com.example.resources.bean.BeautifulGsItemBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.BeautifulGsAdapter;
import com.gseasypro.app.adapter.school.FeedItemAnimator;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.widget.FeedContextMenu;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.FeedContextMenuManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gseasypro.app.adapter.school.BeautifulGsAdapter.ACTION_LIKE_BUTTON_CLICKED;
import static com.gseasypro.app.adapter.school.BeautifulGsAdapter.ACTION_LIKE_IMAGE_CLICKED;

public class BeautifulGsActivity extends PresenterActivity<BeautifulGsPresenter, BeautifulGsPresenter.BeautifulGsView>
        implements BeautifulGsPresenter.BeautifulGsView {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_gs_list)
    RecyclerView mRvGsRecyclerList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private BeautifulGsAdapter gsAdapter;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_beautiful_gs);
        ButterKnife.bind(this);
        mTitleBar.setCenterText("最美广师");
        mTitleBar.setBackClick(this);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        gsAdapter = new BeautifulGsAdapter(this, new ArrayList<BeautifulGsItemBean>());
        mRvGsRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mRvGsRecyclerList.setAdapter(gsAdapter);
        mRvGsRecyclerList.setItemAnimator(new FeedItemAnimator());
        initDatas();
        initListener();
    }

    private void initDatas() {
        getPresenter().requestData();
    }

    private void initListener() {
        gsAdapter.setListener(new BeautifulGsAdapter.OnFeedItemOnClickListener() {
            @Override
            public void onImageClick(View view, int position) {
                gsAdapter.getData().get(position).likesCount++;
                gsAdapter.getData().get(position).isLiked = true;
                gsAdapter.notifyItemChanged(position, ACTION_LIKE_IMAGE_CLICKED);

            }

            @Override
            public void onShareClick(View view, int position) {
                FeedContextMenuManager.getInstance().toggleContextMenuFromView(view, position,
                        new FeedContextMenu.OnFeedContextMenuItemClickListener() {
                            @Override
                            public void onSharePhotoClick(int feedItem) {

                            }

                            @Override
                            public void onCancelClick(int feedItem) {
                                FeedContextMenuManager.getInstance().hideContextMenu();
                            }
                        });
            }

            @Override
            public void onCommentsClick(View view, int position) {
                launch(GsCommentsActivity.class, false);
            }

            @Override
            public void onLikeClick(View view, int position) {
                gsAdapter.getData().get(position).likesCount++;
                gsAdapter.getData().get(position).isLiked = true;
                gsAdapter.notifyItemChanged(position, ACTION_LIKE_BUTTON_CLICKED);
            }
        });

        mRvGsRecyclerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void requestSuccess(List<BeautifulGsItemBean> datas) {
        gsAdapter.setNewData(datas);
    }
}
