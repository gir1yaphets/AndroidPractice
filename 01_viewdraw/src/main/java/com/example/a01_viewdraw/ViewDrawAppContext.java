package com.example.a01_viewdraw;

import android.app.Application;
import android.content.Context;

/**
 * Created by pengxiaolve on 17/7/23.
 */

public class ViewDrawAppContext extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getInstance() {
        return sContext;
    }
}
