package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contract.HeatPraiseContract;
import com.ralf.module_community.mvp.model.HeatPraiseModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseModule
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@Module
public class HeatPraiseModule {

    private HeatPraiseContract.DayView mDayView;
    private HeatPraiseContract.HistoryView mHistoryView;

    public HeatPraiseModule(HeatPraiseContract.DayView view) {
        mDayView = view;
    }

    public HeatPraiseModule(HeatPraiseContract.HistoryView view) {
        mHistoryView = view;
    }

    @FragmentScope
    @Provides
    public HeatPraiseContract.DayView provideHeatPraiseDayView() {
        return mDayView;
    }

    @FragmentScope
    @Provides
    public HeatPraiseContract.HistoryView provideHeatPraiseHistoryView() {
        return mHistoryView;
    }


    @FragmentScope
    @Provides
    public HeatPraiseContract.Model provideHeatPraiseModel(HeatPraiseModel model) {
        return model;
    }
}
