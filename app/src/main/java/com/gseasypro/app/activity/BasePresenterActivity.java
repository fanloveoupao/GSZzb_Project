package com.gseasypro.app.activity;

import android.net.Uri;

import com.example.ActionRequest;
import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;
import com.gseasypro.app.R;
import com.gseasypro.app.ZzbApplication;
import com.gseasypro.app.fragment.dialog.ActionReloadDialogFragment;
import com.gseasypro.app.ioc.compoents.DaggerPresenterComponent;
import com.gseasypro.app.ioc.compoents.PresenterComponent;
import com.gseasypro.app.ioc.modules.ServiceModule;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.PresenterActivity;

/**
 * Created by fan-gk on 2017/4/14.
 */

public abstract class BasePresenterActivity<T extends BasePresenter<E>, E extends IView>
        extends PresenterActivity<T, E> {

    @Override
    protected T createPresenter() {
        T presenter = super.createPresenter();
        PresenterComponent component = DaggerPresenterComponent.builder()
                .serviceModule(new ServiceModule())
                .build();
        injectPresenter(component, getPresenter());
        return presenter;
    }

    @Override
    protected boolean canRotateScreen() {
        return false;
    }

    protected abstract void injectPresenter(PresenterComponent component, T preseneter);



    @Override
    protected boolean launchWeb(Uri uri) {
        H5Activity.launch(this, uri.toString());
        return true;
    }

    @Override
    public void onException(final ActionRequest request, NetworkException e) {
        safeRunAfterSaveInstanceState(new Runnable() {
            @Override
            public void run() {
                ActionReloadDialogFragment.singleShow(BasePresenterActivity.this, request);
            }
        });
    }


    public void showCompleteInfoDialog() {
        DialogUtil.hardTwoBtnDialog(this, getString(R.string.my_info_no_complete), "放弃发布", "完善资料", new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public ZzbApplication getYwqApplication() {
        return (ZzbApplication) getApplication();
    }
}

