package com.sft.annam.Connection;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by JESNA on 7/14/2016.
 */
public class HttpRequestHelper {

    final static int DEFAULT_TIMEOUT = 30 * 1000;
    public static final String AnnamFarer_BaseUrl = "http://annamagrotech.com/webservice/farmer/";
    //public static final String AnnamFarer_BaseUrl = "http://192.168.1.13/debug/AnnamAgroTech/farmer/";
    public static final String AnnamFarer_Login_URL ="logincheck.php";
    public static final String  AnnamFarer_uploadsignup_URL ="signup_onload.php";
    public static final String  AnnamFarer_SignUp_URL ="signup.php";
    public static final String  AnnamFarer_ViewProfile = "viewcustomerdetails.php";
    public static final String  AnnamFarer_ViewMachineries = "viewmachinery_details.php";
    public static final String  AnnamFarer_SubmitBooking_URL = "submitbooking.php";
    public static final String  AnnamFarer_Machine_types = "getmachinerytype.php";
    public static final String  AnnamFarer_Machineries_Booking = "getmachinerydetails.php";
    public static final String  AnnamFarer_Laterooking = "krishibhavan_onload.php";
    public static final String  AnnamFarer_SubmitBooking_LATER_URL = "submitbooklater.php";
    public static final String  AnnamFarer_ForgotPassword_URL = "forgotpassword.php";
    public static final String  AnnamFarer_ChangePassword_URL = "changepassword.php";
    public static final String  AnnamFarer_TermasAndConditions = "terms.php";
    public static AsyncHttpClient client = new AsyncHttpClient();
    public static final String TAG="@HttpRequestHelper";
    public static void post(Context context, RequestParams params,
                             AsyncHttpResponseHandler responseHandler, String url) {
        client.setTimeout(DEFAULT_TIMEOUT);
        client.post(context, AnnamFarer_BaseUrl+url, params, responseHandler);

    }
}






