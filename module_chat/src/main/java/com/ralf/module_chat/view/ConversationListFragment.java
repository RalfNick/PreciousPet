//package com.ralf.module_chat.view;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.ContextMenu;
//import android.view.ContextMenu.ContextMenuInfo;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView.AdapterContextMenuInfo;
//import android.widget.Toast;
//
//import com.hyphenate.chat.EMChatManager;
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.chat.EMConversation;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.easeui.EaseConstant;
//import com.hyphenate.easeui.EaseUI;
//import com.hyphenate.easeui.ui.EaseConversationListFragment;
//import com.hyphenate.easeui.utils.EaseCommonUtils;
//import com.ralf.module_chat.Constant;
//import com.ralf.module_chat.R;
//import com.ralf.pet_provider.user.constant.UserConstant;
//
//import java.util.List;
//
//public class ConversationListFragment extends EaseConversationListFragment {
//
//    //    private TextView errorText;
//    static EaseUI.EaseUserProfileProvider userProvider;
//
//    static {
//        userProvider = EaseUI.getInstance().getUserProfileProvider();
//    }
//
//    public ConversationListFragment() {
//        super();
//
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    protected void initView() {
//        super.initView();
////        View errorView = (LinearLayout) View.inflate(getActivity(), R.layout.em_chat_neterror_item, null);
////        errorItemContainer.addView(errorView);
////        errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
//    }
//
//    @Override
//    protected void setUpView() {
//        super.setUpView();
//        // register context menu
//        registerForContextMenu(conversationListView);
//        conversationListView.setOnItemClickListener((parent, view, position, id) -> {
//            EMConversation conversation = conversationListView.getItem(position);
//            String userId = conversation.conversationId();
////            String userName = "";
////            if(userProvider != null){
//////                EaseUser user = userProvider.getUser(userId);
////                userName = user.getNick();
////            }
//
//            if (userId.equals(EMClient.getInstance().getCurrentUser())) {
//                Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
//            } else {
//                // it's single chat
//                // TODO: 2017/8/15 会话列表页item点击事件拦截
//
//                switch (userId) {
//                    case EaseConstant.TL_ADMIN_ZAN_ID:
//                        Intent zanIntent = ChatSystemInformationActivity
//                                .getCllingIntent(getContext(), Constant.ChatSystemInformationType.ZAN, userId);
//                        startActivity(zanIntent);
//                        break;
//                    case EaseConstant.TL_ADMIN_TONGZHI_ID:
//                        Intent tongzhiIntent = ChatSystemInformationActivity
//                                .getCllingIntent(getContext(), Constant.ChatSystemInformationType.TONGZHI, userId);
//                        startActivity(tongzhiIntent);
//                        break;
//                    case EaseConstant.TL_ADMIN_PINGLUN_ID:
//                        Intent pinglunIntent = ChatSystemInformationActivity
//                                .getCllingIntent(getContext(), Constant.ChatSystemInformationType.PINGLUN, userId);
//                        startActivity(pinglunIntent);
//                        break;
//                    case EaseConstant.TL_ADMIN_GUANZHU_ID:
//                        Intent guanzhuIntent = ChatSystemInformationActivity
//                                .getCllingIntent(getContext(), Constant.ChatSystemInformationType.GUANZHU, userId);
//                        startActivity(guanzhuIntent);
//                        break;
//                    case EaseConstant.TL_ADMIN_TIXING_ID:
//                        Intent tixingIntent = ChatSystemInformationActivity
//                                .getCllingIntent(getContext(), Constant.ChatSystemInformationType.TIXING, userId);
//                        startActivity(tixingIntent);
//                        break;
//                    default:
//                        EMMessage lastMessage = conversation.getLastMessage();
//                        String from = lastMessage.getFrom();
//                        int userIdId = -1;
//                        String name = "";
//                        Log.d("conversationfrom", "from = " + from + "my = " + UserConstant.APP_HX_USERID);
//                        if (from.equals(UserConstant.APP_HX_USERID)) {
//                            userIdId = lastMessage.getIntAttribute(EaseConstant.EXTRA_OTHER_USER_ID, -1);
//                            name = lastMessage.getStringAttribute(EaseConstant.EXTRA_OTHER_USER_NAME, "asdas");
//                        } else {
//                            userIdId = lastMessage.getIntAttribute(EaseConstant.EXTRA_USER_ID, -1);
//                            name = lastMessage.getStringAttribute(EaseConstant.EXTRA_USER_NAME, "asdas");
//                        }
//                        Intent intent = new Intent(getActivity(), ChatActivity.class);
//                        intent.putExtra(EaseConstant.EXTRA_USER_ID, userId);
//                        intent.putExtra(EaseConstant.EXTRA_USER_NAME, name);
//                        intent.putExtra(EaseConstant.EXTRA_USER_ID_ID, userIdId);
//                        startActivity(intent);
//                        break;
//                }
//
//                EMClient instance = EMClient.getInstance();
//                EMChatManager emChatManager = instance.chatManager();
//                conversation = emChatManager.getConversation(userId, EaseCommonUtils.getConversationType(EaseConstant.CHATTYPE_SINGLE), true);
//                conversation.markAllMessagesAsRead();
//                // the number of messages loaded into conversation is getChatOptions().getNumberOfMessagesLoaded
//                // you can change this number
//                List<EMMessage> msgs = conversation.getAllMessages();
//                EMMessage emMessage0 = msgs.get(0);
//                Log.d("EaseConstant", "List<EMMessage>0" + emMessage0.getUserName() + "**content**" + emMessage0.getBody().toString());
//            }
//        });
//        super.setUpView();
//        //end of red packet code
//    }
//
//    @Override
//    protected void onConnectionDisconnected() {
//        super.onConnectionDisconnected();
////        if (NetUtils.hasNetwork(getActivity())){
////         errorText.setText(R.string.can_not_connect_chat_server_connection);
////
////        } else {
////          errorText.setText(R.string.the_current_network);
////        }
//    }
//
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        boolean deleteMessage = false;
//        if (item.getItemId() == R.id.delete_message) {
//            deleteMessage = true;
//        } else if (item.getItemId() == R.id.delete_conversation) {
//            deleteMessage = false;
//        }
//        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
//        if (tobeDeleteCons == null) {
//            return true;
//        }
////        if (tobeDeleteCons.getType() == EMConversationType.GroupChat) {
////            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
////        }
////        try {
////            // delete conversation
////            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
////            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
////            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        refresh();
//
//        return true;
//    }
//
//    public static ConversationListFragment newInstance() {
//        return new ConversationListFragment();
//    }
//
//}
