package app.gseasypro.com.utils;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.BasePresenter;
import com.example.IView;

import app.gseasypro.com.utils.BaseActivity;

/**
 * Created by fan-gk on 2017/2/4.
 */

public abstract class PresenterFragment<T extends BasePresenter<E>, E extends IView> extends BaseFragment {

    private T presenter;

    protected T getPresenter(){
        return presenter;
    }

    protected T createPresenter(){
        presenter = PresenterUtil.createPresenter(this);
        return presenter;
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Override
    @CallSuper
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onViewCreate();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroy();
    }

    @Override
    @CallSuper
    public void onResume() {
        presenter.onViewResume();
        super.onResume();
    }



    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        presenter.onViewPause();
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        presenter.onViewStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewStop();
    }
}
