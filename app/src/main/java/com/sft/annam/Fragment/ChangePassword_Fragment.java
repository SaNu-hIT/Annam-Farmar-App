package com.sft.annam.Fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sft.annam.Connection.HttpRequestHelperForChangePassword;
import com.sft.annam.Model.FetchId;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;


/**
 * Created by SFT on 8/4/2016.
 */
public class ChangePassword_Fragment extends Fragment{
    EditText oldpassword,newpassword;
    Button chanepassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.changepasswordfragment,container,false);
        oldpassword=(EditText)rootView.findViewById(R.id.edt_change_old_pasfeild);
        newpassword=(EditText)rootView.findViewById(R.id.edt_change_newfeild);
        chanepassword=(Button)rootView.findViewById(R.id.btn_change_ok);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();


        chanepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Utilities.getInstance(getActivity()).isNetAvailable()) {
                    HttpRequestHelperForChangePassword helperForChangePassword = new HttpRequestHelperForChangePassword();
                    helperForChangePassword.changepassword(getActivity(),fechid,
                            oldpassword.getText().toString(),newpassword.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
                }


            }
        });
        getActivity().setTitle("Change Password");
        return rootView;
    }
}
