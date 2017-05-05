package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.FragmentAdapter;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.Login.FragmentRegistered;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.Login.fragmentLogin;

import java.util.ArrayList;
import java.util.List;

/*
用户详情
 */
public class LoginActivity extends AppCompatActivity {
    static LoginActivity instance;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();

    }

    private void InitView() {
        mTabLayout = (TabLayout) findViewById(R.id.qna_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.qna_viewpager);

        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText("登录"));
        mTabLayout.addTab(mTabLayout.newTab().setText("注册"));

        List<String> titles = new ArrayList<>();
        titles.add("登录");
        titles.add("注册");
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new fragmentLogin());
        fragments.add(new FragmentRegistered());
        //创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

    }


}




