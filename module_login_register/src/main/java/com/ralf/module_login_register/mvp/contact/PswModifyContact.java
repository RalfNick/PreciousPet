package com.ralf.module_login_register.mvp.contact;

import android.support.annotation.NonNull;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_login_register.entity.VertifyCodeEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PswModifyContact
 * @email -
 * @date 2018/12/10 下午2:02
 **/
public interface PswModifyContact {

    interface View extends IView {

        /**
         * 设置发送验证码按钮
         *
         * @param result true or false
         */
        void enableSendCodeBtn(boolean result);

        /**
         * 设置保存按钮状态
         *
         * @param result true or false
         */
        void enableSaveBtn(boolean result);

        /**
         * 倒计时信息
         *
         * @param msg 信息
         */
        void updateSendCodeBtn(String msg);

        /**
         * 检查文本框并重置
         */
        void checkEditTexts();
    }

    interface Model extends IModel {

        /**
         * 获取未登录时获取验证码
         *
         * @param phone  电话号码
         * @param userId 用户 id ，未登录时 -1
         * @param type   类型 验证码类型（必传），短信验证码类型 注册：0，修改密码：1，修改手机：3，修改邮箱：4
         */
        Observable<VertifyCodeEntity> getVertifyCode(String phone, String userId, @NonNull int type);

        /**
         * 请求更换新密码
         *
         * @param phone       手机
         * @param passWord    密码
         * @param vertifyCode 验证码
         */
        Observable<VertifyCodeEntity> requestModifyPsw(String phone, String passWord, String vertifyCode);
    }
}
