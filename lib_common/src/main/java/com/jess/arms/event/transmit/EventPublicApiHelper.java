package com.jess.arms.event.transmit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION  使用该类进行注册和反注册，实现不同模块之间的通信
 * @name EventPublicApiHelper
 * @email wanglixin@icourt.cc
 * @date 2018/11/10 下午6:45
 **/
public class EventPublicApiHelper {

    private static Map<Class<? extends EventModuleApi>, EventModuleApi> sModuleApiMap = new HashMap<>();

    public static <T extends EventModuleApi> T getEventModuleApi(Class<T> claz) {
        if (claz == null) {
            return null;
        }
        EventModuleApi eventModuleApi = null;
        if (sModuleApiMap != null && sModuleApiMap.size() > 0) {
            eventModuleApi = sModuleApiMap.get(claz);
        }
        return (T) eventModuleApi;
    }

    public static void register(Class<? extends EventModuleApi> claz, EventModuleApi eventModuleApi) {
        if (sModuleApiMap.containsKey(claz)) {
            return;
        }
        sModuleApiMap.put(claz, eventModuleApi);
    }

    public static void registerAll(Map<Class<? extends EventModuleApi>, EventModuleApi> eventMaps) {
        if (eventMaps != null) {
            sModuleApiMap.putAll(eventMaps);
        }
    }

    public static void unregister(Class<? extends EventModuleApi> claz) {
        if (sModuleApiMap.containsKey(claz)) {
            sModuleApiMap.remove(claz);
        }
    }

    public static void unregisterAll(Map<Class<? extends EventModuleApi>, EventModuleApi> eventMaps) {
        if (eventMaps == null || eventMaps.size() < 1) {
            return;
        }
        Set<Class<? extends EventModuleApi>> eventSet = eventMaps.keySet();
        for (Class<? extends EventModuleApi> claz : eventSet) {
            unregister(claz);
        }
    }
}
