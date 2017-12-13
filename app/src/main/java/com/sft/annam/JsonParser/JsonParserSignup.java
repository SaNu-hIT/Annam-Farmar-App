package com.sft.annam.JsonParser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JESNA on 7/19/2016.
 */
public class JsonParserSignup {
    private String TAG = "JsonParsersignup";
    private static JsonParserSignup jsonParserSignup;

    private JsonParserSignup() {
        super();
    }

    public static JsonParserSignup getInstance() {

        if (jsonParserSignup == null) {
            jsonParserSignup = new JsonParserSignup();

        }
        return jsonParserSignup;
    }


    public boolean ChecksignupResponse(String response) {

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
        Log.e(TAG, "ParseResponseMessage: "+response );

        //Log.e(TAG, "@Signup");
        JSONObject jsonObj;
        String message = "";

        try {
            //Log.e(TAG, "@Signup>");
            jsonObj = new JSONObject(response);
            //Log.e(TAG, "@Signup>>");
            message = jsonObj.getString("errorCode");
            //Log.e(TAG, "@Signup>>.");
            //Log.e(TAG,"errorCode = " + message);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }


    public String ParseCustomerId(String response) {

        //Log.e(TAG, "@Signup");
        JSONObject jsonObj;
        String customer_id = "";

        try {
            jsonObj = new JSONObject(response);
            if(!jsonObj.getString("status").equals("0")) {

                customer_id = jsonObj.getString("id");
            }
            //Log.e(TAG,"id = " + customer_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return customer_id;
    }

}
