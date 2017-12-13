package com.sft.annam.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.History_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Shafal on 3/9/2016.
 */
public class History_Fragment extends Fragment implements View.OnClickListener {

    private EditText editTextId;
    private Button buttonGet, buttonGetDate;
    private ListView listViewResult;
    private ArrayAdapter arrayAdapter;
    private static TextView txtFromDateHistory;

    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View historyView = inflater.inflate(R.layout.history,container,false);

        editTextId = (EditText) historyView.findViewById(R.id.editTextId);
        buttonGet = (Button) historyView.findViewById(R.id.buttonGet);
        buttonGetDate = (Button) historyView.findViewById(R.id.buttonGetDate);
        listViewResult = (ListView) historyView.findViewById(R.id.listViewResult);
        txtFromDateHistory=(TextView)historyView.findViewById(R.id.txtFromDateHistory);
        buttonGet.setOnClickListener(this);
        buttonGetDate.setOnClickListener(this);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        getActivity().setTitle("History");
        initListeners();
        return historyView;
    }

    private void initListeners() {
        txtFromDateHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        buttonGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(),"Button Clicked",Toast.LENGTH_SHORT).show();
                String date = txtFromDateHistory.getText().toString().trim();
                if (date.equals("")) {
                    Toast.makeText(getActivity(), "Please enter a  date", Toast.LENGTH_LONG).show();
                    return;
                }
                loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
                FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
                final String fechid = fetchId.getUser_id();
                String url="http://annamagrotech.com/webservice/farmer/history_get_date.php?date="+date+"&fid="+fechid;
//                String url = History_Model.DATA_URL+"null"+"&fid="+fechid+"&date="+date;

                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSONDate(response);
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);


            }
        });
    }

    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(getActivity(), "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        String url = History_Model.DATA_URL+editTextId.getText().toString().trim()+"&fid="+fechid+"&date=null";
        Log.e( "getData: ", ""+url);

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
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response){
        String data_avail="";
        String machine_type_name="";
        String machine_name="";
        String farmer_land_loc = "";
        String total_hr = "";
        String total_amt = "";
        String owner_name = "";
        String owner_phone = "";
        String booking_status = "";
        Log.e( "showJSON: ",""+response );
        //FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        //String fechid = fetchId.getUser_id();
        //Toast.makeText(getActivity(), "ID : " + fechid, Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(History_Model.JSON_ARRAY);
            JSONObject serverData = result.getJSONObject(0);
            data_avail = serverData.getString(History_Model.KEY_DATA_AVAIL);
            if (data_avail.equals("true")){
                machine_type_name = serverData.getString(History_Model.KEY_MACHINE_TYPE_NAME);
                machine_name = serverData.getString(History_Model.KEY_MACHINE_NAME);
                farmer_land_loc = serverData.getString(History_Model.KEY_FARMER_LAND_LOC);
                total_hr = serverData.getString(History_Model.KEY_TOTAL_HR);
                total_amt = serverData.getString(History_Model.KEY_TOTAL_AMT);
                owner_name = serverData.getString(History_Model.KEY_OWNER_NAME);
                owner_phone = serverData.getString(History_Model.KEY_OWNER_PHONE);
                booking_status = serverData.getString("booking_status");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (data_avail.equals("true")){
            String[] result = {
                    "Machine Type : "+machine_type_name,
                    "Machine Name : "+machine_name,
                    "Location     : "+farmer_land_loc,
                    "Total Hours  : "+total_hr,
                    "Total Amount : "+total_amt,
                    "Owner Name   : "+owner_name,
                    "Owner Phone  : "+owner_phone,
                    "Booking status  : "+booking_status
            };
            arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, result);
            listViewResult.setAdapter(arrayAdapter);
        } else {
            String[] result = {
                    "History Not Found",
                    "Make Sure You Entered Correct Booking ID"
            };
            arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, result);
            listViewResult.setAdapter(arrayAdapter);
        }
    }


    private void showJSONDate(String response){
        String data_avail="";
        String machine_type_name="";
        String machine_name="";
        String farmer_land_loc = "";
        String total_hr = "";
        String total_amt = "";
        String owner_name = "";
        String owner_phone = "";
        String booking_status = "";
        //FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        //String fechid = fetchId.getUser_id();
        //Toast.makeText(getActivity(), "ID : " + fechid, Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("history");
            JSONObject serverData = result.getJSONObject(0);;

                machine_type_name = serverData.getString(History_Model.KEY_MACHINE_TYPE_NAME);
                machine_name = serverData.getString(History_Model.KEY_MACHINE_NAME);
                farmer_land_loc = serverData.getString(History_Model.KEY_FARMER_LAND_LOC);
                total_hr = serverData.getString(History_Model.KEY_TOTAL_HR);
                total_amt = serverData.getString(History_Model.KEY_TOTAL_AMT);
                owner_name = serverData.getString(History_Model.KEY_OWNER_NAME);
                owner_phone = serverData.getString(History_Model.KEY_OWNER_PHONE);
                booking_status = serverData.getString("booking_status");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (data_avail.equals("true")){
            String[] result = {
                    "Machine Type : "+machine_type_name,
                    "Machine Name : "+machine_name,
                    "Location     : "+farmer_land_loc,
                    "Total Hours  : "+total_hr,
                    "Total Amount : "+total_amt,
                    "Owner Name   : "+owner_name,
                    "Owner Phone  : "+owner_phone,
                    "Booking status  : "+booking_status
            };
            arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, result);
            listViewResult.setAdapter(arrayAdapter);
        } else {
            String[] result = {
                    "History Not Found",
                    "Make Sure You Entered Correct Booking ID"
            };
            arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, result);
            listViewResult.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onClick(View v) {

        getData();
    }

    @SuppressLint("ValidFragment")
    private static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }


        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            txtFromDateHistory.setText(year+"-"+month+"-"+day);
        }
    }

}

