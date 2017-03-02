package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class SettingPresenter extends BasePresenter<SettingPresenter.SettingView> {
    public interface SettingView extends IView {
        void onExit();
    }

    public SettingPresenter(SettingView viewer) {
        super(viewer);
    }

    public void onExit() {
        getView().onExit();
    }
}
