package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.presenter.PublicRepairPresenter;
import com.example.utils.CollectionUtil;
import com.example.utils.DateUtil;
import com.gseasypro.app.R;
import com.gseasypro.app.widget.dialog.ChooseTimeDialogFragment;
import com.gseasypro.app.widget.dialog.SimpleListDialog;

import java.util.Date;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fan-gk on 2017/3/14.
 */
public class PublicRepairActivity extends PresenterActivity<PublicRepairPresenter, PublicRepairPresenter.PublicRepairView>
        implements PublicRepairPresenter.PublicRepairView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_repair_time)
    TextView mTvRepairTime;
    @BindView(R.id.tv_degree)
    TextView mTvDegree;
    @BindView(R.id.tv_damage)
    TextView mTvDamage;
    @BindView(R.id.layout_fist_view)
    LinearLayout mLayoutFistView;
    private SimpleListDialog mCsdegree;
    private SimpleListDialog mDamageDialog;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_public_repair);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("报修管理");
        mTitleBar.setBackClick(this);
    }


    @OnClick({R.id.ll_repair_time, R.id.ll_degree, R.id.ll_damage, R.id.btn_next})
    public void onClick(View view) {
        mLayoutFistView.requestFocus();
        switch (view.getId()) {
            case R.id.ll_repair_time:
                new ChooseTimeDialogFragment.Builder()
                        .backgroundColor(ContextCompat.getColor(this, R.color.white_ffffff))
                        .mainColor(ContextCompat.getColor(this, R.color.orange_ff5500))
                        .titleTextColor(ContextCompat.getColor(this, R.color.white_ffffff))
                        .minDateRange(new Date(System.currentTimeMillis()))
                        .title("选择时间")
                        .build()
                        .setListener(new ChooseTimeDialogFragment.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                mTvRepairTime.setText(DateUtil.dateToString(date, "MM月dd日 HH:mm"));
                            }
                        })
                        .show(this);

                break;
            case R.id.ll_degree:
                if (mCsdegree == null)
                    getPresenter().getDegree();
                else
                    mCsdegree.show();
                break;
            case R.id.ll_damage:
                if (mDamageDialog == null)
                    getPresenter().getDamage();
                else
                    mDamageDialog.show();
                break;
            case R.id.btn_next:
                launch(PublicRepairSuccessActivity.class, true);
                break;
        }
    }

    @Override
    public void onGetDegreeSuccess(List<String> typeList) {
        if (!CollectionUtil.isNullOrEmpty(typeList)) {
            mCsdegree = new SimpleListDialog(this, "选择紧急程度", typeList);
            mCsdegree.setOnItemClickListener(new SimpleListDialog.OnItmeClickListener() {
                @Override
                public void listener(String str, int position) {
                    mTvDegree.setText(str);
                }
            });
        }
        mCsdegree.show();
    }

    @Override
    public void onGetDamageSuccess(List<String> typeList) {
        if (!CollectionUtil.isNullOrEmpty(typeList)) {
            mDamageDialog = new SimpleListDialog(this, "选择损坏程度", typeList);
            mDamageDialog.setOnItemClickListener(new SimpleListDialog.OnItmeClickListener() {
                @Override
                public void listener(String str, int position) {
                    mTvDamage.setText(str);
                }
            });
        }
        mDamageDialog.show();
    }

}
