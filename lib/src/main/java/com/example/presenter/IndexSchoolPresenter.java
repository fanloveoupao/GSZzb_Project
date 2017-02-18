package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexSchoolPresenter extends BasePresenter<IndexSchoolPresenter.IndexSchoolView> {
    public IndexSchoolPresenter(IndexSchoolView mView) {
        super(mView);
    }

    public interface IndexSchoolView extends IView {

    }
}
