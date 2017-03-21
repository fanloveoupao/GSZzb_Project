package com.gseasypro.app.life.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.RechargePresenter;
import com.example.resources.bean.RechargeBean;
import com.example.resources.bean.UserInfoBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.life.RechargeAdapter;
import com.gseasypro.app.picasso.ImageLoader;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/3/16.
 */
public class RechargeFragment extends DialogPresenterFragment<RechargePresenter, RechargePresenter.RechargeView>
        implements RechargePresenter.RechargeView {

    public static String EXTRA_TITLE = "extra_title";
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_recharge_list)
    RecyclerView mRvRechargeList;
    @BindView(R.id.multiStateView)
    MultiStateView mMultiStateView;
    private RechargeAdapter adapter;
    private View mheadView;

    public static RechargeFragment createInstance(String title) {
        RechargeFragment fragment = new RechargeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_recharge, container, false);
        mheadView = inflater.inflate(R.layout.head_recharge_view, container, false);
        ButterKnife.bind(this, mView);
        String title = getArguments().getString(EXTRA_TITLE);
        mTitleBar.setLeftText(title + "充值");
        mTitleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mRvRechargeList.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));
        adapter = new RechargeAdapter(new ArrayList<RechargeBean>());
        adapter.addHeaderView(mheadView);
        mRvRechargeList.setAdapter(adapter);
        getPresenter().getUserData();
        getPresenter().initData();
        initListener();
        return mView;
    }

    private void initListener() {
        mRvRechargeList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }

    @Override
    public void onInitData(List<RechargeBean> data) {
        adapter.setNewData(data);
    }

    @Override
    public void onInitUserDataSuccess(UserInfoBean userInfo) {
        TextView tvNumber = (TextView) mheadView.findViewById(R.id.tv_card_number);
        TextView tvName = (TextView) mheadView.findViewById(R.id.tv_name);
        ImageView imgAvatar = (ImageView) mheadView.findViewById(R.id.iv_avatar);
        ImageLoader.loadIcon(userInfo.image, imgAvatar, true, true);
        tvNumber.setText(userInfo.company);
        tvName.setText("绑定姓名(" + userInfo.name + ")");
    }
}
