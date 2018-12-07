package com.hyphenate.easeui.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMConversationListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseConversationList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * conversation list fragment
 */
public class EaseConversationListFragment extends EaseBaseFragment {
    private final static int MSG_REFRESH = 2;
    //    protected EditText query;
//    protected ImageButton clearSearch;
    protected boolean hidden;
    protected List<EMConversation> conversationList = new ArrayList<>();
    protected EaseConversationList conversationListView;
    protected FrameLayout errorItemContainer;

    protected boolean isConflict;
    protected Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    onConnectionDisconnected();
                    break;
                case 1:
                    onConnectionConnected();
                    break;

                case MSG_REFRESH: {
                    conversationList.clear();
                    conversationList.addAll(loadConversationList());
                    conversationListView.refresh();
                    break;
                }
                default:
                    break;
            }
        }
    };
    protected EMConversationListener convListener = new EMConversationListener() {

        @Override
        public void onCoversationUpdate() {
            refresh();
        }

    };
    protected EMConnectionListener connectionListener = new EMConnectionListener() {

        @Override
        public void onDisconnected(int error) {
            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE || error == EMError.SERVER_SERVICE_RESTRICTED
                    || error == EMError.USER_KICKED_BY_CHANGE_PASSWORD || error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
                isConflict = true;
            } else {
                handler.sendEmptyMessage(0);
            }
        }

        @Override
        public void onConnected() {
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ease_fragment_conversation_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView() {
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        conversationListView = (EaseConversationList) getView().findViewById(R.id.list);
//        query = (EditText) getView().findViewById(R.id.query);
        // button to clear content in search bar
//        clearSearch = (ImageButton) getView().findViewById(R.id.search_clear);
        errorItemContainer = (FrameLayout) getView().findViewById(R.id.fl_error_item);
    }
    //    private EaseConversationListItemClickListener listItemClickListener;

    @Override
    protected void setUpView() {
        conversationList.addAll(loadConversationList());
        Log.d("KarasJoker-Pan", "KarasJoker-Pan2");
        conversationListView.init(conversationList);

        //        if(listItemClickListener != null){
        //            conversationListView.setOnItemClickListener(new OnItemClickListener() {
        //
        //                @Override
        //                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //                    EMConversation conversation = conversationListView.getItem(position);
        //                    listItemClickListener.onListItemClicked(conversation);
        //                }
        //            });
        //        }

        EMClient.getInstance().addConnectionListener(connectionListener);

//        query.addTextChangedListener(new TextWatcher() {
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                conversationListView.filter(s);
//                if (s.length() > 0) {
//                    clearSearch.setVisibility(View.VISIBLE);
//                } else {
//                    clearSearch.setVisibility(View.INVISIBLE);
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void afterTextChanged(Editable s) {
//            }
//        });
//
//        clearSearch.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                query.getText().clear();
//                hideSoftKeyboard();
//            }
//        });

        conversationListView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard();
                return false;
            }
        });
    }

    /**
     * connected to server
     */
    protected void onConnectionConnected() {
        errorItemContainer.setVisibility(View.GONE);
    }

    /**
     * disconnected with server
     */
    protected void onConnectionDisconnected() {
        errorItemContainer.setVisibility(View.VISIBLE);
    }


    /**
     * refresh ui
     */
    public void refresh() {
        if (!handler.hasMessages(MSG_REFRESH)) {
            handler.sendEmptyMessage(MSG_REFRESH);
        }
    }

    /**
     * 初始化官方账号会话
     *
     * @param hxUserId
     */
    private EMConversation initAdminConversation(String hxUserId) {
        EMChatManager emChatManager = EMClient.getInstance().chatManager();
        EMConversation tongzhiConversation = emChatManager.getConversation(hxUserId, EaseCommonUtils.getConversationType(EaseConstant.CHATTYPE_SINGLE), true);
        return tongzhiConversation;
    }

    private void putAdminConversation(Map<String, EMConversation> conversations, EMConversation adminConversation) {
        String conversationId = adminConversation.conversationId();
        if (!conversations.containsKey(conversationId)) {
            if (adminConversation.getAllMessages().size() == 0) {
                adminConversation.insertMessage(getAdminInitMessage(conversationId));
            }
            conversations.put(conversationId, adminConversation);
        }
    }

    private EMMessage getAdminInitMessage(String hxUserId) {
        String defaultString = "", toChatUserImage = "", toChatUserName = "";
        switch (hxUserId) {
            case EaseConstant.TL_ADMIN_ZAN_ID:
                defaultString = EaseConstant.DEFAULT_ADMIN_ZAN;
                toChatUserImage = EaseConstant.TL_ADMIN_ZAN_IMAGE;
                toChatUserName = EaseConstant.TL_ADMIN_ZAN_NAME;
                break;
            case EaseConstant.TL_ADMIN_TONGZHI_ID:
                defaultString = EaseConstant.DEFAULT_ADMIN_TONGZHI;
                toChatUserImage = EaseConstant.TL_ADMIN_TONGZHI_IMAGE;
                toChatUserName = EaseConstant.TL_ADMIN_TONGZHI_NAME;
                break;
            case EaseConstant.TL_ADMIN_PINGLUN_ID:
                defaultString = EaseConstant.DEFAULT_ADMIN_PINGLUN;
                toChatUserImage = EaseConstant.TL_ADMIN_PINGLUN_IMAGE;
                toChatUserName = EaseConstant.TL_ADMIN_PINGLUN_NAME;
                break;
            case EaseConstant.TL_ADMIN_GUANZHU_ID:
                defaultString = EaseConstant.DEFAULT_ADMIN_GUANZHU;
                toChatUserImage = EaseConstant.TL_ADMIN_GUANZHU_IMAGE;
                toChatUserName = EaseConstant.TL_ADMIN_GUANZHU_NAME;
                break;
            case EaseConstant.TL_ADMIN_TIXING_ID:
                defaultString = EaseConstant.DEFAULT_ADMIN_TIXING;
                toChatUserImage = EaseConstant.TL_ADMIN_TIXING_IMAGE;
                toChatUserName = EaseConstant.TL_ADMIN_TIXING_NAME;
                break;
        }
        EMMessage message = EMMessage.createTxtSendMessage(defaultString, hxUserId);
        message.setAttribute(EaseConstant.EXTRA_OTHER_USER_IMAGE, toChatUserImage);
        message.setAttribute(EaseConstant.EXTRA_OTHER_USER_NAME, toChatUserName);
        message.setAttribute(EaseConstant.CONVERSATION_TIME_SHOW, true);
        message.setAttribute(EaseConstant.INIT_CONVERSATION_SHOW, true);
        return message;
    }

    /**
     * load conversation list
     *
     * @return +
     */
    public List<EMConversation> loadConversationList() {
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<>();
        // TODO: 2017/8/16 初始化官方账号会话
        EMChatManager emChatManager = EMClient.getInstance().chatManager();
        /**通知账号会话*/
        EMConversation tongzhiConversation = initAdminConversation(EaseConstant.TL_ADMIN_TONGZHI_ID);
        putAdminConversation(conversations, tongzhiConversation);

        /**评论账号会话*/
        EMConversation pinglunConversation = initAdminConversation(EaseConstant.TL_ADMIN_PINGLUN_ID);
        putAdminConversation(conversations, pinglunConversation);

        /**未关注账号会话*/
        EMConversation guanzhuConversation = initAdminConversation(EaseConstant.TL_ADMIN_GUANZHU_ID);
        putAdminConversation(conversations, guanzhuConversation);

        /**赞账号会话*/
        EMConversation zanConversation = initAdminConversation(EaseConstant.TL_ADMIN_ZAN_ID);
        putAdminConversation(conversations, zanConversation);

        /**提醒账号会话*/
        EMConversation tixingConversation = initAdminConversation(EaseConstant.TL_ADMIN_TIXING_ID);
        putAdminConversation(conversations, tixingConversation);


        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    String conversationId = conversation.conversationId();
                    Log.d("KarasJoker-Pan", "conversationId = " + conversationId);
//                    if (conversationId == EaseConstant.TL_ADMIN_TONGZHI_ID ||
//                            conversationId == EaseConstant.TL_ADMIN_PINGLUN_ID ||
//                            conversationId == EaseConstant.TL_ADMIN_GUANZHU_ID ||
//                            conversationId == EaseConstant.TL_ADMIN_ZAN_ID ||
//                            conversationId == EaseConstant.TL_ADMIN_TIXING_ID){
//                        continue;
//                    }else {

                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(),
                            conversation));
