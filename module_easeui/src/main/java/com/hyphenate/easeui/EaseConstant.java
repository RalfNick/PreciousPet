/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyphenate.easeui;

public class EaseConstant {


    public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

    public static final String MESSAGE_TYPE_RECALL = "message_recall";

    public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";

    public static final String MESSAGE_ATTR_AT_MSG = "em_at_list";
    public static final String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";

    public static String TO_HX_USERID = "";
    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;
    public static final String CONVERSATION_TIME_SHOW = "ConversationTimeShow";
    public static final String INIT_CONVERSATION_SHOW = "InitConversationShow";
    public static final String SEND_MESSAGE_TAG = "sendMessageTag";

    public static final String TL_ADMIN_ZAN_ID = "tl_admin_praise";
    public static final String TL_ADMIN_ZAN_NAME = "赞";
    public static final String TL_ADMIN_ZAN_IMAGE = "file:///android_asset/chat_system_zan.png";
    public static final String DEFAULT_ADMIN_ZAN = "您没有新的赞哦";

    public static final String TL_ADMIN_TONGZHI_ID = "tl_admin_tongzhi";
    public static final String TL_ADMIN_TONGZHI_NAME = "通知";
    public static final String TL_ADMIN_TONGZHI_IMAGE = "file:///android_asset/chat_system_tongzhi.png";
    public static final String DEFAULT_ADMIN_TONGZHI = "您没有新的通知哦";

    public static final String TL_ADMIN_PINGLUN_ID = "tl_admin_pinglun";
    public static final String TL_ADMIN_PINGLUN_NAME = "评论";
    public static final String TL_ADMIN_PINGLUN_IMAGE = "file:///android_asset/chat_system_pinglun.png";
    public static final String DEFAULT_ADMIN_PINGLUN = "您没有新的评论哦";

    public static final String TL_ADMIN_GUANZHU_ID = "tl_admin_guanzhu";
    public static final String TL_ADMIN_GUANZHU_NAME = "未关注人的消息";
    public static final String TL_ADMIN_GUANZHU_IMAGE = "file:///android_asset/chat_system_guanzhu.png";
    public static final String DEFAULT_ADMIN_GUANZHU = "您没有新的关注消息哦";

    public static final String TL_ADMIN_TIXING_ID = "tl_admin_tixing";
    public static final String TL_ADMIN_TIXING_NAME = "提醒";
    public static final String TL_ADMIN_TIXING_IMAGE = "file:///android_asset/chat_system_tixing.png";
    public static final String DEFAULT_ADMIN_TIXING = "您还没有新的提醒哦";

    public static final String EXTRA_CHAT_TYPE = "chatType";
    public static final String EXTRA_USER_ID = "userId";// 对方的ID
    public static final String EXTRA_USER_ID_ID = "userIdId";// 对方的ID
    public static final String EXTRA_USER_HXID = "userId";// 对方的环信ID
    public static final String EXTRA_USER_NAME = "userName";// 对方的昵称
    public static final String EXTRA_USER_IMAGE = "userImage";// 对方的头像
    public static final String EXTRA_OTHER_USER_NAME = "otherUserName";// 对方的昵称
    public static final String EXTRA_OTHER_USER_IMAGE = "otherUserImage";// 对方的头像
    public static final String EXTRA_OTHER_USER_ID = "otherUserId";// 对方的头像

    public static final String EXTRA_CONTENT_USER_HEAD_IMAGE = "contentHeadImage";// 点赞人/评论人/通知人 头像
    public static final String EXTRA_CONTENT_USER_NAME = "contentUserName";// 点赞人/评论人/通知人 昵称
    public static final String EXTRA_CONTENT_USER_ID = "contentUserId";// 点赞人/评论人/通知人 ID
    public static final String EXTRA_CONTENT_USER_AGE = "contentUserAge";// 点赞人/评论人/通知人 年龄
    public static final String EXTRA_CONTENT_USER_SEX = "contentUserSex";// 点赞人/评论人/通知人 年龄
    public static final String EXTRA_CONTENT_USER_PROVINCE = "contentProvince";// 点赞人 省份
    public static final String EXTRA_CONTENT_USER_CITY = "contentCity";// 点赞人 城市

    public static final String EXTRA_CONTENT_IMAGE = "contentImage";// 点赞/评论/通知 内容图片
    public static final String EXTRA_CONTENT_IMAGE_TYPE = "contentImageType";// 点赞/评论/通知 内容图片类型
    public static final String EXTRA_CONTENT_ID = "contentId";// 点赞/评论/通知 内容ID
    public static final String EXTRA_CONTENT_TIME = "contentTime";// 点赞/评论/通知 内容时间戳
    public static final String EXTRA_CONTENT_MESSAGE = "contentMessage";// 点赞/评论/通知 内容
    public static final String EXTRA_CONTENT_TYPE = "contentType";// 点赞/评论/通知 类型
    public static final String EXTRA_CONTENT_BE_MESSAGE = "contentBeMessage";// 点赞/评论/通知 被评论内容

}
