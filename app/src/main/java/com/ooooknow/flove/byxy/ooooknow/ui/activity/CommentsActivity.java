package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.CommentAdapter;
import com.ooooknow.flove.byxy.ooooknow.model.NewsComments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FLOVE on 2017/5/4.
 */

public class CommentsActivity extends Activity {

    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    List<NewsComments.CommentsBean> datas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        datas = new ArrayList<>();
        List a = new ArrayList();
        for (int i = 0; i < 10; i++) {
            NewsComments.CommentsBean commentsBean = new NewsComments.CommentsBean();
            datas.add(commentsBean);
        }


        adapter = new CommentAdapter(datas);
        recyclerView.setAdapter(adapter);
    }
}