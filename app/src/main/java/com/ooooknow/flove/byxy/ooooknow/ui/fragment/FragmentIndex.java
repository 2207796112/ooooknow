package com.ooooknow.flove.byxy.ooooknow.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.IndexViewPagerAdapter;

/**
 * 首页
 */
public class FragmentIndex extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private IndexViewPagerAdapter myFragmentPagerAdapter;

    private AppBarLayout index_app_bar;
    Toolbar mToolbar;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_index, container, false);

        //给于menu权限
        setHasOptionsMenu(true);
        mViewPager = (ViewPager) view.findViewById(R.id.index_viewpager);

        myFragmentPagerAdapter = new IndexViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);


        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) view.findViewById(R.id.index_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);


        return view;
    }

}
