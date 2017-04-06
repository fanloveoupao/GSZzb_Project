package com.gseasypro.app.life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presenter.PaySelectPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.DialogPresenterFragment;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/4/6.
 */

public class PaySelectDialogFragment extends DialogPresenterFragment<PaySelectPresenter, PaySelectPresenter.PaySelectView>
        implements PaySelectPresenter.PaySelectView {
    public static PaySelectDialogFragment createInstance() {

        PaySelectDialogFragment fragment = new PaySelectDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.PaySelectDialogFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pay_select, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onApplyTradeSuccessful(int type, String tradeInfo) {

    }

    @Override
    public void onPaySuccessful() {

    }

    @Override
    public void onPayFail() {

    }
}
