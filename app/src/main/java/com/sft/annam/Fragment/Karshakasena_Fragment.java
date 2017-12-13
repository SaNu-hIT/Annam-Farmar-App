package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Adapter.KarshakaSenaAdapter;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.KarshakaItem_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SFT on 15/2/2017.
 */
public class Karshakasena_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private KarshakaSenaAdapter adapter;
    TextView myLocation;
    Location_MOdel location_mOdel;
    String latitude,longtude;
    private List<KarshakaItem_Model> karshakaitem_modelList;
    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView =inflater.inflate(R.layout.booknow_screen,container,false);
        myLocation=(TextView)rootView.findViewById(R.id.myLocation);
        setLocation();
        getActivity().setTitle("Karshakasena");
        // -------------- Added ---------------------
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        karshakaitem_modelList = new ArrayList<>();
        adapter = new KarshakaSenaAdapter(getActivity(), karshakaitem_modelList,getFragmentManager());
        recyclerView.setAdapter(adapter);
        getData();
        //prepareMachineCategoryCards();
        // -------------- End -----------------------
        return rootView;
    }

    private void getData() {
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        String url = "http://annamagrotech.com/webservice/farmer/getkarshakasena.php?farmer_id="+fechid;

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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String data_avail="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")){
                JSONArray karshakasena_details_array = jsonObject.getJSONArray("karshakasena_details");
                JSONObject karshakasena_details;
                KarshakaItem_Model a;
                for (int i=0; i<karshakasena_details_array.length(); i++){
                    karshakasena_details = karshakasena_details_array.getJSONObject(i);
                    String karshakasena_id = karshakasena_details.getString("karshakasena_id");
                    String title = karshakasena_details.getString("title");
                    String nature_of_work = karshakasena_details.getString("nature_of_work");
                    String radious = karshakasena_details.getString("radious");
                    String labours_no = karshakasena_details.getString("labours_no");
                    String ownerid = karshakasena_details.getString("owner_id");
                    String location = karshakasena_details.getString("location");
                    a = new KarshakaItem_Model(karshakasena_id,ownerid,title,location,nature_of_work,labours_no,radious);
                    karshakaitem_modelList.add(a);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(),"Krarshakasena Not Available",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    TODO: location  null exception.

    private void setLocation() {
        location_mOdel= DataBaseHelperFor_location.getProfileOffline(getActivity());
        if(location_mOdel.equals("")) {
            Toast.makeText(getActivity(), "Select location", Toast.LENGTH_SHORT).show();
        }
        else {
            String place = location_mOdel.getPlace();
            String state = location_mOdel.getState();
            String contry = location_mOdel.getCountry();
            latitude = location_mOdel.getLatitude();
            longtude = location_mOdel.getLongitude();
            String location = place + "," + state + "," + contry;
            myLocation.setText(location);
        }
    }
}
