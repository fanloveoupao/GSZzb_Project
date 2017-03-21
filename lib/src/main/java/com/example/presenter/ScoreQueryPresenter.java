package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/16.
 */

public class ScoreQueryPresenter extends BasePresenter<ScoreQueryPresenter.ScoreQueryView> {


    public interface ScoreQueryView extends IView {

    }

    public ScoreQueryPresenter(ScoreQueryView viewer) {
        super(viewer);
    }
}
