package com.ralf.pet_provider.webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.pet_provider.R;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.constant.WebViewTypeEnum;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.share.PetShare;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name WebActivity
 * @email -
 * @date 2019/06/14 12:57
 **/
@Route(path = RouterConfig.Provider.WEB_VIEW_PATH)
public class WebActivity extends BaseSwipeBackActivity {

    /**
     * 当前分享类型(商品, 拼团)
     */
    private static int currShareType;

    @Autowired(name = RouterConfig.Provider.KEY_WEB_URL)
    String mUrl;
    @Autowired(name = RouterConfig.Provider.KEY_WEB_TYPE)
    WebViewTypeEnum mType = WebViewTypeEnum.Shopping;
    @Autowired(name = RouterConfig.Provider.KEY_WEB_TITLE)
    String mTitle;
    @Autowired(name = RouterConfig.Provider.KEY_WEB_MENU)
    String mMenu;

    private SonicSession sonicSession;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        // init sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }
        SonicSessionClientImpl sonicSessionClient = null;
        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        // create sonic session and run sonic flow
        sonicSession = SonicEngine.getInstance().createSession(mUrl, sessionConfigBuilder.build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        }

        // init webview
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @TargetApi(21)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return shouldInterceptRequest(view, request.getUrl().toString());
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        Intent intent = new Intent();
        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
        webView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
        // init webview settings
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView);
            sonicSessionClient.clientReady();
        } else {
            webView.loadUrl(mUrl);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }

    private String mJsTitle;
    private String mJsImageUrl;
    private String mJsShareUrl;

    @JavascriptInterface
    public void shareShopUrl(String title, String imageUrl, String shareUrl) {
        mJsTitle = title;
        mJsImageUrl = imageUrl;
        mJsShareUrl = shareUrl;
        if (title.contains("疾病")) {
            mType = WebViewTypeEnum.ChongwuJibing;
            currShareType = SHARE_TYPE_SICKNESS;
        } else {
            mType = WebViewTypeEnum.Shopping;
            currShareType = SHARE_TYPE_GOODS;
        }
    }

    /**
     * 分享
     * 根据不同shareType 执行不同分享
     */
    private static final int SHARE_TYPE_GOODS = 1000;
    private static final int SHARE_TYPE_PINTUAN = 1001;
    private static final int SHARE_TYPE_SICKNESS = 1002;
    private static final int SHARE_TYPE_BANNER = 1003;

    private void performShare(int shareType) {
        String shareUrl = null;
        switch (shareType) {
            case SHARE_TYPE_GOODS:
                //商品
//                shareUrl = ShopServiceImp.BASE_URL + "wap/share_goods.htm" + "?id=" + currShareId;
                shareUrl = mJsShareUrl;
                break;
            case SHARE_TYPE_PINTUAN:
                //拼团
//                shareUrl = ShopServiceImp.BASE_URL + "wap/share_group.htm" + "?id=" + currShareId;
                shareUrl = mJsShareUrl;
                break;
            case SHARE_TYPE_SICKNESS:
                //疾病详情
//                shareUrl = AppServiceImp.BASE_URL + "web/sicknessInfo?id=" + 1;
                shareUrl = mJsShareUrl;
                break;
            case SHARE_TYPE_BANNER:
                shareUrl = mJsShareUrl;
                break;
        }

        PetShare.ShareBuilder shareBuilder = PetShare.ShareBuilder.with(this)
                .url(mJsShareUrl)
                .imgUrl(mJsImageUrl)
                .title(mJsTitle)
                .withText(mJsTitle);

        if (currShareType == SHARE_TYPE_SICKNESS) {
            shareBuilder.desc("内容来自牵宠-疾病百科");
        } else {
            shareBuilder.desc("\r");
        }
        shareBuilder.build().share();
    }
}
