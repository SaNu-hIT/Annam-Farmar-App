package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.sft.annam.Interfaces.OnHttpResponseListenerForUploadSignUp;
import com.sft.annam.JsonParser.JsonParserForsigupOnload;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JESNA on 7/15/2016.
 */
public class HttpRequestHelperForOnloadSignup {

    public void uploadsignup(final FragmentActivity activity,String deviceid){
        RequestParams params= new RequestParams();
        params.put("deviceid",deviceid);

        AsyncHttpResponseHandler handler=new AsyncHttpResponseHandler() {
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
                String response=new String(responseBody);
               Log.e("panchayath",""+response);

                OnHttpResponseListenerForUploadSignUp onloadSignp=(OnHttpResponseListenerForUploadSignUp)activity;
                onloadSignp.onHttpSuccessfulResponseOnloadSignup(response,
                        JsonParserForsigupOnload.getInstance().ChecksignResponse(response),
                        JsonParserForsigupOnload.getInstance().ParseResponseMessage(response),
                        JsonParserForsigupOnload.getInstance().getFarmerDetails(response),
                        JsonParserForsigupOnload.getInstance().getCropsDetails(response),
                        JsonParserForsigupOnload.getInstance().getPanchaythDetails(response));
            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response=responseBody;


                OnHttpResponseListenerForUploadSignUp onloadSignp=(OnHttpResponseListenerForUploadSignUp)activity;

                onloadSignp.onHttpFailedResponseForOnloadLogin(error,response,false,
                        JsonParserForsigupOnload.getInstance().getErrorMessage(response));

            }
        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_uploadsignup_URL);
    }
}
