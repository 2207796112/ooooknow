package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ooooknow.flove.byxy.ooooknow.R;

/**
 * Created by FLOVE on 2017/5/5.
 */

public class FeedbackActivity extends AppCompatActivity {

    Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("意见反馈");//设置主标题
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    }
}


