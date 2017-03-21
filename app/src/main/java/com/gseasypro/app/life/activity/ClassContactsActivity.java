package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lang.StringUtil;
import com.example.presenter.ClassContactsPresenter;
import com.example.resources.bean.ClassContactsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.life.ClassContactsAdapter;

import java.util.List;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.ui.widget.WaveSideBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassContactsActivity extends PresenterActivity<ClassContactsPresenter, ClassContactsPresenter.ClassContactsView>
        implements ClassContactsPresenter.ClassContactsView {

    @BindView(R.id.rv_contacts)
    RecyclerView mRvContactsList;
    @BindView(R.id.side_bar)
    WaveSideBar mSideBar;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    private ClassContactsAdapter adapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_class_contacts);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("班级通讯");
        mTitleBar.setBackClick(this);
        mRvContactsList.setLayoutManager(new LinearLayoutManager(this));
        getPresenter().getContactData();
        initListener();
    }

    private void initListener() {
        mRvContactsList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                DialogUtil.softTwoBtnDialog(ClassContactsActivity.this, "确定打电话给" + adapter.getData().get(position).name + "吗?"
                        , "取消", "确定", new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
            }
        });
        mSideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < adapter.getData().size(); i++) {
                    if (StringUtil.equals(adapter.getData().get(i).index, index)) {
                        ((LinearLayoutManager) mRvContactsList.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void onInitContactData(List<ClassContactsBean> data) {
        adapter = new ClassContactsAdapter(data);
        mRvContactsList.setAdapter(adapter);
    }


}
