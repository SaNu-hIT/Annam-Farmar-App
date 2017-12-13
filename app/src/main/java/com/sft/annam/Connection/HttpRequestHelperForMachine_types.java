package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.sft.annam.Interfaces.OnHttpResponseListenerForMachineTypes;
import com.sft.annam.JsonParser.JsonParserForMachinedTypes;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JESNA on 7/23/2016.
 */
public class HttpRequestHelperForMachine_types {
    public void machine_types(final FragmentActivity activity){
        RequestParams params= new RequestParams();
        params.put("device_id","21");

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
                Log.e("response succssss",""+response);
                Toast.makeText(activity, "thiss" , Toast.LENGTH_LONG);
                OnHttpResponseListenerForMachineTypes mchinetypes=(OnHttpResponseListenerForMachineTypes)activity;
                mchinetypes.onHttpSuccessfulResponseMachineTypes(response,
                        JsonParserForMachinedTypes.getInstance().CheckmachinetypesResponse(response),
                        JsonParserForMachinedTypes.getInstance().ParseResponseMessage(response),

                        JsonParserForMachinedTypes.getInstance().getMachineTypes(response),
                        JsonParserForMachinedTypes.getInstance().getkrishibhavandetails(response) );
            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response=responseBody;


                OnHttpResponseListenerForMachineTypes mchinetypes=(OnHttpResponseListenerForMachineTypes)activity;
                mchinetypes.onHttpFailedResponseMachineTypes(error,response,false,
                        JsonParserForMachinedTypes.getInstance().getErrorMessage(response));

            }
        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_Machine_types);
    }
}
