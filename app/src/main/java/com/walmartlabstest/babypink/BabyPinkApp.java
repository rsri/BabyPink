package com.walmartlabstest.babypink;

import android.app.Application;
import android.content.Context;

import com.walmartlabstest.babypink.network.Service;

/**
 * Created by srikaram on 06-Feb-18.
 */

public class BabyPinkApp extends Application {

    private Service mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new Service(this);
    }

    public static BabyPinkApp getApp(Context context) {
        return ((BabyPinkApp) context.getApplicationContext());
    }

    public Service getService() {
        return mService;
    }
}
