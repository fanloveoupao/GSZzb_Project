package com.gseasypro.app.mine;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.presenter.IndexMinePresenter;
import com.gseasypro.app.R;
import app.gseasypro.com.utils.PresenterFragment;
import com.gseasypro.app.picasso.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexMineFragment extends PresenterFragment<IndexMinePresenter, IndexMinePresenter.IndexMineIView>
implements IndexMinePresenter.IndexMineIView{

    @BindView(R.id.imgv_icon)
    ImageView mImgvIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index_mine, container, false);
        ButterKnife.bind(this, view);
        ImageLoader.loadIcon("", mImgvIcon, true, true);
        return view;
    }


}
