package app.gseasypro.com.utils;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;

import app.gseasypro.com.utils.ui.KeyBoardUtils;

/**
 * Created by fan-gk on 2017/2/3.
 */

public abstract class PresenterActivity<T extends BasePresenter<E>, E extends IView> extends BaseActivity {

    private T presenter;

    protected T getPresenter() {
        return presenter;
    }

    protected T createPresenter() {
        presenter = PresenterUtil.createPresenter(this);
        return presenter;
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!canRotateScreen())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        createPresenter();
        initView(savedInstanceState);
        presenter.onViewCreate();
    }

    protected boolean canRotateScreen() {
        return true;
    }


    protected abstract void initView(@Nullable Bundle savedInstanceState);

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroy();
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        presenter.onViewResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        presenter.onViewPause();
        KeyBoardUtils.hideKeyBoard(this);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        presenter.onViewStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewStop();
    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onException(Exception e, boolean finish) {

    }

    @Override
    public void onException(NetworkException e) {

    }

    @Override
    public void onWarn(String message) {

    }
}
