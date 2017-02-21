package com.gseasypro.app.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.BasePresenter;
import com.example.presenter.QRCodeScanPresenter;
import com.github.yoojia.qrcode.camera.CameraPreviewView;
import com.github.yoojia.qrcode.camera.CaptureCallback;
import com.github.yoojia.qrcode.qrcode.QRCodeDecoder;
import com.gseasypro.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ToastUtils;
import app.gseasypro.com.utils.ui.widget.LiveCameraView;
import app.gseasypro.com.utils.ui.widget.SimplePopupWindow;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.ImageSelecter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeScanActivity extends PresenterActivity<QRCodeScanPresenter, QRCodeScanPresenter.QRCodeScanView>
        implements QRCodeScanPresenter.QRCodeScanView {
    @BindView(R.id.lcv_camera)
    LiveCameraView lcvCamera;
    @BindView(R.id.iv_preview)
    ImageView ivPreview;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    private AtomicBoolean decodeSuccessed;
    private float x;
    private float y;
    private float lengthOfSide;
    private Point screenSize;
    private QRCodeDecoder qrCodeDecoder;
    private SimplePopupWindow mPopupWindow;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_qrcode_scan);
        ButterKnife.bind(this);
        lcvCamera.setOnPermissionDeniedListener(new LiveCameraView.OnPermissionDeniedListener() {
            @Override
            public void onPermissionDenied() {
                ToastUtils.showLong(QRCodeScanActivity.this, "无法访问摄像头");
                QRCodeScanActivity.this.finish();
            }
        });
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        titleBar.setBackClick(this);
        titleBar.setLeftText("扫一扫");
        titleBar.setRightView(R.mipmap.title_right_menu);

        decodeSuccessed = new AtomicBoolean(false);
        qrCodeDecoder = new QRCodeDecoder.Builder().build();

        lengthOfSide = getResources().getDimension(R.dimen.qr_code_scan_length_of_side);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenSize = new Point();
        windowManager.getDefaultDisplay().getSize(screenSize);

        x = (screenSize.x - lengthOfSide) / 2;
        y = getResources().getDimension(R.dimen.qr_code_scan_y) + getResources().getDimension(R
                .dimen.title_bar_layout_height);


        lcvCamera.setPreviewReadyCallback(new CameraPreviewView.PreviewReadyCallback() {
            @Override
            public void onStarted(Camera camera) {
                lcvCamera.startAutoCapture(1000, new CaptureCallback() {
                    @Override
                    public void onCaptured(final Bitmap bitmap) {
                        launchByQR(bitmap, false);
                    }
                });
            }

            @Override
            public void onStopped() {
                lcvCamera.stopAutoCapture();
            }
        });

        titleBar.setRightViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow == null) {
                    mPopupWindow = new SimplePopupWindow(QRCodeScanActivity.this, R.layout.popup_qr_sweep);
                }
                mPopupWindow.showAsDropDown(titleBar.mRightTextView);
                mPopupWindow.getContentView().findViewById(R.id.tv_select).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                        ImageSelecter.selectMuit(1, onPhotoSelectedListener);

                    }
                });
                mPopupWindow.getContentView().findViewById(R.id.tv_mine).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
            }


        });


    }

    public void launchByQR(Bitmap bitmap, boolean fromPhoto) {
        try {
            String value = qrCodeDecoder.decode(bitmap);
            if (value != null) {
                if (decodeSuccessed.compareAndSet(false, true)) {
                    finish();
                }
            } else {
                if (fromPhoto)
                    ToastUtils.showShort(this, "无法识别二维码");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public ImageSelecter.OnSelectedListener onPhotoSelectedListener = new ImageSelecter.OnSelectedListener() {
        @Override
        public void successed(List<ImageSelecter.PhotoInfo> photoInfos) {
            List<String> paths = new ArrayList<>();
            for (ImageSelecter.PhotoInfo photoInfo : photoInfos) {
                paths.add(photoInfo.getPhotoPath());
            }
            if (paths.size() > 0) {
                String userFace = paths.get(0);
                ToastUtils.showShort(QRCodeScanActivity.this, "扫描中");
                Bitmap bitmap = BitmapFactory.decodeFile(userFace);
                launchByQR(bitmap, true);
                //---------------TODO-------------------
            }
        }

        @Override
        public void error(String message) {
            ToastUtils.showLong(QRCodeScanActivity.this, message);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
