package com.ralf.pet_provider.util;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jess.arms.integration.AppManager;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name WebViewUtil
 * @email -
 * @date 2019/05/28 16:05
 **/
public final class WebViewUtil {

    public static WebView initWebView(String url, boolean isWrapContent) {
        WebView webView = new WebView(AppManager.getAppManager().getApplication());
        ViewGroup.LayoutParams params = null;
        if (isWrapContent) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        WebSettings settings = webView.getSettings();
        webView.setLayoutParams(params);
        webView.setPadding(25, 25, 25, 0);
        webView.loadUrl(url);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setGeolocationEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        webView.requestFocus();
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.d("webViewClient", "shouldOverrideUrlLoading: " + url);
                String id = url.replace("https://tlshop.goodcool.cn:8443/wap/goods.htm?id=", "");
                Log.d("webViewClient", "shouldOverrideUrlLoading: " + id);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        return webView;
    }
}
