package com.gseasypro.app.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ActionRequest;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.dialog.BaseActionRequestHandleDialogFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fan-gk on 2017/4/14.
 */


public class ActionReloadDialogFragment extends BaseActionRequestHandleDialogFragment {

    private final static String FRAGMENT_TAG = "action_reload_fragment";

    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @OnClick(R.id.btn_reload)
    void onReloadClick() {
        for (ActionRequest action : actions) {
            action.run();
        }
        actions.clear();
        dismiss();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.ActivityStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reload, null);
        ButterKnife.bind(this, view);

        titleBar.setLeftText("出错啦");
        titleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!onBeforeBackPressed())
                    dismiss();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        actions.clear();
    }

    public static ActionReloadDialogFragment singleShow(FragmentActivity parent, ActionRequest request){
        return singleShow(parent, ActionReloadDialogFragment.class, FRAGMENT_TAG, request, null);
    }

    public static ActionReloadDialogFragment singleShow(Fragment parent, ActionRequest request){
        return singleShow(parent, ActionReloadDialogFragment.class, FRAGMENT_TAG, request, null);
    }
}
