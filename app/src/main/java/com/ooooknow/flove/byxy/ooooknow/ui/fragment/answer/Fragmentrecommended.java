package com.ooooknow.flove.byxy.ooooknow.ui.fragment.answer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.RecommendedAdapter;
import com.ooooknow.flove.byxy.ooooknow.model.AnswerInfo;
import com.ooooknow.flove.byxy.ooooknow.utils.Date_U;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 */
public class Fragmentrecommended extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    View view;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecommendedAdapter adapter;
    List<AnswerInfo> datas;
    private boolean isRefresh = false;//是否刷新中

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qna_recommended, container, false);
        InitView();
        return view;
    }

    private void InitView() {

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.recommended_srl);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recommended_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        datas = new ArrayList<AnswerInfo>();
        for (int i = 0; i <= 10; i++) {
            AnswerInfo answer=new AnswerInfo();
            answer.setAnswerUserhead("http://60.205.183.108/background.jpg");
            answer.setAnswerUsername("用户名" + i);
            String time = Date_U.timet("1492315049");
            answer.setAnswerTime(time);
            answer.setAnswerTitle("标题" + i);
            answer.setAnswerFocus("" + i);
            answer.setAnswerAnswer("" + i);
            answer.setAnswerTag("标签 " + i);

            datas.add(answer);
        }
        adapter = new RecommendedAdapter(mContext, datas);
        recyclerView.setAdapter(adapter);
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
                    for (int i = 0; i <= 10; i++) {
                        AnswerInfo answer=new AnswerInfo();
                        answer.setAnswerUserhead("http://60.205.183.108/background.jpg");
                        answer.setAnswerUsername("用户名" + i);
                        String time = Date_U.timet("1492315049");
                        answer.setAnswerTime(time);
                        answer.setAnswerTitle("更新标题" + i);
                        answer.setAnswerFocus("" + i);
                        answer.setAnswerAnswer("" + i);
                        answer.setAnswerTag("更新标签 " + i);

                        datas.add(answer);
                    }
                    adapter.notifyDataSetChanged();
                    isRefresh = false;
                }
            }, 2000);

        }
    }
}