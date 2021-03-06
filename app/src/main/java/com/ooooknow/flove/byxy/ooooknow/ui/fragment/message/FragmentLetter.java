package com.ooooknow.flove.byxy.ooooknow.ui.fragment.message;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.LetterAdapter;
import com.ooooknow.flove.byxy.ooooknow.model.LetterInfo;
import com.ooooknow.flove.byxy.ooooknow.utils.Date_U;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 */
public class FragmentLetter extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    View view;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LetterAdapter adapter;
    List<LetterInfo> datas;
    private boolean isRefresh = false;//是否刷新中
    private FloatingActionButton fab;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message_letter, container, false);
        InitView();
        return view;
    }

    private void InitView() {
        fab = (FloatingActionButton) view.findViewById(R.id.letter_fab);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.letter_srl);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.letter_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        datas = new ArrayList<LetterInfo>();
        for (int i = 0; i <= 15; i++) {
            LetterInfo letter = new LetterInfo();
            letter.setLetteName("This is item " + i);
            letter.setLetteHead("http://60.205.183.108/background.jpg");
            String time = Date_U.timet("1492315049");
            letter.setLetteTime(time);
            letter.setLetteMessage("This is item " + i);
            datas.add(letter);
        }
        adapter = new LetterAdapter(mContext, datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fab.hide();
                    } else {
                        fab.show();
                    }
                }
            }
        });

    }


    @Override
    public void onRefresh() {

        if (!isRefresh) {
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    datas.clear();
                    //显示或隐藏刷新进度条
                    swipeRefreshLayout.setRefreshing(false);
                    //修改adapter的数据



                    for (int i = 2; i < 10; i++) {
                        LetterInfo letter = new LetterInfo();
                        letter.setLetteName("This is item " + i + "news");
                        letter.setLetteHead("http://60.205.183.108/background.jpg");
                        String time = Date_U.timet("1492315049");
                        letter.setLetteTime(time);
                        letter.setLetteMessage("This is item " + i + "news");
                        datas.add(letter);
                    }
                    adapter.notifyDataSetChanged();
                    isRefresh = false;
                }
            }, 2000);

        }
    }
}