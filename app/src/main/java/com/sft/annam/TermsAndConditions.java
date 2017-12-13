package com.sft.annam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sft.annam.Connection.HttpRequestHelper;
import com.sft.annam.Interfaces.OnHttpResponseForChangePassword;
import com.sft.annam.JsonParser.JsonParserSignup;
import com.sft.annam.Utilities.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by intellyelabs on 27/03/17.
 */

public class TermsAndConditions {


    private Activity mContext;

    SessionManager sessionManager;

    public TermsAndConditions(Activity context) {

        mContext = context;
    }
    public void Terms(String machine_id){
        RequestParams params = new RequestParams();
        params.put("machine_id",machine_id);





        AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
            private ProgressDialog progress;

            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(mContext, "Loading",
                        "Loading...", true, true,
                        new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                HttpRequestHelper.client.cancelRequests(mContext, true);

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
                Log.e("Responce",responseBody);
                try {
                    JSONObject respon= new JSONObject(responseBody);


                    String status=respon.getString("status");
                    String errorcode=respon.getString("errorcode");
                    if(status.equals("1")&&errorcode.equals("success"))
                    {
                        String message=respon.getString("terms");
                        show(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }






            }




            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@forgot", "FAILED  >" + response);

            }

        };
        HttpRequestHelper.post(mContext, params, handler, HttpRequestHelper.AnnamFarer_TermasAndConditions);

    }


    public void show(String messages) {



        // The eulaKey changes every time you increment the version number in
        // the AndroidManifest.xml




            // EULA title
            String title = mContext.getString(R.string.app_name);



            // EULA text
            String message = messages;

            // Disable orientation changes, to prevent parent activity
            // reinitialization
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.accept,
                            new Dialog.OnClickListener() {

                                @Override
                                public void onClick(
                                        DialogInterface dialogInterface, int i) {
                                    // Mark this version as read.



                                    // Close dialog
                                    dialogInterface.dismiss();

                                    // Enable orientation changes based on
                                    // device's sensor
                                    mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new Dialog.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // Close the activity as they have declined
                                    // the EULA



                                    dialog.cancel();

                                    mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                                }

                            });
            builder.create().show();

    }
}
