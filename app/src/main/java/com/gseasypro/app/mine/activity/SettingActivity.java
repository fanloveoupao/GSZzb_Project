package com.gseasypro.app.mine.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.presenter.SettingPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends PresenterActivity<SettingPresenter, SettingPresenter.SettingView>
        implements SettingPresenter.SettingView {

    @BindView(R.id.tv_setting_version)
    TextView mTvSettingVersion;
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initTitleBar();

        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            String versionName = info.versionName;
            String versionView = mTvSettingVersion.getText().toString().trim();
            versionView = versionView.replace("%", versionName);
            mTvSettingVersion.setText(versionView);
        } catch (PackageManager.NameNotFoundException e) {
        }

    }

    private void initTitleBar() {
        mTitleBar.setBackClick(this);
        mTitleBar.setLeftText(getResources().getString(R.string.setting_title));
    }

    @Override
    public void onExit() {

    }
}
