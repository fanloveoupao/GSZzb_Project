package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/23.
 */

public class GsBestDetailPresent extends BasePresenter<GsBestDetailPresent.GsBestDetailIView> {
    public GsBestDetailPresent(GsBestDetailIView viewer) {
        super(viewer);
    }

    public interface GsBestDetailIView extends IView {

    }
}
