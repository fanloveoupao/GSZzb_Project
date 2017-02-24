package com.gseasypro.app.school.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.presenter.GsBestDetailPresent;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GsBestDetailFragment extends DialogPresenterFragment<GsBestDetailPresent, GsBestDetailPresent.GsBestDetailIView>
        implements GsBestDetailPresent.GsBestDetailIView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.avatar)
    ImageView mIvavatar;
    @BindView(R.id.iv_background)
    ImageView mIvBackground;

    public static GsBestDetailFragment createInstance() {
        return new GsBestDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FragmentActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_gs_best_detail, container, false);
        ButterKnife.bind(this, mView);
        mTitleBar.setLeftText("王老师");
        mTitleBar.setBackClick(getBaseActivity());
        initView();

        return mView;
    }

    private void initView() {
        ImageLoader.loadIcon(Uri.parse("http://setc.gpnu.edu.cn/img/logo.png"), mIvavatar, true, true);
    }

}
