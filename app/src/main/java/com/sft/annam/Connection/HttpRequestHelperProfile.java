package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.sft.annam.Interfaces.OnHttpResponseForProfile;
import com.sft.annam.JsonParser.JsonParserForProfile;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public class HttpRequestHelperProfile {


    public  static void  profileview(final FragmentActivity activity,String fetchid) {


        RequestParams params = new RequestParams();
        params.put("farmer_id",fetchid);


        AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
            private ProgressDialog progress;



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
                OnHttpResponseForProfile profile = (OnHttpResponseForProfile) activity;
                profile.onHttpSuccessfulResponseProfile(response,
                        JsonParserForProfile.getInstance().CheckprofileResponse(response),
                        JsonParserForProfile.getInstance().ParseResponseMessage(response),
                        JsonParserForProfile.getInstance().getProfileDetails(response));

            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@Profile", "FAILED  >" + response);
                OnHttpResponseForProfile profileview= (OnHttpResponseForProfile) activity;
                profileview.onHttpFailedResponseProfile(error, response, false,
                        JsonParserForProfile.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_ViewProfile);
        }
}

