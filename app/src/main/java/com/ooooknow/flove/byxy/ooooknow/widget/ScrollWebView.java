package com.ooooknow.flove.byxy.ooooknow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class ScrollWebView extends WebView {
    private static final String TAG = "ScrollWebView";
    public OnScrollListener listener;

    public ScrollWebView(Context context) {
        this(context,null);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (listener != null){
            if (t - oldt <= 2){
                listener.onScrollDown();
            }
            if(oldt - t >= 2) {
                listener.onScrollUp();
            }
        }
    }

    public void setListener(OnScrollListener listener){
        this.listener = listener;
    }

    public interface OnScrollListener{
        void onScrollUp();//上滑
        void onScrollDown();//下滑
    }
}