//                    }
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }

        Map<String, EMConversation> map = new HashMap<>();
        for (EMConversation conversation : list) {
            String conversationId = conversation.conversationId();
            map.put(conversationId, conversation);
        }

        List<EMConversation> list1 = new ArrayList<EMConversation>();
        list1.add(map.get(EaseConstant.TL_ADMIN_TONGZHI_ID));
        list1.add(map.get(EaseConstant.TL_ADMIN_PINGLUN_ID));
        list1.add(map.get(EaseConstant.TL_ADMIN_ZAN_ID));
        list1.add(map.get(EaseConstant.TL_ADMIN_GUANZHU_ID));
        list1.add(map.get(EaseConstant.TL_ADMIN_TIXING_ID));

        for (EMConversation conversation : list) {
            String conversationId = conversation.conversationId();
            switch (conversationId) {
                case EaseConstant.TL_ADMIN_TONGZHI_ID:
                case EaseConstant.TL_ADMIN_PINGLUN_ID:
                case EaseConstant.TL_ADMIN_GUANZHU_ID:
                case EaseConstant.TL_ADMIN_ZAN_ID:
                case EaseConstant.TL_ADMIN_TIXING_ID:
                    break;
                default:
                    list1.add(conversation);
                    break;
            }
        }
        return list1;
    }

    /**
     * sort conversations according time stamp of last message
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }

    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (!hidden && !isConflict) {
            refresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hidden) {
            refresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(connectionListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }

//    /**
//     * set conversation list item click listener
//     *
//     * @param listItemClickListener
//     */
//    public void setConversationListItemClickListener(EaseConversationListItemClickListener listItemClickListener) {
//        //        this.listItemClickListener = listItemClickListener;
//    }
//
//    public interface EaseConversationListItemClickListener {
//        /**
//         * click event for conversation list
//         *
//         * @param conversation -- clicked item
//         */
//        void onListItemClicked(EMConversation conversation);
//    }

}
