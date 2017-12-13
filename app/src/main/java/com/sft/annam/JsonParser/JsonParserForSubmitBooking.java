package com.sft.annam.JsonParser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JESNA on 7/25/2016.
 */
public class JsonParserForSubmitBooking {
    private String TAG = "JsonParserSubmitbooking";
    private static JsonParserForSubmitBooking jsonParsersubmitbooking;

    private JsonParserForSubmitBooking() {
        super();
    }

    public static JsonParserForSubmitBooking getInstance() {

        if (jsonParsersubmitbooking == null) {
            jsonParsersubmitbooking = new JsonParserForSubmitBooking();

        }
        return jsonParsersubmitbooking;
    }


    public boolean ChecksubmitresponseResponse(String response) {

        boolean success = true;

        JSONObject jsonObj;
        Log.e(TAG, "ChecksubmitresponseResponse: "+response );

        try {
            jsonObj = new JSONObject(response);
            String status = jsonObj.getString("status");
            String message = jsonObj.getString("errorCode");


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


    public String ParseBookingId(String response) {

        //Log.e(TAG, "@Signup");
        JSONObject jsonObj;
        String customer_id = "";

        try {
            jsonObj = new JSONObject(response);
            int id = jsonObj.getInt("Bookingid");
            customer_id = Integer.toString(id);
            //Log.e(TAG,"id = " + customer_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return customer_id;
    }

    public String getErrorMessage(String response) {
        JSONObject jsonObj;
        JSONObject user;
        String message = null;
        try {
            if (response != null) {
                Log.e(TAG, "getErrorMessage: "+response );
                jsonObj = new JSONObject(response);
                String status = "";
                status = jsonObj.getString("status");
                if (status.equals("0")) {

                    message = jsonObj.getString("message");


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
}
