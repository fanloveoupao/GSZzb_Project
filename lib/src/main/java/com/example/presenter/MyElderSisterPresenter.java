package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/24.
 */

public class MyElderSisterPresenter extends BasePresenter<MyElderSisterPresenter.MyElderSisterIView> {
    public MyElderSisterPresenter(MyElderSisterIView viewer) {
        super(viewer);
    }

    public interface MyElderSisterIView extends IView {

    }
}
