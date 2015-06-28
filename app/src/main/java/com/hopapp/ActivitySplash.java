package com.hopapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.hopapp.persistance.AppPreferences;

public class ActivitySplash extends Activity {

    Activity activity = ActivitySplash.this;
    Context context = ActivitySplash.this;

    AppPreferences appPreferences;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        resources=getResources();
        appPreferences=AppPreferences.getAppPreferences(context);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        appPreferences.putInt(resources.getString(R.string.LScreenWidth), width);
        appPreferences.putInt(resources.getString(R.string.LScreenHeight), height);


        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(context,ActivityLogin.class));
                    finish();
                }
            }
        }).start();


    }

}
