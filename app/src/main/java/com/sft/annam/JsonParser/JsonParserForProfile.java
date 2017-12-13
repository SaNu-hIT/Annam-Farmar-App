package com.sft.annam.JsonParser;

import android.util.Log;


import com.sft.annam.Model.Profile_View_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public class JsonParserForProfile {


    private String TAG = "JsonParserForProfile";
    private static JsonParserForProfile jsonParserForProfile;

    private JsonParserForProfile () {
        super();
    }


    public static JsonParserForProfile  getInstance() {
        if (jsonParserForProfile  == null) {
            jsonParserForProfile  = new JsonParserForProfile();

        }
        return  jsonParserForProfile;



    }

    public boolean CheckprofileResponse(String response) {

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

        //Log.e(TAG, "@Profile");
        JSONObject jsonObj;
        String message = "";

        try {
            //Log.e(TAG, "@Profile>");
            jsonObj = new JSONObject(response);
            //Log.e(TAG, "@Profile>>");
            message = jsonObj.getString("errorCode");
            //Log.e(TAG, "@Profile>>.");
            //Log.e(TAG,"errorCode = " + message);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }




    public ArrayList<Profile_View_Model> getProfileDetails(String response) {
        Log.d(TAG, response);
        JSONObject jsonObj;
        ArrayList<Profile_View_Model> profile_view_models = new ArrayList<Profile_View_Model>();
        if (response != null) {
            try {
                jsonObj = new JSONObject(response);
                String statusval = jsonObj.getString("status");

                if (statusval.equalsIgnoreCase("1")) {

                    JSONArray resultArray = jsonObj.getJSONArray("Details");
                int length=resultArray.length();
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject result = resultArray.getJSONObject(i);


                        profile_view_models.add(new Profile_View_Model(result.getString("farmer_id"),
                                result.getString("farmer_name"),result.getString("address"),
                                result.getString("phone"),result.getString("email"),
                                result.getString("panchyath_loc"),result.getString("farmer_type"),
                                result.getString("crop_name")));

                    }

                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.e("getprofileDetails>>", ""+profile_view_models);

        return profile_view_models;

    }

}
