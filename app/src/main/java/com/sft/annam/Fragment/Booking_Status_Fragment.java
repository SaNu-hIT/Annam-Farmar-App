package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Adapter.Booking_Status_Adapter;
import com.sft.annam.Model.Booking_Status_Items_model;
import com.sft.annam.Model.Booking_Status_Model;
import com.sft.annam.Model.FetchId;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/*
*
 * Created by SFT on 7/9/2016.
*/



public class Booking_Status_Fragment extends Fragment {
    private ListView listViewStatus;
    private ProgressDialog loading;
    private ArrayAdapter<String> listAdapter;
    Booking_Status_Adapter booking_status_adapter;

    ArrayList<HashMap<String, String>> bookingList;

    ArrayList<Booking_Status_Items_model> booking_status_items_models_array = new ArrayList<Booking_Status_Items_model>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View statusView = inflater.inflate(R.layout.booking_status,container,false);

        listViewStatus = (ListView) statusView.findViewById(R.id.listViewStatus);
        listViewStatus.setDivider(null);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();
        Log.e( "onCreateView: ",""+fechid );
        bookingList = new ArrayList<HashMap<String,String>>();

        getActivity().setTitle("Booking Status");
        getData(fechid);
        return statusView;
    }

    private void getData(String fechid)
    {
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        String url = Booking_Status_Model.DATA_URL+fechid;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Log.e( "onResponse: ",""+response );
                booking_status_items_models_array = new ArrayList<Booking_Status_Items_model>();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(),"Network error",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String booking_id="";
        String farmer_land_loc = "";
        String name = "";
        String phone = "";
        String machine_name = "";
        String bstatus = "";
        String booking_date = "";
        String arrival_time = "";
        String image = "";
        Integer length = 0;
        Booking_Status_Items_model booking_status_items_model;

        int i = 0;
        ArrayList<String> bookingList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.booking_status_empty_list, bookingList);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Booking_Status_Model.JSON_ARRAY);
            length = result.length();

            for(i=0;i<length;i++){
                JSONObject serverData = result.getJSONObject(i);
                booking_id = serverData.getString(Booking_Status_Model.KEY_BOOKING_ID);
                farmer_land_loc = serverData.getString(Booking_Status_Model.KEY_FARMER_LAND_LOC);
                bstatus = serverData.getString(Booking_Status_Model.KEY_BSTATUS);
                name = serverData.getString(Booking_Status_Model.KEY_NAME);
                phone = serverData.getString(Booking_Status_Model.KEY_PHONE);
                machine_name = serverData.getString(Booking_Status_Model.KEY_MACHINE_NAME);
                booking_date = serverData.getString(Booking_Status_Model.KEY_BOOKING_DATE);
                arrival_time = serverData.getString(Booking_Status_Model.KEY_ARRIVAL_TIME);
                image = serverData.getString(Booking_Status_Model.KEY_IMAGE);


                String results =
                                "Booking ID           : " +booking_id + System.getProperty ("line.separator")+
                                "Location                : " +farmer_land_loc + System.getProperty ("line.separator")+
                                "Owner Name       : " +name + System.getProperty ("line.separator")+
                                "Owner Phone      : " +phone + System.getProperty ("line.separator")+
                                "Machine Name   : " +machine_name + System.getProperty ("line.separator")+
                                "Booking Status   : " +bstatus + System.getProperty ("line.separator")

                        ;

                booking_status_items_model = new Booking_Status_Items_model(booking_id,farmer_land_loc,name,phone,machine_name,bstatus,booking_date,arrival_time,image);
                booking_status_items_models_array.add(booking_status_items_model);


                //listAdapter.add(results);

            }
            //listViewStatus.setAdapter(listAdapter);

            booking_status_adapter = new Booking_Status_Adapter(getActivity(),booking_status_items_models_array);
            listViewStatus.setAdapter(booking_status_adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (i == 0){
            String results = "No Bookings Found \n";
            listAdapter.add(results);
            results = "Make Sure You Have At least Made One Booking \n";
            listAdapter.add(results);
            listViewStatus.setAdapter(listAdapter);
        }
    }
}



/*

package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Adapter.Booking_Status_Adapter;
import com.sft.annam.Model.Booking_Status_Items_model;
import com.sft.annam.Model.Booking_Status_Model;
import com.sft.annam.Model.FetchId;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Booking_Status_Fragment extends Fragment {
    ListView booking_status_listview;
    ArrayList<Booking_Status_Items_model> booking_status_items_model_array = new ArrayList<Booking_Status_Items_model>();
    Booking_Status_Adapter booking_status_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.booking_status, container, false);

        initViews(rootView);
        getData();

        getActivity().setTitle("Booking Status");
        return rootView;
    }

    private void getData() {
        booking_status_items_model_array = getArray();
        if (booking_status_items_model_array != null) {
            if (booking_status_items_model_array.size() > 0) {
                buildUi();
            } else {
                Toast.makeText(getActivity(), "Else on Build UI" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void buildUi() {
        if (booking_status_items_model_array != null) {
            if (!booking_status_items_model_array.isEmpty()) {
                booking_status_adapter = new Booking_Status_Adapter(getActivity(),
                        booking_status_items_model_array);
                booking_status_listview.setAdapter(booking_status_adapter);
            }
        }
    }

    private ArrayList<Booking_Status_Items_model> getArray() {
        Booking_Status_Items_model booking_model;
        ArrayList<Booking_Status_Items_model> booking_array = new ArrayList<Booking_Status_Items_model>();
        booking_model = new Booking_Status_Items_model("1000","loc","nam","123","mac","con");
        booking_array.add(booking_model);
        booking_model = new Booking_Status_Items_model("1002","loc2","nam2","123","mac2","con2");
        booking_array.add(booking_model);
        booking_model = new Booking_Status_Items_model("1003","loc2","nam2","123","mac2","con2");
        booking_array.add(booking_model);
        booking_model = new Booking_Status_Items_model("1004","loc2","nam2","123","mac2","con2");
        booking_array.add(booking_model);
        booking_model = new Booking_Status_Items_model("1005","loc2","nam2","123","mac2","con2");
        booking_array.add(booking_model);
        booking_model = new Booking_Status_Items_model("1006","loc2","nam2","123","mac2","con2");
        booking_array.add(booking_model);

        return booking_array;
    }

    private void initViews(View rootView) {
        booking_status_listview = (ListView)rootView.findViewById(R.id.listViewStatus);
    }
}

*/
