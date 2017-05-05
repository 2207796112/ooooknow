package com.ooooknow.flove.byxy.ooooknow.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.FragmentAdapter;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.answer.Fragmentrecommended;

import java.util.ArrayList;
import java.util.List;

/**
 * 问答
 */
public class FragmentQna extends Fragment {
    private View rootView;
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_qna, container, false);
        InitView();
        return rootView;
    }

    private void InitView() {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.qna_tablayout);
        mViewPager = (ViewPager) rootView.findViewById(R.id.qna_viewpager);

        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText("推荐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("最新"));
        mTabLayout.addTab(mTabLayout.newTab().setText("热门"));
        mTabLayout.addTab(mTabLayout.newTab().setText("悬赏"));

        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("最新");
        titles.add("热门");
        titles.add("悬赏");
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new Fragmentrecommended());
        fragments.add(new Fragmentrecommended());
        fragments.add(new Fragmentrecommended());
        fragments.add(new Fragmentrecommended());
        //创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

    }

}

