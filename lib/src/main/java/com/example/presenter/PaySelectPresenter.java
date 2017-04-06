package com.example.presenter;

import com.example.IView;

/**
 * Created by fan-gk on 2017/4/6.
 */

public class PaySelectPresenter extends EventPresenter<PaySelectPresenter.PaySelectView> {

    public PaySelectPresenter(PaySelectView viewer) {
        super(viewer);
    }

    public interface PaySelectView extends IView {
        void onApplyTradeSuccessful(int type, String tradeInfo);

        void onPaySuccessful();

        void onPayFail();
    }
}
