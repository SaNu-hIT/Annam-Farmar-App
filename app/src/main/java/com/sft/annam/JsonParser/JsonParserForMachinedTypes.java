package com.sft.annam.JsonParser;

import android.util.Log;

import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.Machine_types_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/23/2016.
 */
public class JsonParserForMachinedTypes {
    private String TAG = "JsonParsermachine_types";
    private static JsonParserForMachinedTypes jsonParsermachine;

    private JsonParserForMachinedTypes() {
        super();
    }

    public static JsonParserForMachinedTypes getInstance() {

        if (jsonParsermachine == null) {
            jsonParsermachine = new JsonParserForMachinedTypes();

        }
        return jsonParsermachine;
    }


    public boolean CheckmachinetypesResponse(String response) {

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

        //Log.e(TAG, "@Machineload");
        JSONObject jsonObj;
        String message = "";

        try {
            //Log.e(TAG, "@Machineload>");
            jsonObj = new JSONObject(response);
            //Log.e(TAG, "@Machineload>>");
            message = jsonObj.getString("errorCode");
            //Log.e(TAG, "@Machineload>>.");
            //Log.e(TAG,"errorCode = " + message);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }


    public String getErrorMessage(String response) {
        JSONObject jsonObj;

        String message = null;
        try {
            if (response!=null){
                jsonObj = new JSONObject(response);
                String status = "";
                status = jsonObj.getString("status");
                if (status.equals("0")) {

                    message = jsonObj.getString("errorCode");


                }}
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ArrayList<Machine_types_model> getMachineTypes (String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Machine_types_model> machineDetails = new ArrayList<Machine_types_model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("machine_type");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);


                        machineDetails.add(new Machine_types_model(
                                result.getString("machine_type_id"),
                                result.getString("machine_name"),
                                result.getString("pickup_required"),
                                result.getString("image"),""
//                                result.getString("quantity")
                        ));

                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getMchineDetails>>", ""+machineDetails);

        return machineDetails;

    }

    public ArrayList<Krishibhavan_Model> getkrishibhavandetails (String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Krishibhavan_Model> krishibhavanDetails = new ArrayList<Krishibhavan_Model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {
                    //TODO deleted krishibhavan item

//                    JSONArray resultArray = jsonObj.getJSONArray("krishibhavan");
//                    for (int i = 0; i < resultArray.length(); i++) {
//                        JSONObject result = resultArray.getJSONObject(i);
//
//
//                        krishibhavanDetails.add(new Krishibhavan_Model(result.getString("id")
//                                ,result.getString("name")));
//
//                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("gekrishichineDetails>>", ""+krishibhavanDetails);

        return krishibhavanDetails;

    }



}
