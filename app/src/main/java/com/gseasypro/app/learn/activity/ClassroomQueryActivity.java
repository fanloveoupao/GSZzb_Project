package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.presenter.ClassroomQueryPresenter;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.learn.ClassroomQueryAdapter;
import com.gseasypro.app.adapter.learn.bean.Level0Item;
import com.gseasypro.app.adapter.learn.bean.Level1Item;
import com.gseasypro.app.adapter.learn.bean.Person;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.Random;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassroomQueryActivity extends PresenterActivity<ClassroomQueryPresenter, ClassroomQueryPresenter.ClassroomQueryView>
        implements ClassroomQueryPresenter.ClassroomQueryView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_classroom_list)
    RecyclerView mRvClassroomList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private ClassroomQueryAdapter adapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_classroom_query);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("空课室查询");
        mTitleBar.setBackClick(this);
        mRvClassroomList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassroomQueryAdapter(new ArrayList<MultiItemEntity>());
        mRvClassroomList.setAdapter(adapter);
        getPresenter().initData();
    }

    @Override
    public void onInitDataSuccess() {
        int lv0Count = 3;
        int lv1Count = 6;
        int personCount = 5;

        String[] nameList = {"10", "20", "32", "41", "52"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("第 " + (i + 1) + "教学楼", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("第" + (j + 1)+"层", "层");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(10)));
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        adapter.setNewData(res);
    }
}
