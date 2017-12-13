package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.sft.annam.Interfaces.OnHttpResponseForChangePassword;
import com.sft.annam.JsonParser.JsonParserSignup;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by JESNA on 8/4/2016.
 */
public class HttpRequestHelperForChangePassword {
    public void changepassword(final FragmentActivity activity, String customer_id,String oldpassword,String newpassword){
        RequestParams params = new RequestParams();
        params.put("customer_id",customer_id);
        params.put("old_password",oldpassword);
        params.put("new_password",newpassword);



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
                Log.e( "onSuccess: ",""+response );
                OnHttpResponseForChangePassword change_password = (OnHttpResponseForChangePassword) activity;
                change_password.onHttpSuccessfulForChangePassword(response,
                        JsonParserSignup.getInstance().ChecksignupResponse(response),
                        JsonParserSignup.getInstance().ParseResponseMessage(response),
                        JsonParserSignup.getInstance().ParseCustomerId(response));

            }




            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@forgot", "FAILED  >" + response);
                OnHttpResponseForChangePassword change_password = (OnHttpResponseForChangePassword) activity;
                change_password.onHttpFailedForChangePassword(error, response, false, JsonParserSignup.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_ChangePassword_URL);

    }
}
