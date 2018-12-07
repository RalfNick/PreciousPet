package com.jess.arms.event.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.jess.arms.integration.AppManager;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name EventManager
 * @email wanglixin@icourt.cc
 * @date 2018/09/04 下午5:06
 **/
public class EventManager {

    private static volatile EventManager sEventManager;

    private static LocalBroadcastManager sLocalBroadcastManager;

    private EventManager() {
        sLocalBroadcastManager = LocalBroadcastManager.getInstance(AppManager.getAppManager().getApplication());
    }

    private static EventManager initEventManager() {
        if (sEventManager == null) {
            synchronized (EventManager.class) {
                if (sEventManager == null) {
                    sEventManager = new EventManager();
                }
            }
        }
        return sEventManager;
    }

    public static void registerBroadcast(LocalBroadcastReceiver receiver, IntentFilter filter) {
        initEventManager();
        sLocalBroadcastManager.registerReceiver(receiver, filter);
    }

    public static void sendBroadcast(Intent intent) {
        initEventManager();
        sLocalBroadcastManager.sendBroadcast(intent);
    }

    public static void sendBroadcastSync(Intent intent) {
        initEventManager();
        sLocalBroadcastManager.sendBroadcastSync(intent);
    }

    public static void unregisterBroadcast(LocalBroadcastReceiver receiver) {
        sLocalBroadcastManager.unregisterReceiver(receiver);
    }

    public static class LocalBroadcastReceiver extends BroadcastReceiver {

        private LocalBroadcastListener mListener;

        public LocalBroadcastReceiver(LocalBroadcastListener listener) {
            mListener = listener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mListener != null) {
                mListener.broadcastListener(intent);
            }
        }
    }

    public interface LocalBroadcastListener {
        void broadcastListener(Intent intent);
    }

}
