package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexLearnPresenter extends BasePresenter<IndexLearnPresenter.IndexLearnIView> {
    public IndexLearnPresenter(IndexLearnIView mView) {
        super(mView);
    }

    public interface IndexLearnIView extends IView {

    }
}
