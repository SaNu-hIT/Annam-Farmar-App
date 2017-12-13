package com.sft.annam.JsonParser;

import android.util.Log;

import com.sft.annam.Model.Machineries_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 20-Jul-16.
 */

public class JsonParserForMachineView {
    private static JsonParserForMachineView jsonParserForMachineView;
    private String TAG = "JsonParserForMachineView";

    private JsonParserForMachineView() {
        super();
    }

    public static JsonParserForMachineView getInstance() {

        if (jsonParserForMachineView == null) {
            jsonParserForMachineView = new JsonParserForMachineView();

        }
        return jsonParserForMachineView;
    }


    public boolean CheckmachineResponse(String response) {

        boolean success = false;

        JSONObject jsonObj;

        try {
            jsonObj = new JSONObject(response);
            String status = jsonObj.getString("status");


            if (status.equalsIgnoreCase("1"))
                success = true;
            else
                success = false;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return success;
    }

    public String ParseResponseMessage(String response) {

        //Log.e(TAG, "@MachineView");
        JSONObject jsonObj;
        String message = "";

        try {
            //Log.e(TAG, "@MachineView>");
            jsonObj = new JSONObject(response);
            //Log.e(TAG, "@MachineView>>");
            message = jsonObj.getString("errorCode");
            //Log.e(TAG, "@MachineView>.");
            //Log.e(TAG,"errorCode = " + message);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }


    public ArrayList<Machineries_Model> getMachineriesDetails(String response) {
        Log.e("getMachineriesDetails", response);
        JSONObject jsonObj;
        ArrayList<Machineries_Model> machineries_models = new ArrayList<Machineries_Model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("machinery_details");
                    int length = resultArray.length();
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);

                        Log.e("DATA IN SS", result.toString());
                        machineries_models.add(new Machineries_Model(result.getString("machine_id"),
                                result.getString("machine_type"), result.getString("machine_name"),
                                result.getString("rate_per_hour"), result.getString("rate_per_day"),
                                result.getString("description"),
                                result.getString("description"),
                                result.getString("image"), result.getString("rate_per_tree_climb"), result.getString("rate_per_tree_clean")
                                , result.getString("rate_per_coconut_kg"), result.getString("rate_per_pesticide_kg"), result.getString("specification1"), result.getString("specification2"), result.getString("specification3")));


                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getmachineriesDetails>>", ""+machineries_models);

        return machineries_models;

    }

}


