package com.example.presenter;

import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.api.ILoginService;
import com.example.resources.bean.BaseUserBean;
import com.example.resources.bean.PhpDataBean;
import com.example.api.schoolapi.ISchoolService;
import com.example.resources.bean.UserBaseClassBean;

import javax.inject.Inject;


/**
 * Created by fan-gk
 * on 2017/5/23.
 */

public class PhpDataTestPresenter extends BasePresenter<PhpDataTestPresenter.PhpDataTestView> {
    public PhpDataTestPresenter(PhpDataTestView viewer) {
        super(viewer);
    }

    @Inject
    ISchoolService service;
    @Inject
    ILoginService iLoginService;
    public interface PhpDataTestView extends IView {
        void onSuccess(PhpDataBean bean);

        void onFailed();
    }

    public void getData() {
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {
                PhpDataBean bean = service.getPhpData();
                if (bean != null)
                    getView().onSuccess(bean);
                else
                    getView().onFailed();
            }
        }).setRunLoading().run();
    }

    public void postData(final String id) {
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {
                PhpDataBean bean = service.postPhpData(id);
                if (bean != null)
                    getView().onSuccess(bean);
                else
                    getView().onFailed();
            }
        }).setRunLoading().run(

        );
    }

    public void getUserData() {
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {



            }
        }).setRunLoading().run();
    }
}
