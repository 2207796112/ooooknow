package com.ooooknow.flove.byxy.ooooknow.ui.fragment.answer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooooknow.flove.byxy.ooooknow.R;

/**
 * 提问
 */
public class FragmentQuestions extends Fragment  {


    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index_news, container, false);


        return view;
    }


}
