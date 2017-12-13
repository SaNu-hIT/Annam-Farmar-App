package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.sft.annam.Interfaces.OnHttpResponseListenerForMachineTypes_booklater;
import com.sft.annam.JsonParser.JsonParserForMachinedTypes;
import com.sft.annam.JsonParser.JsonParserForMachinedTypes_laterbook;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JESNA on 8/1/2016.
 */
public class HttpRequestHelperForMachine_types_book_later {
    public void machine_types_booklater(final FragmentActivity activity){
        RequestParams params= new RequestParams();
        params.put("deviceid","21");

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
                String response=responseBody;
                Log.d("response",""+response);

                OnHttpResponseListenerForMachineTypes_booklater mchinetypes=(OnHttpResponseListenerForMachineTypes_booklater)activity;
                mchinetypes.onHttpSuccessfulResponseMachineTypes_booklater(response,
                        JsonParserForMachinedTypes_laterbook.getInstance().Checkmachinetypes_bookResponse(response),
                        JsonParserForMachinedTypes_laterbook.getInstance().ParseResponseMessage(response),

                        JsonParserForMachinedTypes_laterbook.getInstance().getMachineTypes_later(response),
                        JsonParserForMachinedTypes_laterbook.getInstance().getkrishibhavandetails(response));
            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response=responseBody;


                OnHttpResponseListenerForMachineTypes_booklater mchinetypes=(OnHttpResponseListenerForMachineTypes_booklater)activity;
                mchinetypes.onHttpFailedResponseMachineTypes_booklater(error,response,false,
                        JsonParserForMachinedTypes.getInstance().getErrorMessage(response));

            }
        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_Laterooking);
    }
}
