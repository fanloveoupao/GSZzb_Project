package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/23.
 */

public class SplashPresenter extends BasePresenter<SplashPresenter.SplashIView> {
    public SplashPresenter(SplashIView viewer) {
        super(viewer);
    }

    public interface SplashIView extends IView {

    }
}
