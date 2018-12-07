package com.jess.arms.event.transmit;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name EventPublicApi
 * @email wanglixin@icourt.cc
 * @date 2018/11/10 下午7:05
 **/
public class EventPublicApi {

    /**
     * 主界面的更新接口
     */
    public interface MainApi extends EventModuleApi {
        /**
         * 关闭聊天室后，刷新主界面
         *
         * @param courseId
         */
        void updateMainView(String courseId);
    }

    /**
     * 直播界面状态刷新接口
     */
    public interface LiveApi extends EventModuleApi {

        /**
         * @param type     1 - 开始  2 - 结束
         * @param courseId
         */
        void changeLiveState(int type, String courseId);

        /**
         * 关闭聊天室 延迟 10 分钟
         *
         * @param courseId
         */
        void closeChat(String courseId);

        /**
         * 显示登录提示框
         *
         * @param code    编码
         * @param content 内容
         */
        void showLoginDialog(int code, String content);
    }
}
