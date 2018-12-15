package com.ralf.www.module_chat.view;

import android.support.v7.app.AppCompatActivity;

//package com.ralf.www.module_chat.view;
//
//import android.annotation.TargetApi;
//import android.app.NotificationManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.chat.EMConversation;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.easeui.EaseConstant;
//import com.hyphenate.easeui.model.EaseNotifier;
//import com.hyphenate.easeui.ui.EaseChatFragment;
//import com.hyphenate.util.EasyUtils;
//import com.ralf.www.module_chat.ChatHelper;
//import com.ralf.www.module_chat.Constant;
//import com.ralf.www.module_chat.R;
//import com.ralf.www.module_chat.permissions.PermissionsManager;
//
//import javax.inject.Inject;
//import butterknife.ButterKnife;
//import debug.MainActivity;
//
public class ChatActivity extends AppCompatActivity{

    public static String toChatUserHxID;
    public static int toChatUserID;
//
//    @Inject
//    ChatService mChatService;
//
//    @Inject
//    ReplaceHeadPortraltPresenter mPortraltPresenter;
//
//    @Bind(R.id.my_title_toolbar_backImage)
//    ImageView mBackImage;
//    @Bind(R.id.my_title_toolbar_titleText)
//    TextView mTitleText;
//    @Bind(R.id.my_title_toolbar_menuImage)
//    ImageView mMenuImage;
//
//    public static ChatActivity activityInstance;
//    private ChatFragment chatFragment;
//    protected static final String TAG = "ChatActivity";
//    public static String toChatUserHxID;
//    public static int toChatUserID;
//    public String toChatUserImage, toChatUserName;
//
//
//    public static Intent getCallingIntent(Context context, int toUserId, String hxUserId, String userNickName, String userHeadImage) {
//        Intent callingIntent = new Intent(context, ChatActivity.class);
//        callingIntent.putExtra(EaseConstant.EXTRA_USER_ID_ID, toUserId);
//        callingIntent.putExtra(EaseConstant.EXTRA_USER_HXID, hxUserId);
//        callingIntent.putExtra(EaseConstant.EXTRA_USER_NAME, userNickName);
//        callingIntent.putExtra(EaseConstant.EXTRA_USER_IMAGE, userHeadImage);
//        callingIntent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//        return callingIntent;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    protected void onCreate(Bundle arg0) {
//        super.onCreate(arg0);
//        setContentView(R.layout.activity_chat);
//        ButterKnife.bind(this);
//
//        activityInstance = this;
//        // runtime permission for android 6.0, just require all permissions here for simple
//        requestPermissions();
//
//        ChatHelper.getInstance().initHandler(this.getMainLooper());
//
//        Intent intent = getIntent();
//        //get user id
//        toChatUserID = intent.getIntExtra(EaseConstant.EXTRA_USER_ID_ID, -1);
//
//        //获取私聊人头像
//        mPortraltPresenter.setView(this);
//        mPortraltPresenter.ReplaceHeadPortralt(toChatUserID);
//
//        toChatUserHxID = intent.getStringExtra(EaseConstant.EXTRA_USER_HXID);
//        toChatUserName = intent.getStringExtra(EaseConstant.EXTRA_USER_NAME);
//        EaseNotifier.toUserName = toChatUserName;
//        mTitleText.setText(toChatUserName);
//        //use EaseChatFratFragment
//        chatFragment = new ChatFragment();
//        //pass parameters to chat fragment
//        chatFragment.setArguments(intent.getExtras());
//        getSupportFragmentManager().beginTransaction().add(R.id.activity_chat_layout, chatFragment).commit();
//
//        mBackImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        mMenuImage.setImageResource(R.mipmap.chat_activity_menu);
//        mMenuImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "toChatUserID = " + toChatUserID);
//                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUserHxID);
//                EMMessage lastMessage = conversation.getLastMessage();
//                if (lastMessage != null){
//                    String from = lastMessage.getFrom();
//                    Log.d("conversationfrom", "from = " + from + "my = " + Constant.APP_HX_USERID);
//                    if (from.equals(Constant.APP_HX_USERID)) {
//                        toChatUserID = lastMessage.getIntAttribute(EaseConstant.EXTRA_OTHER_USER_ID, -1);
//                    } else {
//                        toChatUserID = lastMessage.getIntAttribute(EaseConstant.EXTRA_USER_ID, -1);
//                    }
//                }
//
//                navigator.navigateToChatSetting(ChatActivity.this, toChatUserID, toChatUserHxID);
//            }
//        });
//
//        clearNotificatons();
//    }
//
//    @TargetApi(23)
//    private void requestPermissions() {
//        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
//            @Override
//            public void onGranted() {
//                //				Toast.makeText(MainActivity.t
//                // his, "All permissions have been granted", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDenied(String permission) {
//                //Toast.makeText(MainActivity.this, "Permis
//                // sion " + permission + " has been denied", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
////    EMMessageListener messageListener = new EMMessageListener() {
////
////        @Override
////        public void onMessageReceived(List<EMMessage> messages) {
////            // notify new message
////            for (EMMessage message : messages) {
////                DemoHelper.getInstance().getNotifier().onNewMsg(message);
////            }
//////            chatFragment.refreshUI();
////        }
////
////        @Override
////        public void onCmdMessageReceived(List<EMMessage> messages) {
////            //end of red packet code
////        }
////
////        @Override
////        public void onMessageRead(List<EMMessage> messages) {
////        }
////
////        @Override
////        public void onMessageDelivered(List<EMMessage> message) {
////        }
////
////        @Override
////        public void onMessageChanged(EMMessage message, Object change) {
////        }
////    };
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // unregister this event listener when this activity enters the
//        // background
//        DemoHelper sdkHelper = DemoHelper.getInstance();
//        sdkHelper.pushActivity(this);
////        EMClient.getInstance().chatManager().addMessageListener(messageListener);
//    }
//
//    @Override
//    protected void onStop() {
////        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
//        DemoHelper sdkHelper = DemoHelper.getInstance();
//        sdkHelper.popActivity(this);
//
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        activityInstance = null;
//        toChatUserID = -1;
//        EaseChatFragment.toUserImage = "";
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        Log.d(TAG, "onNewIntent: exec");
//        // make sure only one chat activity is opened
//        String username = intent.getStringExtra("userId");
//        if (toChatUserHxID.equals(username))
//            super.onNewIntent(intent);
//        else {
//            finish();
//            startActivity(intent);
//        }
//
//        clearNotificatons();
//    }
//
//    @Override
//    public void onBackPressed() {
//        chatFragment.onBackPressed();
//        if (EasyUtils.isSingleActivity(this)) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerDemoComponent.builder()
//                .applicationComponent(getApplicationComponent())
//                .activityModule(getActivityModule())
//                .build()
//                .inject(this);
//    }
//
//    @Override
//    protected void initPresenter() {
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
//    }
//
//    /**
//     * clear notifications
//     */
//    public void clearNotificatons() {
//        NotificationManager mNotificationManager =
//                (NotificationManager) AndroidApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.cancelAll();
//    }
//
//    @Override
//    public void replaceHead(String image) {
//        EaseChatFragment.toUserImage = image;
//        toChatUserImage = image;
//    }
}
