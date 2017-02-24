package app.gseasypro.com.utils.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import app.gseasypro.com.utils.R;
import app.gseasypro.com.utils.executor.ThreadExecutor;


/**
 * Created by fan-gk on 2017/2/21.
 */


public class H5WebView extends RelativeLayout {

    private Activity mActivity;
    private Context mContext;
    private boolean isError = false;
    private boolean isFinish = false;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private ProgressDialog mProgressBar;

    public H5WebView(Context context) {
        super(context);
        initView();

    }

    public H5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public H5WebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_h5_webview, this);
        mWebView = (WebView) view.findViewById(R.id.my_webview);
        mProgressBar = new ProgressDialog(mContext);
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setMessage("加载中，请稍候！");
        initConfiguration();
    }


    private void initConfiguration() {
        mWebSettings = mWebView.getSettings();

        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        //设置WebView的UserAgent属性
        String userAgent = mWebSettings.getUserAgentString();
        mWebSettings.setUserAgentString(userAgent);

        mWebSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        mWebSettings.setLoadsImagesAutomatically(true);//支持自动加载图片

        // 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); //支持内容从新布局
        requestFocus(View.FOCUS_DOWN);

    }


    public void setLoadConflguration(Activity activity, final String webviewUrl, final WebViewLoadListener listener) {
        mActivity = activity;
        setDiaplay();
        isError = false;
        isFinish = false;
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                isError = false;
                isFinish = false;
                if (!listener.urlLoading(url)) {
                    String newUrl = listener.modifyUrl(url);
                    mWebView.loadUrl(newUrl == null ? url : newUrl);
                }
                return true;//返回true时不会执行else后面代码，返回false时满足条件，if ，else都会执行（可以通过页面跳转后按返回验证）
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                isError = true;
                listener.loadError();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                isFinish = true;
                if (isError) listener.loadError();
                else listener.loadSuccessFinish();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setProgress(newProgress);
                    mProgressBar.hide();
                } else {
                    if (!mProgressBar.isShowing()) {
                        mProgressBar.show();
                    }
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                listener.onReceiveTitle(title);
            }
        });
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(webviewUrl);
            }
        });
   
    }


    //适配
    private void setDiaplay() {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }

    }

    public interface WebViewLoadListener {
        String modifyUrl(String url);

        boolean urlLoading(String url);

        void loadError();

        void loadSuccessFinish();

        void onReceiveTitle(String title);
    }

    public void reload() {
        if (isFinish) {
            isError = false;
            mWebView.reload();
        }
    }


}

