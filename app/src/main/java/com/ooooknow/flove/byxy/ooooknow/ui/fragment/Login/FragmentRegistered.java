package com.ooooknow.flove.byxy.ooooknow.ui.fragment.Login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ooooknow.flove.byxy.ooooknow.R;

/**
 * Created by FLOVE on 2017/5/3.
 */

public class FragmentRegistered extends Fragment {

    View view;

    TextInputLayout usernameWrapper, passwordWrapper, confirmpasswordWrapper;

    LinearLayout linearLayout;
    Button registered;
    TextView forgetPassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registered, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.registered_root);
        registered = (Button) view.findViewById(R.id.registered_btn_registered);
        usernameWrapper = (TextInputLayout) view.findViewById(R.id.registered_tl_userMobile);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.registered_tl_userPassword);
        confirmpasswordWrapper = (TextInputLayout) view.findViewById(R.id.registered_tl_confirmPassword);
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                String confirmPasswrod = confirmpasswordWrapper.getEditText().getText().toString();
                Toast.makeText(getActivity(),username+"+"+password+"+"+confirmPasswrod,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}

