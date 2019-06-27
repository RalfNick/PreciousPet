package com.ralf.module_service.mvp.ui.provider;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.ralf.module_service.R;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;
import com.ralf.pet_provider.http.HttpUrl;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemHeaderProvider
 * @email -
 * @date 2019/06/19 21:00
 **/
public class ItemHeaderProvider extends BaseItemProvider<ExpertOnlineMultiEntity, BaseViewHolder> implements View.OnClickListener {

    @Override
    public int viewType() {
        return ExpertOnlineMultiType.TYPE_HEADER;
    }

    @Override
    public int layout() {
        return R.layout.item_expert_header_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, ExpertOnlineMultiEntity data, int position) {
        helper.getView(R.id.item_disease_knowledge_ll).setOnClickListener(this);
        helper.getView(R.id.item_disease_check_ll).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String url = "";
        String title = "";
        if (viewId == R.id.item_disease_knowledge_ll) {
            url = HttpUrl.JIBING_BAIKE;
            title = mContext.getString(R.string.disease_check);
        } else if (viewId == R.id.item_disease_check_ll) {
            url = HttpUrl.JIBING_ZICHA;
            title = mContext.getString(R.string.disease_check);
        }
        ARouter.getInstance().build(RouterConfig.Provider.WEB_VIEW_PATH)
                .withString(RouterConfig.Provider.KEY_WEB_URL, url)
                .withString(RouterConfig.Provider.KEY_WEB_TITLE, title)
                .withString(RouterConfig.Provider.KEY_WEB_MENU, "menu")
                .navigation();
    }
}
