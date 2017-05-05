package com.ooooknow.flove.byxy.ooooknow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ooooknow.flove.byxy.ooooknow.ui.fragment.index.FragmentNews;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.index.FragmentTest;

public class IndexViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"新闻", "经验", "杂谈", "生活", "职业圈"};

    public IndexViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new FragmentTest();
        } else if (position == 2) {
            return new FragmentNews();
        } else if (position == 3) {
            return new FragmentNews();
        } else if (position == 4) {
            return new FragmentNews();
        }
        return new FragmentNews();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}