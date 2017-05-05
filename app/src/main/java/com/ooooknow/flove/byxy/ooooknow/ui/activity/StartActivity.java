package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ooooknow.flove.byxy.ooooknow.App;
import com.ooooknow.flove.byxy.ooooknow.R;

import static com.ooooknow.flove.byxy.ooooknow.App.app;

/**
 * Created by FLOVE on 2017/5/3.
 */

public class StartActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                app = (App) getApplication();

                if (App.LoginState == true) {
                    //跳转至 MainActivity
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    //结束当前的 Activity
                    StartActivity.this.finish();
                } else {
                    //跳转至 MainActivity
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    //结束当前的 Activity
                    StartActivity.this.finish();
                }


            }
        }, 2000);
    }
}