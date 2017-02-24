package app.gseasypro.com.utils;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;

/**
 * Created by fan-gk on 2017/2/18.
 */

public abstract class DialogPresenterFragment<T extends BasePresenter<E>, E extends IView> extends BaseDialogFragment
        implements IView {

    private T presenter;

    protected T getPresenter() {
        return presenter;
    }

    protected T createPresenter() {
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

    //region IView


    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
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


    //endregion
}
