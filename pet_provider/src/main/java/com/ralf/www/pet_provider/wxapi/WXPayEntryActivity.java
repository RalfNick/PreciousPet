//package com.ralf.www.pet_provider.wxapi;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tulong.tlpet.R;
//import com.tulong.tlpet.view.activity.pay_activity.pay.weixin.WXPay;
//
//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
//    private IWXAPI iwxapi;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wxpay_call_back);
////        iwxapi = WXAPIFactory.createWXAPI(this, Constant.WX_APP_KEY);
////        iwxapi.handleIntent(getIntent(), this);
//        if(WXPay.getInstance() != null) {
//            WXPay.getInstance().getWXApi().handleIntent(getIntent(), this);
//        } else {
//            finish();
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
////        iwxapi.handleIntent(intent, this);
//        if(WXPay.getInstance() != null) {
//            WXPay.getInstance().getWXApi().handleIntent(intent, this);
//        }
//    }
//
//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            WXPay.getInstance().onResp(baseResp.errCode);
//            finish();
//        }
//    }
//
//}