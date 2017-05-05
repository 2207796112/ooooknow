package com.ooooknow.flove.byxy.ooooknow;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ooooknow.flove.byxy.ooooknow.model.User;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.MainActivity;
import com.zxy.recovery.core.Recovery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App extends Application {
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static List<User> listUser = new ArrayList<>();
    public static boolean LoginState = false;

    public static int H, W;
    public static App app;
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mAppContext = this;
        getScreen(this);
        Fresco.initialize(this);
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
        String[] urls = getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
    }

    public static Context getContext() {

        return mAppContext;
    }

    public void setListUser(List<User> listUser) {
        listUser = listUser;
    }

    public List<User> getListUser() {

        return listUser;
    }

}
