package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/21.
 */

public class H5Presenter extends BasePresenter<H5Presenter.H5View> {
    public interface H5View extends IView {

    }

    public H5Presenter(H5View viewer) {
        super(viewer);
    }
}