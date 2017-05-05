package com.ooooknow.flove.byxy.ooooknow.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.AboutActivity;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.FeedbackActivity;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.UserActivity;

/**
 * 我的
 */
public class FragmentMy extends Fragment implements View.OnClickListener {

    Toolbar mToolbar;
    RelativeLayout userinfo, about, feedback;
    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_my, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        mToolbar.setTitle("我的");//设置主标题

        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        userinfo = (RelativeLayout) view.findViewById(R.id.my_re_myinfo);
        about = (RelativeLayout) view.findViewById(R.id.my_re_about);
        feedback = (RelativeLayout) view.findViewById(R.id.my_re_feedback);
        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userinfo = (RelativeLayout) view.findViewById(R.id.my_re_myinfo);
        about = (RelativeLayout) view.findViewById(R.id.my_re_about);
        feedback = (RelativeLayout) view.findViewById(R.id.my_re_feedback);

        userinfo.setOnClickListener(this);
        about.setOnClickListener(this);
        feedback.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_re_myinfo:
                Intent intent = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.my_re_about:
                Intent intent2 = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.my_re_feedback:
                Intent intent3 = new Intent(getActivity(), FeedbackActivity.class);
                getActivity().startActivity(intent3);
                break;

        }
    }
}
