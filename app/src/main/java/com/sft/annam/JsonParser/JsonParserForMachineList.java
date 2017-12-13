package com.sft.annam.JsonParser;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;


import com.sft.annam.Model.MachineListModel_Booking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/23/2016.
 */
public class JsonParserForMachineList {
    private String TAG = "JsonParserForMachineList";
    private static JsonParserForMachineList jsonParserForMachineList;

    private JsonParserForMachineList() {
        super();
    }

    public static JsonParserForMachineList getInstance() {

        if (jsonParserForMachineList == null) {
            jsonParserForMachineList= new JsonParserForMachineList();

        }
        return jsonParserForMachineList;
    }


    public boolean CheckmachinelistResponse(String response) {

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


    public ArrayList<MachineListModel_Booking> getMachilistforbooking(Activity activity, String response) {
        JSONObject jsonObj;
        ArrayList<MachineListModel_Booking> machineries_models = new ArrayList<MachineListModel_Booking>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {
                    JSONArray resultArray = jsonObj.getJSONArray("machinery_details");
                    int length=resultArray.length();
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);

                        machineries_models.add(new MachineListModel_Booking(result.getString("machine_id"),
                               result.getString("machine_name"),  result.getString("machine_type"),
                                result.getString("rate per hour"),result.getString("pickup"),result.getString("machine_desc"),
                                result.getString("image"),
                                result.getString("rate per day"),result.getBoolean("day"),result.getBoolean("land_area"),result.getBoolean("no_of_trees_to_climb"),result.getBoolean("no_of_trees_to_clean"),result.getBoolean("with_fuel"),result.getBoolean("without_fuel"),result.getBoolean("with_driver"),result.getBoolean("without_driver"),result.getBoolean("coconut_in_kg"),result.getBoolean("pesticide_in_kg"),result.getBoolean("land_type"),result.getString("rate_per_tree_climb"),result.getString("rate_per_tree_clean"),result.getString("rate_per_coconut_kg"),result.getString("rate_per_pesticide_kg")));

                    }

                } else {
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(activity, "ecept0"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        //Log.e("getmachineriesDetails>>", ""+machineries_models);

        return machineries_models;

    }
}
