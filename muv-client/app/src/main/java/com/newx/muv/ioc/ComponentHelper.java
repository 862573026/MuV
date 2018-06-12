package com.newx.muv.ioc;

import com.newx.muv.ioc.component.AppComponent;

/**
 * Created by newx on 18-4-26.
 */

public class ComponentHelper {

    private static AppComponent mComponent;

    public static AppComponent getComponent() {
        return mComponent;
    }

    public static void setComponent(AppComponent component) {
        mComponent = component;
    }
}
