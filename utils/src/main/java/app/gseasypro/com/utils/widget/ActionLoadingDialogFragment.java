package app.gseasypro.com.utils.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ActionRequest;
import com.example.lang.StringUtil;
import com.example.log.LoggerResolver;

import java.util.concurrent.atomic.AtomicInteger;

import app.gseasypro.com.utils.R;
import app.gseasypro.com.utils.dialog.BaseActionRequestHandleDialogFragment;
import app.gseasypro.com.utils.dialog.DialogFragmentUtil;
import app.gseasypro.com.utils.executor.ThreadExecutor;
import app.gseasypro.com.utils.ui.dialog.SimpleDialogFragment;

/**
 * Created by fan-gk on 2017/4/12.
 */


public class ActionLoadingDialogFragment extends BaseActionRequestHandleDialogFragment {
    private static class Builder extends SimpleDialogFragment.Builder<ActionLoadingDialogFragment, ActionLoadingDialogFragment.Builder> {
        private String content;

        public String getContent() {
            return content;
        }

        public ActionLoadingDialogFragment.Builder setContent(String content) {
            this.content = content;
            return this;
        }

        @Override
        public ActionLoadingDialogFragment build() {
            return createInstance(this);
        }
    }

    private final static String FRAGMENT_TAG = "single_progress_dialog";
    private final AtomicInteger refCount = new AtomicInteger(0);

    private static ActionLoadingDialogFragment createInstance(ActionLoadingDialogFragment.Builder builder){
        ActionLoadingDialogFragment fragment = new ActionLoadingDialogFragment();
        fragment.setBuilder(builder);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_progress, null);

        Builder builder = getBuilder();
        if(builder != null) {
            if(!StringUtil.isNullOrEmpty(builder.getContent())) {
                TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
                tvContent.setText(builder.getContent());
            }
        }

        return view;
    }

    public static ActionLoadingDialogFragment singleShow(FragmentActivity parent, ActionRequest request){
        Builder builder = new Builder().setCancelable(true).setContent(request.getLoadingMessage());
        ActionLoadingDialogFragment fragment = singleShow(parent, ActionLoadingDialogFragment.class, FRAGMENT_TAG, request, builder.buildArgs(null));
        fragment.refCount.incrementAndGet();
        return fragment;
    }

    public static ActionLoadingDialogFragment singleShow(Fragment parent, ActionRequest request){
        Builder builder = new Builder().setCancelable(true).setContent(request.getLoadingMessage());
        ActionLoadingDialogFragment fragment = singleShow(parent, ActionLoadingDialogFragment.class, FRAGMENT_TAG, request, builder.buildArgs(null));
        fragment.refCount.incrementAndGet();
        return fragment;
    }

    public static void dismiss(FragmentActivity parent){
        DialogFragmentUtil.dismissAllowingStateLoss(parent.getSupportFragmentManager(), ActionLoadingDialogFragment.class, FRAGMENT_TAG);
    }

    public static void dismiss(Fragment parent){
        DialogFragmentUtil.dismissAllowingStateLoss(parent.getChildFragmentManager(), ActionLoadingDialogFragment.class, FRAGMENT_TAG);
    }

    @Override
    public void dismiss() {
        final int count = refCount.decrementAndGet();
        if(count <= 0){
            refCount.set(0);
            ThreadExecutor.runInMain(new Runnable() {
                @Override
                public void run() {
                    try {
                        ActionLoadingDialogFragment.super.dismiss();
                    }catch (Throwable e){
                        LoggerResolver.getInstance().fail("ActionLoadingDialogFragment", e);
                    }
                }
            });
        }
    }
}

