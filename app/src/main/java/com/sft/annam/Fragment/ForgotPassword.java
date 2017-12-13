package com.sft.annam.Fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sft.annam.Connection.HttpRequestHelperForgotPassword;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;


/**
 * Created by JESNA on 8/4/2016.
 */
public class ForgotPassword extends Fragment{
    EditText edt_forgotpassword;
    Button forgot_password;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.forgot_password,container,false);
        edt_forgotpassword=(EditText)rootView.findViewById(R.id.edt_forgotfeild);
        getActivity().setTitle("forgot password");
        forgot_password=(Button) rootView.findViewById(R.id.btn_forgot_ok);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utilities.getInstance(getActivity()).isNetAvailable()) {
                    HttpRequestHelperForgotPassword helperForforgotpassword = new HttpRequestHelperForgotPassword();
                    helperForforgotpassword.forgotpassword(getActivity(), edt_forgotpassword.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return rootView;

    }
}
