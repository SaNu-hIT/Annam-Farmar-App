package com.sft.annam.JsonParser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/*
* Created by JESNA on 7/25/2016.
 */
public class JsonParserLogin {

    private String TAG = "JsonParserLogin";
    private static JsonParserLogin jsonParser;

    private JsonParserLogin() {
        super();
    }

    public static JsonParserLogin getInstance() {

        if (jsonParser == null) {
            jsonParser = new JsonParserLogin();

        }
        return jsonParser;
    }
    public boolean CheckLoginResponse(String response) {

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

        //Log.e(TAG, "@Login");
        JSONObject jsonObj;
        String message = "";

        try {
            jsonObj = new JSONObject(response);
            message = jsonObj.getString("errorCode");
            //Log.e(TAG,"errorCode = " + message);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }
    public String getErrorMessage(String response) {
        JSONObject jsonObj;
        JSONObject user;
        String message = null;
        try {
            if (response!=null){
                jsonObj = new JSONObject(response);
                String status = "";
                status = jsonObj.getString("status");
                if (status.equals("0")) {

                    message = jsonObj.getString("message");


                }}
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
    public String ParseSessionId(String response) {


        //Log.e(TAG, "@Login");
        JSONObject jsonObj;
        String session_id = "";

        try {
            jsonObj = new JSONObject(response);

            if(!jsonObj.getString("status").equals("0")) {
                JSONObject jsonObj2 = jsonObj.getJSONObject("Customer Details");
                session_id = jsonObj2.getString("id");
            }

            //Log.e(TAG,"id = " + session_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return session_id;
    }
    public int ParseUserid(String response) {

        //Log.e(TAG, "@Login");
        JSONObject jsonObj;
        int uid = 0;

        try {
            jsonObj = new JSONObject(response);
            if(!jsonObj.getString("status").equals("0"))

            {
                JSONObject jsonObj2 = jsonObj.getJSONObject("Customer Details");
                //JSONObject jsonObj3 = jsonObj2.getJSONObject("customer_id");
                String user_id = jsonObj2.getString("customer_id");
                uid = Integer.parseInt(user_id);
            }

            //Log.e(TAG,"id = " + uid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.e(TAG,"id = " + uid);
        return uid;
    }
}
