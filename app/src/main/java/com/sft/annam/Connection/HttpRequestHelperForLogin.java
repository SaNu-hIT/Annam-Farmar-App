package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.sft.annam.Interfaces.OnHtttpResponseListenerForLogin;
import com.sft.annam.JsonParser.JsonParserLogin;
import com.sft.annam.Model.Login_Model;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 *Created by JESNA on 8/4/2016.
 */
public class HttpRequestHelperForLogin {

    public void login(final FragmentActivity activity, Login_Model login,String device_id){
        RequestParams params = new RequestParams();
        params.put("username",login.getUsername());
        params.put("password",login.getPassword());
        params.put("deviceid",device_id);

        Log.e("login device_id: ",""+device_id );


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

                String response = new String(responseBody);
                Log.e("Login", "onSuccess: "+response );
                OnHtttpResponseListenerForLogin login = (OnHtttpResponseListenerForLogin) activity;

                login.onHttpSuccessfulResponseLogin(response,
                        JsonParserLogin.getInstance().CheckLoginResponse(response),
                        JsonParserLogin.getInstance().ParseResponseMessage(response),
                        JsonParserLogin.getInstance().ParseSessionId(response),
                        JsonParserLogin.getInstance().ParseUserid(response));

            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@Login", "FAILED  >" + response);
                OnHtttpResponseListenerForLogin login = (OnHtttpResponseListenerForLogin) activity;
                login.onHttpFailedResponseLogin(error, response, false, JsonParserLogin.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_Login_URL);

    }
}
