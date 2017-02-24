package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/22.
 */

public class SelectedBlogSubPresenter extends BasePresenter<SelectedBlogSubPresenter.SelectedBlogSubIView> {
    public SelectedBlogSubPresenter(SelectedBlogSubIView viewer) {
        super(viewer);
    }

    public interface SelectedBlogSubIView extends IView {

    }
}
