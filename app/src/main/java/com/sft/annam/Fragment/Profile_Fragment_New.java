package com.sft.annam.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Connection.HttpRequestHelperProfile;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Profile_Model;
import com.sft.annam.Model.Profile_View_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import com.sft.annam.customfonts.MyTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 15-Jul-16.
 */
public class Profile_Fragment_New extends Fragment {
    MyTextView profile_name,profile_address,profile_phone,profile_mail,profile_location;
    Button profile_save,profile_cancel;
    ArrayList<Profile_View_Model>profile_fetch_models=new ArrayList<Profile_View_Model>();
    private ProgressDialog loading;
    private Button profile_edit;
    private Button profile_restpassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View profileView=inflater.inflate(R.layout.profile_design,container,false);
        initViews(profileView);
        getActivity().setTitle("Profile");
        getData();
        return profileView;

    }







    public void getData(){
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        String url = Profile_Model.DATA_URL+fechid;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

   private void initViews(View profileView){
       profile_name=(MyTextView)profileView.findViewById(R.id.textProfileName);
       profile_address=(MyTextView)profileView.findViewById(R.id.textProfileAddress);
       profile_phone=(MyTextView)profileView.findViewById(R.id.textProfilePhone);
       profile_mail=(MyTextView)profileView.findViewById(R.id.textProfileMail);
       profile_location=(MyTextView)profileView.findViewById(R.id.textProfileLocation);
       profile_edit=(Button)profileView.findViewById(R.id.buttonProfileedit);
       profile_restpassword=(Button)profileView.findViewById(R.id.buttonresetPassword);


   }

    private void showJSON(String response){
        String data_avail="";
        String name="";
        String address="";
        String phone = "";
        String email = "";
        String panchayath_loc = "";


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Profile_Model.JSON_ARRAY);
            JSONObject serverData = result.getJSONObject(0);
            data_avail = serverData.getString(Profile_Model.KEY_DATA_AVAIL);
            if (data_avail.equals("true")){
                name = serverData.getString(Profile_Model.KEY_NAME);
                address = serverData.getString(Profile_Model.KEY_ADDRESS);
                phone = serverData.getString(Profile_Model.KEY_PHONE);
                email = serverData.getString(Profile_Model.KEY_EMAIL);
                panchayath_loc = serverData.getString(Profile_Model.KEY_PANCHAYATH_LOC);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (data_avail.equals("true")){
            profile_name.setText(name);
            profile_address.setText(address);
            profile_phone.setText(phone);
            profile_mail.setText(email);
            profile_location.setText(panchayath_loc);

        } else {
            Toast.makeText(getActivity(), "Please Logout", Toast.LENGTH_SHORT).show();
        }
    }
}




