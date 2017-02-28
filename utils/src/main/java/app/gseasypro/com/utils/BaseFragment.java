package app.gseasypro.com.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.IView;
import com.example.exceptions.NetworkException;

import app.gseasypro.com.utils.ui.KeyBoardUtils;
import app.gseasypro.com.utils.utils.PermissionHelper;

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

    @Override
    public void hideKeyBoard() {
        KeyBoardUtils.hideKeyBoard(getBaseActivity());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.getBaseFragmentHelper().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}