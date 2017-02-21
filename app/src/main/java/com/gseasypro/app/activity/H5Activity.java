package com.gseasypro.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lang.StringUtil;
import com.example.presenter.H5Presenter;
import com.gseasypro.app.R;

import java.net.URI;

import app.gseasypro.com.utils.BaseActivity;
import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.IIntentInterceptor;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.H5WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class H5Activity extends PresenterActivity<H5Presenter, H5Presenter.H5View>
        implements H5Presenter.H5View {
    private final static String URL_HONG_NIANG = "http://ywq.tgnet.com/help/Matchmaker";

    private final static String INTENT_KEY_URL = "url";

    public static void launch(BaseActivity activity, final String url) {
        activity.launch(H5Activity.class, new IIntentInterceptor() {
            @Override
            public void intercept(Intent intent) {
                intent.putExtra(INTENT_KEY_URL, url);
            }
        });
    }

    public static void launchHongNiang(BaseActivity activity) {
        launch(activity, URL_HONG_NIANG);
    }

    private final H5WebView.WebViewLoadListener listener = new H5WebView.WebViewLoadListener() {
        @Override
        public String modifyUrl(String url) {
            return url;
        }

        @Override
        public boolean urlLoading(String url) {
            return true;
        }

        @Override
        public void loadError() {
            handleNetworkException();
        }

        @Override
        public void loadSuccessFinish() {

        }

        @Override
        public void onReceiveTitle(String title) {
            tbTitleBar.setLeftText(title);
        }
    };

    @BindView(R.id.wv_h5WebView)
    H5WebView h5WebView;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.title_bar_view)
    TitleBar tbTitleBar;
    @BindView(R.id.btn_reload)
    Button btnReload;

    @OnClick(R.id.btn_reload)
    void onBtnReloadClick() {
        init(currentUrl);
    }

    private String currentUrl;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
        tbTitleBar.setBackClick(this);
        init(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        init(intent);
    }

    private void init(Intent intent) {
        final String url = getUrl(intent);
        init(url);
    }

    private void init(String url) {
        tbTitleBar.setLeftText(StringUtil.EMPTY);
        currentUrl = url;
        try {
            URI uri = URI.create(url);
            tvContent.setVisibility(View.GONE);
            btnReload.setVisibility(View.GONE);
            h5WebView.setVisibility(View.VISIBLE);
            h5WebView.setLoadConflguration(this, uri.toString(), listener);
        } catch (Exception e) {
            h5WebView.setVisibility(View.GONE);
            btnReload.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(url);
        }
    }

    private void handleNetworkException() {
        tvContent.setVisibility(View.GONE);
        h5WebView.setVisibility(View.GONE);
        btnReload.setVisibility(View.VISIBLE);
    }

    private String getUrl(Intent intent) {
        String url = null;
        if (intent != null) {
            url = intent.getStringExtra(INTENT_KEY_URL);
            if (StringUtil.isNullOrWhiteSpace(url)) {
                url = intent.getData().toString();
            }
        }
        return url;
    }

}

