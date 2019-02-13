package com.ralf.pet_provider.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.jess.arms.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.ralf.pet_provider.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.ShareBoardlistener;

import static com.umeng.socialize.bean.SHARE_MEDIA.QQ;
import static com.umeng.socialize.bean.SHARE_MEDIA.QZONE;
import static com.umeng.socialize.bean.SHARE_MEDIA.SINA;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN_CIRCLE;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetShare
 * @email -
 * @date 2019/02/12 上午11:18
 **/
public class PetShare {

    private static final String TAG = "PetShare";
    public static String shareTitleOfCommunity = "我分享了%s的牵宠图片,快来围观吧!";
    public static final String SHARE_CANCEL = "取消分享";
    public static final String SHARE_SUCCESS = "分享成功";
    public static final String SHARE_TITLE = "来自牵宠的分享";
    public static final String SHARE_PET_DETAILS_DESC = "这里记录了我家爱宠的每一次成长,一起来见证它的成长日记吧!";
    public static final String SHARE_SELECTED_DESC = "牵宠有爱, 最有趣的宠物社区";
    public static String SHARE_JOIN_TITLE = "加入牵宠,新手福利领不停!";
    public static String SHARE_JOIN_DESC = "下载注册牵宠app领取新手福利,更有丰富的宠物社区活动等你来参与!";

    private final Context context;
    private final UMShareListener umShareListener;
    private final String url;
    private final String imgUrl;
    private final int imgRes;
    private final String title;
    private final String desc;
    private final String withText;
    private ShareAction mShareAction;
    private UMWeb mUmWeb;

    private PetShare(ShareBuilder builder) {
        this.context = builder.context;
        this.umShareListener = builder.umShareListener;
        this.url = builder.url;
        this.imgUrl = builder.imgUrl;
        this.imgRes = builder.imgRes;
        this.title = builder.title;
        this.desc = builder.desc;
        this.withText = builder.withText;
    }

    public UMShareListener getUmShareListener() {
        return umShareListener;
    }

