package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/4/12.
 */

public class PublicRepairSuccessPresenter extends BasePresenter<PublicRepairSuccessPresenter.PublicRepairSuccessView> {
    public PublicRepairSuccessPresenter(PublicRepairSuccessView viewer) {
        super(viewer);
    }

    public interface PublicRepairSuccessView extends IView {

    }
}
