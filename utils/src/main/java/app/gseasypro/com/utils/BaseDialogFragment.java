package app.gseasypro.com.utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;

import app.gseasypro.com.utils.ui.KeyBoardUtils;

/**
 * Created by fan-gk on 2017/2/18.
 */


public class BaseDialogFragment extends DialogFragment {
    @Override
    @CallSuper
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getRepeatCount() == 0) {
                    return onBeforeBackPressed();
                }
                return false;
            }
        });
    }

    protected boolean onBeforeBackPressed() {
        return false;
    }

    public void hideKeyBoard() {
        KeyBoardUtils.hideKeyBoard(this);
    }
}