    public String getUrl() {
        return url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getImgRes() {
        return imgRes;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void share() {
        checkShareAction();
        setUMWeb();
        mUmWeb.setTitle(this.title);
        mUmWeb.setDescription(this.desc);
        mShareAction.withMedia(mUmWeb);
        mShareAction.withText(this.desc);
        mShareAction.open();
    }

    /**
     * release需要在activity#onDestroy()回调中释放资源,必须调用
     */
    public static void release(Context context) {
        UMShareAPI.get(context).release();
    }

    /**
     * onActivityResult需要在activity#onActivityResult()回调中同步调用,以监听分享结果
     */
    public static void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * share image
     *
     * @param bitmap target Bitmap
     */
    public void shareImage(Bitmap bitmap) {
        checkShareAction();
        UMImage umImage = new UMImage(this.context, bitmap);
        mShareAction.withMedia(umImage).open();
    }

    private void checkShareAction() {
        if (mShareAction == null) {
            mShareAction = new ShareAction((Activity) this.context)
                    .setDisplayList(WEIXIN, WEIXIN_CIRCLE, SINA, QQ, QZONE)
                    .setCallback(this.umShareListener);
        }
    }

    /**
     * 分享
     *
     * @param typeEnum 类型
     */
    public void shareToOthers(ShareTypeEnum typeEnum) {
        setUMWeb();
        switch (typeEnum) {
            case SHARE_PET:
                mUmWeb.setTitle(desc);
                break;
            case SHARE_HELP:
            case SHARE_PAIR:
            case SHARE_ADOPTION:
            case SHARE_CHANNEL_POST:
            case SHARE_COMMUNITY_FEATURED:
                mUmWeb.setTitle(title);
                mUmWeb.setDescription(" ");
                break;
            case SHARE_CHANNEL:
                mUmWeb.setTitle(title.replace("[", "").replace("]", ""));
                mUmWeb.setDescription(desc);
                break;
            case SHARE_PET_TRACE:
                mUmWeb.setTitle(desc);
                mUmWeb.setDescription(" ");
                break;
            case SHARE_SAME_CITY:
                mUmWeb.setTitle(title + "\"" + desc + "\"");
                mUmWeb.setDescription(" ");
                break;
            default:
                mUmWeb.setTitle(title);
                mUmWeb.setDescription(desc);
                break;
        }
        doShare();
    }

    private void doShare() {
        ShareBoardlistener shareBoardlistener = (snsPlatform, shareMedia) -> {
            switch (shareMedia) {
                case WEIXIN_CIRCLE:
                case QZONE:
                    break;
                default:
                    mUmWeb.setTitle(title);
                    mUmWeb.setDescription(desc);
                    break;
            }
            new ShareAction((Activity) context)
                    .setPlatform(shareMedia)
                    .withMedia(mUmWeb)
                    .withText(withText)
                    .setCallback(umShareListener)
                    .share();
        };
        new ShareAction((Activity) this.context)
                .setDisplayList(WEIXIN, WEIXIN_CIRCLE, SINA, QQ, QZONE)
                .setShareboardclickCallback(shareBoardlistener)
                .setCallback(this.umShareListener)
                .open();
    }

    private void setUMWeb() {
        if (mUmWeb == null) {
            mUmWeb = new UMWeb(this.url);
        }
        UMImage umImage;
        if (!TextUtils.isEmpty(this.imgUrl)) {
            umImage = new UMImage(this.context, this.imgUrl);
        } else {
            umImage = new UMImage(this.context, R.mipmap.app_icon);
        }
        mUmWeb.setThumb(umImage);
    }

    public static class ShareBuilder {

        private final Context context;
        private UMShareListener umShareListener;
        private String url = "";
        private String imgUrl = "";
        private int imgRes = 0;
        private String title = "";
        private String desc = "";
        private String withText = "";

        private ShareBuilder(Context context) {
            this.context = context;
            umShareListener = new PetShareListener();
        }

        public static ShareBuilder with(Context context) {
            return new ShareBuilder(context);
        }

        public ShareBuilder umShareListener(UMShareListener umShareListener) {
            this.umShareListener = umShareListener;
            return this;
        }

        public ShareBuilder url(String url) {
            this.url = url;
            return this;
        }

        public ShareBuilder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public ShareBuilder imgRes(int imgRes) {
            this.imgRes = imgRes;
            return this;
        }

        public ShareBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ShareBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public ShareBuilder withText(String withText) {
            this.withText = withText;
            return this;
        }

        public PetShare build() {
            return new PetShare(this);
        }
    }

    /**
     * 友盟分享回调
     */
    static class PetShareListener implements UMShareListener {

        @Override
        public void onStart(SHARE_MEDIA shareMedia) {
            Logger.d("PetShareListener - onStart");
        }

        @Override
        public void onResult(SHARE_MEDIA shareMedia) {
            Logger.d("PetShareListener - onResult");
            ToastUtils.showShort(SHARE_SUCCESS);
        }

        @Override
        public void onError(SHARE_MEDIA shareMedia, Throwable throwable) {
            Logger.e("PetShareListener - onError");
        }

        @Override
        public void onCancel(SHARE_MEDIA shareMedia) {
            Logger.d("PetShareListener - onCancel");
        }
    }

    /**
     * 分享的类型枚举
     */
    public enum ShareTypeEnum {
        /**
         * 宠物分享
         * QQ(微信):标题显示:宠物名称+的宠物主页  内容:这里记录了我家爱宠的每一次成长,一起来见证它的成长日记吧!
         * 空间(朋友圈):这里记录了我家爱宠的每一次成长,一起来见证它的成长日记吧!
         */
        SHARE_PET,

        /**
         * 频道分享
         * 频道:  QQ (微信) 标题:[美宠秀秀]  标题下显示:频道简介
         * 空间(朋友圈): 标题:美宠秀秀   标题下显示:频道简介
         */
        SHARE_CHANNEL,
        /**
         * 频道帖子分享
         * 帖子:(无图片显示APP图标)QQ(微信):标题:显示发帖子的内容  标题下面显示:有宠有爱 最有趣的宠物社区
         * 空间(朋友圈):帖子内容
         */
        SHARE_CHANNEL_POST,

        /**
         * 配对分享
         * 配对QQ(微信):标题:年龄+大的+宠物名字(宠物品种)求配对~   标题下面显示:配对说明
         * 空间(朋友圈):年龄+大的+宠物名字(宠物品种)求配对~
         */
        SHARE_PAIR,

        /**
         * 宠物领养分享
         * 领养QQ(微信)分享:标题 :几个月大的宠物品种求领养~(月份显示领养页面的月份),标题下面显示:宠物描述
         * 空间(朋友圈)分享:几个月大的宠物品种求领养 ~
         */
        SHARE_ADOPTION,

        /**
         * 同城服务分享
         * 同城服务,QQ(微信)分享:标题:分享了牵宠的同城服务   标题下面显示商家名称
         * 空间(朋友圈)分享:分享了牵宠的同城服务"商家名称"
         */
        SHARE_SAME_CITY,

        /**
         * 分享求助详情
         */
        SHARE_HELP,

        /**
         * 分享6宠
         * 遛宠:QQ(微信)分享:标题:一起遛宠吧   标题下面显示:我分享了+用户名的遛宠轨迹,陪伴是最幸福的时光,一起遛宠约起来!
         * QQ空间(朋友圈)分享:我分享了+用户名的遛宠轨迹,陪伴是最幸福的时光,一起遛宠约起来!
         */
        SHARE_PET_TRACE,

        /**
         * 社区精选分享
         * 动态 :QQ空间(朋友圈): 我分享了+用户名的牵宠图片,快来围观吧!
         * QQ(微信)分享标题: 我分享了+用户名的牵宠图片快来围观吧!   标题下面显示发布内容
         */
        SHARE_COMMUNITY_FEATURED
    }
}
