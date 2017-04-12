package app.gseasypro.com.utils;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.example.ActionRequest;
import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;

import app.gseasypro.com.utils.executor.ThreadExecutor;
import app.gseasypro.com.utils.widget.ActionLoadingDialogFragment;

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

    @Override
    public void showLoadingView(final ActionRequest request) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ActionLoadingDialogFragment.singleShow(DialogPresenterFragment.this, request);
            }
        });
    }

    @Override
    public void dismissLoadingView() {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ActionLoadingDialogFragment.dismiss(DialogPresenterFragment.this);
            }
        });
    }

    @Override
    public void onNeedLogin(boolean otherDevice) {
        getBaseActivity().onNeedLogin(otherDevice);
    }

    @Override
    public void onException(ActionRequest request, NetworkException e) {
        getBaseActivity().onException(request, e);
    }

    @Override
    public void onException(Exception e) {
        getBaseActivity().onException(e);
    }

    @Override
    public void onException(Exception e, boolean finish) {
        getBaseActivity().onException(e, finish);
    }

    @Override
    public void onWarn(String message) {
        getBaseActivity().onWarn(message);
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void runAction(ActionRequest request){
        getBaseActivity().runAction(request);
    }

    //endregion
}
