package com.gseasypro.app.mine;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.presenter.IndexMinePresenter;
import com.gseasypro.app.R;
import com.gseasypro.app.mine.activity.MyElderSisterActivity;
import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.PresenterFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexMineFragment extends PresenterFragment<IndexMinePresenter, IndexMinePresenter.IndexMineIView>
        implements IndexMinePresenter.IndexMineIView {

    @BindView(R.id.imgv_icon)
    ImageView mImgvIcon;
    @BindView(R.id.lin_lay_last)
    RelativeLayout mLinLayLast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index_mine, container, false);
        ButterKnife.bind(this, view);
        ImageLoader.loadIcon("http://setc.gpnu.edu.cn/img/logo.png", mImgvIcon, true, true);
        return view;
    }


    @OnClick(R.id.lin_lay_last)
    public void onClick(View view) {
        getBaseActivity().launch(MyElderSisterActivity.class, false);
    }
}
