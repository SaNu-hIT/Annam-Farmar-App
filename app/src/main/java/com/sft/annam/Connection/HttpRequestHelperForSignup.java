package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.sft.annam.Interfaces.OnHttpResponseForSignup;
import com.sft.annam.JsonParser.JsonParserSignup;
import com.sft.annam.Model.Signup_Model;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JESNA on 7/19/2016.
 */
public class HttpRequestHelperForSignup {
    public void signup(final FragmentActivity activity, Signup_Model signup_model,String device_id){
        RequestParams params = new RequestParams();
        params.put("name",signup_model.getSignup_name());
        params.put("address",signup_model.getSignup_address());
        params.put("phone",signup_model.getSignup_phone());
        params.put("email",signup_model.getSignup_email());
        params.put("password",signup_model.getSignup_password());
        params.put("panchayath",signup_model.getSignup_panchayath());
        params.put("crops",signup_model.getSignup_crops());
        params.put("farmertype",signup_model.getSignup_farmertype());
        params.put("deviceid",device_id);



        AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
            private ProgressDialog progress;

            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(activity, "Loading",
                        "Loading...", true, true,
                        new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                HttpRequestHelper.client.cancelRequests(activity, true);

                            }
                        });
            }

            @Override
            public void onFinish() {

                super.onFinish();

                if (progress != null) {
                    if (progress.isShowing()) {
                        progress.cancel();
                        progress.dismiss();
                    }
                }
            }

            @Override
            public void onSuccess(String responseBody) {

                String response = responseBody;
                OnHttpResponseForSignup signup = (OnHttpResponseForSignup) activity;
                signup.onHttpSuccessfulResponseSignup(response,
                        JsonParserSignup.getInstance().ChecksignupResponse(response),
                        JsonParserSignup.getInstance().ParseResponseMessage(response),
                        JsonParserSignup.getInstance().ParseCustomerId(response));

            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@Login", "FAILED  >" + response);
                OnHttpResponseForSignup login = (OnHttpResponseForSignup) activity;
                login.onHttpFailedResponseSignup(error, response, false, JsonParserSignup.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_SignUp_URL);

    }
}
