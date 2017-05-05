package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.ooooknow.flove.byxy.ooooknow.R;

/*
用户详情
 */
public class UserActivity extends AppCompatActivity {


    Toolbar mToolbar;

    FloatingActionButton fab;
    boolean fabOpened = false;
    TextView cloud;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user);
        mToolbar = (Toolbar) findViewById(R.id.user_toolbar);
        mToolbar.setTitle("FLOVE");//设置主标题
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

