package com.gseasypro.app;


import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.api.ILoginService;
import com.example.resources.bean.BaseUserBean;
import com.example.ui.ClientSettings;
import com.example.ui.UserSettings;

import javax.inject.Inject;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class IndexPresenter extends BasePresenter<IndexPresenter.IndexView> {
    public IndexPresenter(IndexView mView) {
        super(mView);
    }

    public interface IndexView extends IView {

    }

}
