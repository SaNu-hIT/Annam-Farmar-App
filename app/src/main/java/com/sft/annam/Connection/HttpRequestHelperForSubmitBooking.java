package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.sft.annam.Interfaces.OnHttpResponseForSubmitBooking;
import com.sft.annam.JsonParser.JsonParserForSubmitBooking;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sft.annam.Model.SubmitBookNow_Model;


/**
 * Created by JESNA on 7/25/2016.
 */
public class HttpRequestHelperForSubmitBooking {
    public void submitbooking(final FragmentActivity activity, SubmitBookNow_Model submitBookNow_model) {
        RequestParams params = new RequestParams();


        params.put("farmer_id", submitBookNow_model.getFar_id());
        //Log.e("MyLog","farmer_id : " + submitBookNow_model.getFar_id());
        params.put("machinery_id", submitBookNow_model.getFar_mac_id());
        //Log.e("MyLog","machinery_id : " + submitBookNow_model.getFar_mac_id());
        params.put("machinery_type_id", submitBookNow_model.getFar_mac_type_id());
        //Log.e("MyLog","machinery_type_id : " + submitBookNow_model.getFar_mac_type_id());
        params.put("pickup_or_delivery", submitBookNow_model.getFar_pickup());
        //Log.e("MyLog","pickup_or_delivery : " + submitBookNow_model.getFar_pickup());
        if (submitBookNow_model.getFar_total_amount() != null) {
            params.put("total_amount", submitBookNow_model.getFar_total_amount());
            //Log.e("MyLog","total_amount : " +submitBookNow_model.getFar_total_amount() );
        } else {
            params.put("total_amount", "0");
            //Log.e("MyLog","total_amount : " +"0" );
        }
        params.put("date", submitBookNow_model.getFar_date());
        //Log.e("MyLog","date : " + submitBookNow_model.getFar_date());
        params.put("time", submitBookNow_model.getFar_time());
        //Log.e("MyLog","time :" + submitBookNow_model.getFar_time());

        params.put("day_or_hour", submitBookNow_model.getDay_hr_value());
        //Log.e("MyLog","day_or_hour : " + submitBookNow_model.getDay_hr_value());
        if (submitBookNow_model.getFar_days() != null) {
            params.put("dayvalue", submitBookNow_model.getFar_days());
            //Log.e("MyLog","dayvalue : " + submitBookNow_model.getFar_days());
        } else {
            params.put("dayvalue", "0");
            //Log.e("MyLog","dayvalue : " + "0");
        }
        if (submitBookNow_model.getFar_hrs() != null) {
            params.put("hourvalue", submitBookNow_model.getFar_hrs());
            //Log.e("MyLog","hourvalue : " +submitBookNow_model.getFar_hrs() );
            params.put("minutevalue", submitBookNow_model.getFar_min());
            //Log.e("MyLog","minutevalue : " + submitBookNow_model.getFar_min());
        } else {
            params.put("hourvalue", "0");
            //Log.e("MyLog","hourvalue : " + "0");
            params.put("minutevalue", "0");
            //Log.e("MyLog","minutevalue : " + "0");
        }

        params.put("quantity", submitBookNow_model.getFar_quantity());
        //Log.e("MyLog","quantity : " + submitBookNow_model.getFar_quantity());
        if (submitBookNow_model.getFar_land_area() != null) {
            params.put("land_area", submitBookNow_model.getFar_land_area());
            //Log.e("MyLog","land_area : " + submitBookNow_model.getFar_land_area());
            params.put("land_dry", submitBookNow_model.getFar_dry());
            //Log.e("MyLog","land_dry : " + submitBookNow_model.getFar_dry());
            params.put("land_wet", submitBookNow_model.getFar_wet());
            //Log.e("MyLog","land_wet : " +submitBookNow_model.getFar_wet() );
            params.put("land_lumpy", submitBookNow_model.getFar_lumpy());
            //Log.e("MyLog","land_lumpy : " + submitBookNow_model.getFar_lumpy());
        } else {
            params.put("land_area", "0");
            //Log.e("MyLog","land_area : " + "0");
            params.put("land_dry", "null");
            //Log.e("MyLog","land_dry : " + "null");
            params.put("land_wet", "null");
            //Log.e("MyLog","land_wet : " +"null");
            params.put("land_lumpy", "null");
            //Log.e("MyLog","land_lumpy : " + "null");
        }


        if (submitBookNow_model.getFar_trees_climb() != null) {
            params.put("tress_climb", submitBookNow_model.getFar_trees_climb());
            //Log.e("MyLog","tress_climb : " + submitBookNow_model.getFar_trees_climb());
            params.put("tress_clean", submitBookNow_model.getFar_trees_clean());
            //Log.e("MyLog","tress_clean : " + submitBookNow_model.getFar_trees_clean());
        } else {
            params.put("tress_climb", "0");
            //Log.e("MyLog","tress_climb : " + "0");
            params.put("tress_clean", "0");
            //Log.e("MyLog","tress_clean : " + "0");
        }
        if (submitBookNow_model.getFar_copras() != null) {
            params.put("total_copras", submitBookNow_model.getFar_copras());
            //Log.e("MyLog","total_copras : " + submitBookNow_model.getFar_copras());
        } else {
            params.put("total_copras", "0");
            //Log.e("MyLog","total_copras : " + "0");
        }


        boolean  withfuel= Boolean.parseBoolean(submitBookNow_model.getFar_fuel());
        if(withfuel)
        {
            params.put("with_fuel","true");
            params.put("without_fuel","false");
        }
        else
        {

            params.put("with_fuel","false");
            params.put("without_fuel","true");
        }


        boolean  withdriver= Boolean.parseBoolean(submitBookNow_model.getFar_driver());
        if(withdriver)
        {
            params.put("with_driver","true");
            params.put("without_driver","false");
        }
        else
        {

            params.put("with_driver","false");
            params.put("without_driver","true");
        }




//        params.put("fuel", submitBookNow_model.getFar_fuel());
//        //Log.e("MyLog","fuel : " + submitBookNow_model.getFar_fuel());
//        params.put("driver", submitBookNow_model.getFar_driver());
        //Log.e("MyLog","driver : " +submitBookNow_model.getFar_driver() );
        params.put("farmer_land_loc", submitBookNow_model.getFar_location());
        //Log.e("MyLog","farmer_land_loc : " + submitBookNow_model.getFar_location());
        params.put("delivery_address", submitBookNow_model.getFar_location());
        //Log.e("MyLog","delivery_address : " + submitBookNow_model.getFar_location());
        params.put("latitude", submitBookNow_model.getFar_latitude());
        //Log.e("MyLog","latitude : " + submitBookNow_model.getFar_latitude());
        params.put("longitude", submitBookNow_model.getFar_longitude());
        //Log.e("MyLog","longitude : " + submitBookNow_model.getFar_longitude());

        params.put("booknow_or_later", "0");
        //Log.e("MyLog","booknow_or_later : 0");
        params.put("isadvpay", "67");
        //Log.e("MyLog","isadvpay : 67");
        Log.e("PARAMS>>>", params.toString());


        AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
            ProgressDialog progressDialog;


            @Override
            public void onStart() {
                progressDialog = ProgressDialog.show(activity, "Loading",
                        "Loading...", true, true,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                HttpRequestHelper.client.cancelRequests(activity, true);

                            }
                        });
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onSuccess(String responseBody) {
                String response = responseBody;
   //             Toast.makeText(activity,"resp"+responseBody,Toast.LENGTH_LONG).show();
                OnHttpResponseForSubmitBooking submitBooking = (OnHttpResponseForSubmitBooking) activity;
                submitBooking.onHttpSuccessfulResponseSubmitbooking(response,
                        JsonParserForSubmitBooking.getInstance().ChecksubmitresponseResponse(response),
                        JsonParserForSubmitBooking.getInstance().ParseResponseMessage(response),
                        JsonParserForSubmitBooking.getInstance().ParseBookingId(response));
            }

            @Override
            public void onFailure(Throwable error, String responseBody) {
                String response = responseBody;

   //             Toast.makeText(activity,"er"+error,Toast.LENGTH_LONG).show();
                OnHttpResponseForSubmitBooking submitBooking = (OnHttpResponseForSubmitBooking) activity;
                submitBooking.onHttpFailedResponseSubmitbooking(error, response, false,
                        JsonParserForSubmitBooking.getInstance().getErrorMessage(response));

            }
        };
   //    Toast.makeText(activity,"PARAMS>>>"+params.toString(),Toast.LENGTH_LONG);
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_SubmitBooking_URL);
    }
}
