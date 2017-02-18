package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexMinePresenter extends BasePresenter<IndexMinePresenter.IndexMineIView> {
    public IndexMinePresenter(IndexMineIView mView) {
        super(mView);
    }

    public interface IndexMineIView extends IView {

    }
}
