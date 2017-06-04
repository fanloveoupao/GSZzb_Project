package com.gseasypro.app.mine;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.presenter.IndexMinePresenter;
import com.example.resources.bean.BaseUserBean;
import com.example.ui.UserSettings;
import com.gseasypro.app.R;
import com.gseasypro.app.mine.activity.EditPersonActivity;
import com.gseasypro.app.mine.activity.MyCollectionActivity;
import com.gseasypro.app.mine.activity.MyElderSisterActivity;
import com.gseasypro.app.mine.activity.MyLectureActivity;
import com.gseasypro.app.mine.activity.QRCodeActivity;
import com.gseasypro.app.mine.activity.SettingActivity;
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
    @BindView(R.id.rl_friends)
    RelativeLayout mRlFriends;
    @BindView(R.id.rl_project)
    RelativeLayout mRlProject;
    @BindView(R.id.lin_lay_my_collection)
    RelativeLayout mLinLayMyCollection;
    @BindView(R.id.lin_lay_feedback)
    RelativeLayout mLinLayFeedback;
    @BindView(R.id.lin_lay_setting)
    RelativeLayout mLinLaySetting;
    @BindView(R.id.lin_lay_version_update)
    RelativeLayout mLinLayVersionUpdate;
    @BindView(R.id.tv_name_and_post)
    TextView mTvnameAndpost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index_mine, container, false);
        ButterKnife.bind(this, view);
        ImageLoader.loadIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg", mImgvIcon, true, true);
        mTvnameAndpost.setText("广技师(传播学院)");
        BaseUserBean userBean = UserSettings.getInstance().getBaseUserBean();
        if (userBean != null)
            mTvnameAndpost.setText(userBean.username + "(" + userBean.major + ")");
        return view;
    }

    @OnClick({R.id.lin_lay_last, R.id.rl_friends, R.id.rl_project, R.id.lin_lay_my_collection, R.id.lin_lay_feedback, R.id.lin_lay_setting, R.id.lin_lay_version_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_friends:
                break;
            case R.id.rl_project:
                getBaseActivity().launch(MyLectureActivity.class, false);
                break;
            case R.id.lin_lay_my_collection:
                getBaseActivity().launch(MyCollectionActivity.class, false);
                break;
            case R.id.lin_lay_feedback:
                break;
            case R.id.lin_lay_setting:
                getBaseActivity().launch(SettingActivity.class, false);
                break;
            case R.id.lin_lay_version_update:
                break;
            case R.id.lin_lay_last:
                getBaseActivity().launch(MyElderSisterActivity.class, false);
                break;
        }
    }

    @OnClick(R.id.iv_qr_code)
    public void onClick() {
        getBaseActivity().launch(QRCodeActivity.class, false);
    }

    @OnClick(R.id.tv_name_and_post)
    public void setTvNameAndPost() {
        getBaseActivity().launch(EditPersonActivity.class, false);
    }

    @OnClick(R.id.imgv_icon)
    public void setImgvIcon() {
        getBaseActivity().launch(EditPersonActivity.class, false);
    }

}
