package com.ralf.www.module_login_register.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.www.module_login_register.entity.VertifyCodeEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterContact
 * @email -
 * @date 2018/12/11 上午11:06
 **/
public interface RegisterContact {

    interface View extends IView {

        /**
         * 改变注册按钮是否可点击
         *
         * @param isEnable true or false
         */
        void enableRegisterbBtn(boolean isEnable);

        /**
         * 设置发送验证码按钮
         *
         * @param isEnable true or false
         */
        void enableSendCodeBtn(boolean isEnable);

        /**
         * 倒计时信息
         *
         * @param msg 信息
         */
        void updateSendCodeBtn(String msg);

        /**
         * 检查按钮状态，并重置
         */
        void checkBtnState();

        /**
         * 跳转到下一个页面，个人设置信息页面
         *
         * @param phone    电话
         * @param passWord 密码
         * @param toastMsg 吐司信息
         */
        void jumpToUserSettingPage(String phone, String passWord, String toastMsg);
    }

    interface Model extends IModel {

        /**
         * 获取未登录时获取验证码
         *
         * @param phone    电话号码
         * @param password 用户密码（这地方不好，密码暴露,破解后容易获取）
         * @param
         */
        Observable<VertifyCodeEntity> vertifyPhone(String phone, String password);

        /**
         * 获取未登录时获取验证码
         *
         * @param phone 电话号码
         *              <p>
         *              类型 验证码类型（必传），短信验证码类型 注册：0，修改密码：1，修改手机：3，修改邮箱：4
         */
        Observable<VertifyCodeEntity> getVertifyCode(String phone);

        /**
         * 获取未登录时获取验证码
         *
         * @param phone       电话号码
         * @param vertifyCode 验证码
         * @param
         */
        Observable<VertifyCodeEntity> vertifyCode(String phone, String vertifyCode);

        /**
         * 获取未登录时获取验证码
         *
         * @param image    头像
         * @param nickName 昵称
         * @param phone    电话号码
         * @param password 密码
         * @param sex      性别
         * @param
         */
        Observable<VertifyCodeEntity> registerNewUser(String image, String nickName,
                                                      String phone, String password, int sex);
    }
}
