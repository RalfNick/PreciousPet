package com.ralf.module_login_register.mvp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_login_register.R;
import com.ralf.module_login_register.R2;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserProtocolActivity
 * @email -
 * @date 2018/12/10 上午10:47
 **/
@Route(path = RouterConfig.LoginRegisterModule.USER_PROTOCOL_PATH)
public class UserProtocolActivity extends BaseSwipeBackActivity {

    private static final int PROGRESS_LENGTH = 100;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.layout_title)
    RelativeLayout mLayoutTitle;
    @BindView(R2.id.protocol_webview)
    WebView mWebview;
    @BindView(R2.id.progress_bar_webView)
    ProgressBar mProgressBar;

    @Autowired(name = RouterConfig.LoginRegisterModule.KEY_USER_PROTOCOL_URL)
    String mUrl;

    @Autowired(name = RouterConfig.LoginRegisterModule.KEY_USER_PROTOCOL_TITLE)
    String mTitle;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user_protocol;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mTitleNameTv.setText(mTitle);
        initWebView(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebview != null) {
            ViewParent parent = mWebview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebview);
            }
            mWebview.stopLoading();
            mWebview.getSettings().setJavaScriptEnabled(false);
            mWebview.clearHistory();
            mWebview.removeAllViews();
            mWebview.destroy();
            mWebview = null;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setSupportZoom(true);
        if (!TextUtils.isEmpty(url)) {
            mWebview.loadUrl(url);
        }
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mProgressBar == null) {
                    return;
                }
                if (newProgress == PROGRESS_LENGTH) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });

        mWebview.setWebViewClient(new AppWebViewClients());
        mWebview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @OnClick(R2.id.back_iv)
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.back_iv) {
            finish();
        }
    }

    public class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            try {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                //没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                return true;
            }
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

}
