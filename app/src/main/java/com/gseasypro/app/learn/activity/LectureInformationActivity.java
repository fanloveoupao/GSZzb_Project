package com.gseasypro.app.learn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.presenter.LectureInformationPresenter;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.learn.LecturePagerAdapter;
import com.gseasypro.app.learn.fragment.LectureInformationFragment;

import java.util.ArrayList;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.tablay.CoordinatorTabLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LectureInformationActivity extends PresenterActivity<LectureInformationPresenter, LectureInformationPresenter.LectureInformationView>
        implements LectureInformationPresenter.LectureInformationView {

    @BindView(R.id.vp_lecture_pager)
    ViewPager mVpLecturePager;
    @BindView(R.id.coordinatortablayout)
    CoordinatorTabLayout mCoordinatortablayout;


    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"白云校区", "校本部", "北校区", "西校区"};

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_lecture_information);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        mFragments = new ArrayList<>();
        for (String ignored : mTitles) {
            mFragments.add(LectureInformationFragment.createInstance());
        }
        mVpLecturePager.setOffscreenPageLimit(4);
        mVpLecturePager.setAdapter(new LecturePagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        getPresenter().initPageImage();

    }


    @Override
    public void onInitImageSuccess(int[] mImageArray, int[] mColorArray) {
        mImageArray = new int[]{
                R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js,
                R.mipmap.bg_other};
        mColorArray = new int[]{
                R.color.orange_ff5500,
                R.color.orange_ff5500,
                R.color.orange_ff5500,
                R.color.orange_ff5500};

        mCoordinatortablayout.setTitle("讲座信息")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mVpLecturePager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
