package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.sft.annam.Interfaces.OnHttpResponseForForgotPassword;
import com.sft.annam.JsonParser.JsonParserSignup;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by JESNA on 8/4/2016.
 */
public class HttpRequestHelperForgotPassword {
    public void forgotpassword(final FragmentActivity activity,String username){
        RequestParams params = new RequestParams();
        params.put("username",username);



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
                Log.e(" Forget Passwor",""+response );
                OnHttpResponseForForgotPassword forgot_password = (OnHttpResponseForForgotPassword) activity;
                forgot_password.onHttpSuccessfulForForgotPassword(response,
                        JsonParserSignup.getInstance().ChecksignupResponse(response),
                        JsonParserSignup.getInstance().ParseResponseMessage(response),
                        JsonParserSignup.getInstance().ParseCustomerId(response));

            }

            @Override
            public void onFailure(Throwable error, String responseBody) {
                String response = responseBody;
                Log.e("@forgot", "FAILED  >" + response);
                OnHttpResponseForForgotPassword forgot_password = (OnHttpResponseForForgotPassword) activity;
                forgot_password.onHttpFailedForForgotPassword(error, response, false, JsonParserSignup.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_ForgotPassword_URL);

    }
}
