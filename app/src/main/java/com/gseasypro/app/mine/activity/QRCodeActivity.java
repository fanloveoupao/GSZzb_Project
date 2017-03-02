package com.gseasypro.app.mine.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lang.StringUtil;
import com.example.presenter.QRCodePresenter;
import com.example.resources.bean.UserInfoBean;
import com.github.yoojia.qrcode.qrcode.QRCodeEncoder;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.io.IOException;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ToastUtils;
import app.gseasypro.com.utils.TypedValueUtil;
import app.gseasypro.com.utils.ui.widget.SimplePopupWindow;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.utils.FileHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeActivity extends PresenterActivity<QRCodePresenter, QRCodePresenter.QRCodeView>
        implements QRCodePresenter.QRCodeView, View.OnClickListener {

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.scan_tip)
    TextView mScanTip;
    @BindView(R.id.ll_shot_view)
    LinearLayout mLlShotView;
    @BindView(R.id.tv_job)
    TextView mTvJob;
    private TextView mTvSave;
    private TextView mTvSend;
    private SimplePopupWindow mPopupWindow;
    private Bitmap mQRCodeBitmap;
    private View mPopView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        mTitleBar.setRightView(R.mipmap.title_right_menu);
        mTitleBar.setLeftText("二维码名片");
        mTitleBar.setBackClick(this);
        getPresenter().getUserInfoBean();
    }

    @Override
    public void onSetUi(UserInfoBean userInfoBean) {
        mTvName.setText(StringUtil.ensureNotNull(userInfoBean.name));
        if (!StringUtil.isNullOrEmpty(userInfoBean.company))
            mTvCompany.setText(userInfoBean.company);
        else mTvCompany.setVisibility(View.GONE);
        mTvJob.setText(StringUtil.isNullOrEmpty(userInfoBean.colleage) ? "" : userInfoBean.colleage);
        ImageLoader.loadIcon(userInfoBean.image, mIvHead, true);
        mQRCodeBitmap = new QRCodeEncoder.Builder()
                .width(TypedValueUtil.fromDip(250))
                .height(TypedValueUtil.fromDip(250))
                .build().encode(userInfoBean.url);
        mIvCode.setImageBitmap(mQRCodeBitmap);
        initPopupView();
    }

    public void initPopupView() {
        mPopView = LayoutInflater.from(QRCodeActivity.this).inflate(R.layout.popup_qr_code, null);
        mTvSave = (TextView) mPopView.findViewById(R.id.tv_save);
        mTvSend = (TextView) mPopView.findViewById(R.id.tv_send);
        initListener();
    }

    private void initListener() {
        mTvSave.setOnClickListener(this);
        mTvSend.setOnClickListener(this);
        mTitleBar.setRightViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow == null) {
                    mPopupWindow = new SimplePopupWindow(mPopView);
                }
                mPopupWindow.showAsDropDown(mTitleBar.mRightTextView);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                try {
                    FileHelper.saveImage(this, "1.jpg", mQRCodeBitmap);
                } catch (IOException e) {
                    ToastUtils.showLong(this, e.getMessage());
                }
                mPopupWindow.dismiss();
                break;
            case R.id.tv_send:

                mPopupWindow.dismiss();
                break;
        }
    }
}
