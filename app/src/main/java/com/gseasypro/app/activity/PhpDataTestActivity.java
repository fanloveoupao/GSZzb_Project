package com.gseasypro.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presenter.PhpDataTestPresenter;
import com.example.resources.bean.PhpDataBean;
import com.gseasypro.app.R;
import com.gseasypro.app.ioc.compoents.PresenterComponent;

import app.gseasypro.com.utils.ToastUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhpDataTestActivity extends BasePresenterActivity<PhpDataTestPresenter, PhpDataTestPresenter.PhpDataTestView>
        implements PhpDataTestPresenter.PhpDataTestView {


    @BindView(R.id.text)
    TextView mTextView;
    @BindView(R.id.btnRequire)
    Button btnRequire;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_php_data_test);
        ButterKnife.bind(this);

    }

    @Override
    protected void injectPresenter(PresenterComponent component, PhpDataTestPresenter presenter) {
        component.inject(presenter);
    }


    @Override
    public void onSuccess(PhpDataBean bean) {
        mTextView.setText(bean.name);
    }

    @Override
    public void onFailed() {
        ToastUtils.show(PhpDataTestActivity.this, "请求失败", Toast.LENGTH_LONG);
    }

    @OnClick({R.id.btnRequire})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRequire:
                getPresenter().getUserData();
                break;
        }
    }

}
