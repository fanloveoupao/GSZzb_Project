package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/14.
 */

public class VerificationCardPresenter extends BasePresenter<VerificationCardPresenter.VerificationCardView> {


    public interface VerificationCardView extends IView {

    }

    public VerificationCardPresenter(VerificationCardView viewer) {
        super(viewer);
    }
}
