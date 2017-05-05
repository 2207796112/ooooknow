package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ooooknow.flove.byxy.ooooknow.R;

/**
 * Created by FLOVE on 2017/5/5.
 */

public class AboutActivity extends AppCompatActivity{

    Toolbar mToolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("关于我们");//设置主标题
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    }
}
