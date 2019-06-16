/*
 * MIT License
 *
 * Copyright (c) 2017 Alibaba Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ralf.module_mall.mvp.ui.view;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ralf.module_mall.constant.MallConstant;
import com.ralf.pet_provider.router.RouterConfig;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.SimpleClickSupport;

/**
 * Created by longerian on 17/3/7.
 * 处理Cell的点击事件
 */

public class MallClickSupport extends SimpleClickSupport {

    public MallClickSupport() {
        setOptimizedMode(true);
    }

    @Override
    public void defaultClick(View targetView, BaseCell cell, int type) {
        super.defaultClick(targetView, cell, type);
        Context context = targetView.getContext();
        if (type == 7) {
//            Intent payIntent = PayActivity.getCallingIntent(context);
//            context.startActivity(payIntent);
        } else {
            String action = cell.optStringParam("action") + MallConstant.ACTION;
            ARouter.getInstance().build(RouterConfig.Provider.WEB_VIEW_PATH)
                    .withString(RouterConfig.Provider.KEY_WEB_URL, action)
                    .withString(RouterConfig.Provider.KEY_WEB_TITLE, "")
                    .withString(RouterConfig.Provider.KEY_WEB_MENU, "menu")
                    .navigation();
        }

    }
}
