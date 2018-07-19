package com.newx.base.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.newx.common.frameworks.route.launcher.ARouter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MiddlewareProxy {

    public static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 找Fragment
     *
     * @param path
     * @return
     */
    public static Fragment findFragment(String path) {
        return (Fragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    public static Fragment findFragment(String path, Bundle bundle) {
        return (Fragment) ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation();
    }

    /**
     * 结合一下，统一跳转
     */
//    public static void jumpTo(String activity) {
//        ARouter.getInstance().build(activity)
//                .navigation();
//    }

//    public static void jumpTo(String activity, String fragment) {
//        ARouter.getInstance().build(activity)
//                .withString(FRAGMENT.KEY, fragment)
//                .navigation();
//    }

//    public static void jumpTo(String activity, String fragment, Bundle bundle) {
//        ARouter.getInstance().build(activity)
//                .withString(FRAGMENT.KEY, fragment)
//                .withBundle(BUNDLE.KEY, bundle)
//                .navigation();
//    }


}
