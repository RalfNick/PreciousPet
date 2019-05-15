package com.ralf.module_news.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ralf.module_news.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsEmptyFragment
 * @email -
 * @date 2019/05/15 19:23
 **/
public class NewsEmptyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_layout, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
