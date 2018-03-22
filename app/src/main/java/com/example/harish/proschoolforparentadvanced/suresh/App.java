package com.example.harish.proschoolforparentadvanced.suresh;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.example.harish.proschoolforparentadvanced.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by subbu on 19-12-2017.
 */

public class App extends Application {

    public App(){

    }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Handlee-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}