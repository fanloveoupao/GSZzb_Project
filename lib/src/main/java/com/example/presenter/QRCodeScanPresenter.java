package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/21.
 */

public class QRCodeScanPresenter extends BasePresenter<QRCodeScanPresenter.QRCodeScanView> {
    public QRCodeScanPresenter(QRCodeScanView viewer) {
        super(viewer);
    }

    public interface QRCodeScanView extends IView {

    }
}
