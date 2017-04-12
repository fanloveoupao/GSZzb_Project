package app.gseasypro.com.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.ActionRequest;
import com.example.IView;
import com.example.exceptions.NetworkException;

import app.gseasypro.com.utils.executor.ThreadExecutor;
import app.gseasypro.com.utils.utils.PermissionHelper;
import app.gseasypro.com.utils.widget.ActionLoadingDialogFragment;

/**
 * Created by fan-gk on 2017/2/18.
 */

public class BaseFragment extends Fragment implements IView {

    private final PermissionHelper permissionHelper = PermissionHelper.createFragmentHelper(this);

    protected PermissionHelper getPermissionHelper() {
        return permissionHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    //region BaseActivity
    @Override
    public void onNeedLogin(boolean otherDevice) {
        getBaseActivity().onNeedLogin(otherDevice);
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
    public void onException(ActionRequest request, NetworkException e) {
        getBaseActivity().onException(request, e);
    }

    @Override
    public void onWarn(String message) {
        getBaseActivity().onWarn(message);
    }


    @Override
    public void hideKeyBoard() {
        getBaseActivity().hideKeyBoard();
    }

    @Override
    public void runAction(ActionRequest request) {
        getBaseActivity().runAction(request);
    }


    @Override
    public void showLoadingView(final ActionRequest request) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ActionLoadingDialogFragment.singleShow(BaseFragment.this, request);
            }
        });
    }

    @Override
    public void dismissLoadingView() {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ActionLoadingDialogFragment.dismiss(BaseFragment.this);
            }
        });
    }
    //endregion

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.getBaseFragmentHelper().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}