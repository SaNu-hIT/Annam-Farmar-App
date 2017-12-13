package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Adapter.Bill_Adapter;
import com.sft.annam.Adapter.KarshakaSenaAdapter;
import com.sft.annam.Model.Bill_model;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.History_Model;

import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import com.sft.annam.customfonts.MyTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SFT on 15/10/2016.
 */
public class Bill_Fragment extends Fragment implements View.OnClickListener  {
    private MyTextView tvBookingID, tvLocation, tvDate, tvMachineType, tvMachineName, tvOwnerName,
            tvOwnerPhone,  tvDay, tvRate, tvExpTime,  tvAdditionalTime, tvToatalAmount, tvAdvancePay;
    private Button btnOkay;
    private String response;
    private Fragment fragment;
    RecyclerView recyclerView;
    private ProgressDialog loading;

    private List<Bill_model> bill_models;
    private Bill_Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView=inflater.inflate(R.layout.bill,container,false);
recyclerView= (RecyclerView) rootView.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        bill_models = new ArrayList<>();
        adapter = new Bill_Adapter(getActivity(), bill_models);
        recyclerView.setAdapter(adapter);

        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        getData(fechid);
        getActivity().setTitle("Bill");
        return rootView;
    }

    private void getData(String farmer_id) {




        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        loading.show();
        String url ="http://annamagrotech.com/webservice/farmer/viewBill.php?farmer_id="+farmer_id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {





                showJSON(response);
loading.cancel();
                Log.e( "onResponse: ","res"+response );






            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Log.e( "onResponse: ","error" );
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(response);
//            JSONObject bill_details = jsonObject.getJSONObject("bill_details");
//            tvBookingID.setText(bill_details.getString("booking_id"));
//            tvLocation.setText(bill_details.getString("farmer_loc"));
//            tvDate.setText(bill_details.getString("date_and_time"));
//            tvMachineType.setText(bill_details.getString("machine_type"));
//            tvMachineName.setText(bill_details.getString("machine_name"));
//            tvOwnerName.setText(bill_details.getString("customer_name"));
//            tvOwnerPhone.setText(bill_details.getString("customer_phone"));
//            tvDay.setText(bill_details.getString("Day"));
//            tvRate.setText(bill_details.getString("rate"));
//            tvExpTime.setText(bill_details.getString("exptime"));
////            tvAdditionalTime.setText(bill_details.getString("additional_time"));
//            tvToatalAmount.setText(bill_details.getString("total_amount"));
//            String s=bill_details.getString("total_amount");
//            Log.e("TOTAL",s);
////            tvAdvancePay.setText(bill_details.getString("isAdvancePay"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private void initViews(View rootView) {
        tvBookingID = (MyTextView) rootView.findViewById(R.id.bill_bookingid);
        tvLocation = (MyTextView) rootView.findViewById(R.id.bill_location);
        tvDate = (MyTextView) rootView.findViewById(R.id.bill_date);
        tvMachineType = (MyTextView) rootView.findViewById(R.id.bill_machinetype);
        tvMachineName = (MyTextView) rootView.findViewById(R.id.bill_machinename);
        tvOwnerName = (MyTextView) rootView.findViewById(R.id.bill_ownername);
        tvOwnerPhone = (MyTextView) rootView.findViewById(R.id.bill_ownerphone);
        tvDay = (MyTextView) rootView.findViewById(R.id.bill_day);
        tvRate = (MyTextView) rootView.findViewById(R.id.bill_rate);
        tvExpTime = (MyTextView) rootView.findViewById(R.id.bill_exptime);
        tvAdditionalTime = (MyTextView) rootView.findViewById(R.id.bill_additionaltime);
        tvToatalAmount = (MyTextView) rootView.findViewById(R.id.bill_total_amount);
        tvAdvancePay = (MyTextView) rootView.findViewById(R.id.bill_advancepay);

        btnOkay = (Button) rootView.findViewById(R.id.bill_btnokay);
        btnOkay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        fragment = new Owner_Home_Fragment();
//        Utilities.getInstance(getContext()).changeHomeFragment(
//                fragment, "Owner_Home_Fragment", getActivity());
    }

    private void showJSON(String response) {
        String data_avail="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")){
                JSONArray karshakasena_details_array = jsonObject.getJSONArray("billDetails");
                JSONObject karshakasena_details;
                Bill_model a;
                for (int i=0; i<karshakasena_details_array.length(); i++){
                    karshakasena_details = karshakasena_details_array.getJSONObject(i);
                    String booking_id = karshakasena_details.getString("booking_id");

                    String bill_no = karshakasena_details.getString("bill_no");


                    String farmer_name = karshakasena_details.getString("farmer_name");


                    String farmer_phone = karshakasena_details.getString("farmer_phone");
                    String farmer_loc = karshakasena_details.getString("farmer_loc");
                    String date_and_time = karshakasena_details.getString("date_and_time");
                    String total_amount = karshakasena_details.getString("total_amount");

                    String owner_name = karshakasena_details.getString("owner_name");
                    String land_area_in_cent = karshakasena_details.getString("land_area_in_cent");


                    String machine_type = karshakasena_details.getString("machine_type");
                    String machine_name = karshakasena_details.getString("machine_name");
                    String total_time = karshakasena_details.getString("total_time");
                    String customer_name = karshakasena_details.getString("customer_name");
                    String customer_phone = karshakasena_details.getString("customer_phone");


                    a = new Bill_model( booking_id,  bill_no,  farmer_name,  farmer_phone,  farmer_loc,  date_and_time,  total_amount,  owner_name,  land_area_in_cent,  machine_type,  machine_name,  total_time,  customer_name,  customer_phone);
                    bill_models.add(a);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(),"Krarshakasena Not Available",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
