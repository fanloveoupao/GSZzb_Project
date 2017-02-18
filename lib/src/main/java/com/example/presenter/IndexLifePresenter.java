package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexLifePresenter extends BasePresenter<IndexLifePresenter.IndexLifeIView> {
    public IndexLifePresenter(IndexLifeIView mView) {
        super(mView);
    }

    public interface IndexLifeIView extends IView {

    }
}
