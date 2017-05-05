package com.ooooknow.flove.byxy.ooooknow.ui.fragment.Login;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ooooknow.flove.byxy.ooooknow.App;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.User;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ooooknow.flove.byxy.ooooknow.App.app;

/**
 * Created by FLOVE on 2017/5/3.
 */

public class fragmentLogin extends Fragment {


    View view;

    TextInputLayout usernameWrapper, passwordWrapper;

    LinearLayout linearLayout;
    Button login;
    TextView forgetPassword;
    String username;
    String password;

    int user_state = 0;

    private List<User> userlist;
    Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {

            try {
                String a = removeBOM(s);
                JSONObject jsonArray = new JSONObject(a);
                JSONObject jsonObject;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i + "");
                    User user = new User();
                    user.setUser_name(jsonObject.getString("user_name"));
                    user.setUser_passwrod(jsonObject.getString("user_passwrod"));
                    user.setUser_phone(jsonObject.getString("user_phone"));
                    user.setUser_praise(jsonObject.getString("user_head"));
                    user.setUser_praise(jsonObject.getString("user_praise"));
                    user.setUser_thank(jsonObject.getString("user_thank"));
                    userlist.add(user);
                }
                user_state = jsonArray.getJSONObject("0").getInt("user_state");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (user_state == 1) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                app = (App) getActivity().getApplication();
                app.setListUser(userlist);
                App.LoginState = false;

            } else {
                Toast.makeText(getActivity(), "用户名或密码错误!", Toast.LENGTH_SHORT).show();

            }
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("a", volleyError.getMessage(), volleyError);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        userlist = new ArrayList<>();
        linearLayout = (LinearLayout) view.findViewById(R.id.login_root);
        login = (Button) view.findViewById(R.id.login_btn_login);
        usernameWrapper = (TextInputLayout) view.findViewById(R.id.login_tl_userMobile);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.login_tl_userPassword);
        forgetPassword = (TextView) view.findViewById(R.id.login_tv_forgetPasswrod);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameWrapper.getEditText().getText().toString();
                password = passwordWrapper.getEditText().getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "用户名或密码不能为空，请重新输入!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://60.205.183.108/login.php", listener, errorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("username", username);
                        map.put("password", password);
                        return map;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });
        controlKeyboardLayout(linearLayout, login);
        return view;
    }

    public static final String removeBOM(String data) {

        if (TextUtils.isEmpty(data)) {
            return data;
        }

        if (data.startsWith("ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }
}

