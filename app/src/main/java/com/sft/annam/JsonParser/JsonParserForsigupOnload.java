package com.sft.annam.JsonParser;



import android.util.Log;


import com.sft.annam.Model.Crops_type_Model;
import com.sft.annam.Model.Farmer_type;
import com.sft.annam.Model.Panchayath_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/15/2016.
 */
public class JsonParserForsigupOnload {
    private String TAG = "JsonParsersignuponload";
    private static JsonParserForsigupOnload jsonParsersign;

    private JsonParserForsigupOnload() {
        super();
    }

    public static JsonParserForsigupOnload getInstance() {

        if (jsonParsersign == null) {
            jsonParsersign = new JsonParserForsigupOnload();

        }
        return jsonParsersign;
    }


    public boolean ChecksignResponse(String response) {

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

        //Log.e(TAG, "@SignupUpload");
        JSONObject jsonObj;
        String message = "";

        try {
            //Log.e(TAG, "@SignupUpload>");
            jsonObj = new JSONObject(response);
            //Log.e(TAG, "@SignupUpload>>");
            message = jsonObj.getString("errorcode");
            //Log.e(TAG, "@SignupUpload>>.");
            //Log.e(TAG,"errorcode = " + message);


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

                    message = jsonObj.getString("errorcode");


                }}
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ArrayList<Farmer_type> getFarmerDetails (String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Farmer_type> farmerDetails = new ArrayList<Farmer_type>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("farmer_type");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);


                        farmerDetails.add(new Farmer_type(result.getString("id"),
                                result.getString("name")));

                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getFarmerDetails>>", ""+farmerDetails);

        return farmerDetails;

    }


    public ArrayList<Crops_type_Model> getCropsDetails (String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Crops_type_Model> crops_details = new ArrayList<Crops_type_Model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("crops");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);


                        crops_details.add(new Crops_type_Model(result.getString("id"),
                                result.getString("name")));

                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getCropsDetails>>", ""+crops_details);

        return crops_details;

    }


    public ArrayList<Panchayath_Model> getPanchaythDetails (String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Panchayath_Model> panchayath_models = new ArrayList<Panchayath_Model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("panchayath");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);


                        panchayath_models.add(new Panchayath_Model(result.getString("id"),
                                result.getString("name")));

                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getPanchayathDetails>>", ""+panchayath_models);

        return panchayath_models;

    }



}
