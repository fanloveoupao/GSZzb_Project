package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;


/**
 * Created by fan-gk on 2017/3/16.
 */

public class ClassroomQueryPresenter extends BasePresenter<ClassroomQueryPresenter.ClassroomQueryView> {


    public interface ClassroomQueryView extends IView {
        void onInitDataSuccess();
    }

    public ClassroomQueryPresenter(ClassroomQueryView viewer) {
        super(viewer);
    }

    public void initData() {
        getView().onInitDataSuccess();

    }

}
