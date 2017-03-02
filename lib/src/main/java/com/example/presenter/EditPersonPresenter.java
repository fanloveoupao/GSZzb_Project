package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class EditPersonPresenter extends BasePresenter<EditPersonPresenter.EditPersonIView> {
    public EditPersonPresenter(EditPersonIView viewer) {
        super(viewer);
    }

    public interface EditPersonIView extends IView {

    }
}
