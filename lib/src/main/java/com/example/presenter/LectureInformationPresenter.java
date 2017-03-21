package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/13.
 */

public class LectureInformationPresenter extends BasePresenter<LectureInformationPresenter.LectureInformationView> {


    public interface LectureInformationView extends IView {
        void onInitImageSuccess(int[] mImageArray, int[] mColorArray);
    }

    public LectureInformationPresenter(LectureInformationView viewer) {
        super(viewer);
    }

    public void initPageImage() {
        int[] mImageArray = new int[4];
        getView().onInitImageSuccess(mImageArray, mImageArray);
    }
}
