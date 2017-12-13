package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.nearby.messages.internal.Update;
import com.sft.annam.Adapter.Machine_List_Adapter;
import com.sft.annam.Connection.HttpRequestHelperForMachineView;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_Machineries;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Machineries_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/13/2016.
 */
public class Machineries_Fragment extends Fragment {
    ListView machine_list;
    ArrayList<Machineries_Model> machineries_model_array=new ArrayList<Machineries_Model>();
    Machine_List_Adapter machineries_adapter;
    Location_MOdel location_mOdel;
    String[] minDistance;
    String[] macID;
    String[] numOwners;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.machineries_fragment,container,false);

        initViews(rootView);

//        AsyncTaskRunner runner = new AsyncTaskRunner();
//        runner.execute();

        getServiceList();

        getActivity().setTitle("Machineries");
        return rootView;
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            //publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                // Do your long operations here and return the result
                getData();
                Thread.sleep(1000);
                resp = "success";
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            getServiceList();
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getContext(),"Loading",
                    "Please Wait...");
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
    private void fetchMachine_list() {
        if(Utilities.getInstance(getActivity()).isNetAvailable()) {

            FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
            String fechid = fetchId.getUser_id();
            HttpRequestHelperForMachineView httptomachinelist = new HttpRequestHelperForMachineView();
            httptomachinelist.machineView(getActivity(), fechid);
        }
        else{
//            Toast.makeText(getActivity(), "" + getResources().getString(R.string.internet),
//                    Toast.LENGTH_SHORT).show();
        }
    }
    private void getServiceList() {
        machineries_model_array = DataBaseHelperFor_Machineries.getMachinesList(getActivity());
        //Log.e("", "?????????" + machineries_model_array.size());
        if (machineries_model_array != null) {
            if (machineries_model_array.size() > 0) {
                buildUi();
            } else {
                fetchMachine_list();

            }
        }


    }

    private void buildUi() {
        if (machineries_model_array != null) {
            if (!machineries_model_array.isEmpty()) {
                machineries_adapter = new Machine_List_Adapter(getActivity(),
                        machineries_model_array,minDistance,numOwners);
                machine_list.setAdapter(machineries_adapter);
            }
        }

    }

    private void initViews(View rootView) {
        machine_list=(ListView)rootView.findViewById(R.id.machinelist);


    }

    private void getData() {
        location_mOdel= DataBaseHelperFor_location.getProfileOffline(getContext());
        String latitude=location_mOdel.getLatitude();
        String longitude=location_mOdel.getLongitude();
        String url = "http://annamagrotech.com/webservice/farmer/all_machines_distance.php?"
                + "latitude=" + latitude
                + "&longitude=" + longitude;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e( "onResponse: ",response.toString() );
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jarray_MachineryDetails = jsonObject.getJSONArray("machinery_details");
            int sizeofArray = jarray_MachineryDetails.length();
            macID = new String[sizeofArray];
            minDistance = new String[sizeofArray];
            numOwners = new String[sizeofArray];
            JSONObject machinery_details;
            for (int i=0;i<sizeofArray;i++){
                machinery_details = jarray_MachineryDetails.getJSONObject(i);
                macID[i] = machinery_details.getString("machine_id");
                minDistance[i] = machinery_details.getString("min_distance");
                numOwners[i] = machinery_details.getString("no_of_vehicles");
            }
            buildUi();
        } catch (Exception e){
            Log.e("MyLog","Exception in showJSON of Machineries Fragment");
        }

    }
}
