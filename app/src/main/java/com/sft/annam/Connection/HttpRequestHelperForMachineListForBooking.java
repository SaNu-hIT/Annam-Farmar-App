package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.sft.annam.Interfaces.OnHttpResponseForMachineListBooking;
import com.sft.annam.JsonParser.JsonParserForMachineList;
import com.sft.annam.JsonParser.JsonParserForMachineView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by JESNA on 7/23/2016.
 */
public class HttpRequestHelperForMachineListForBooking {

    public void machinetypesforlistforBooking(final FragmentActivity activity, String farmerid, String machinery_type) {
        RequestParams params = new RequestParams();
        params.put("farmer_id",farmerid);
        params.put("machinery_type", machinery_type);
        Log.e( "mac",""+machinery_type );


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

                if (progress != null)
                {
                    if (progress.isShowing())
                    {
                        progress.cancel();
                        progress.dismiss();
                    }
                }
            }

            @Override
            public void onSuccess(String responseBody) {

            String response = responseBody;
            OnHttpResponseForMachineListBooking machineView = (OnHttpResponseForMachineListBooking) activity;
            machineView.onHttpSuccessfulResponseMachineListBooking(response,
                    JsonParserForMachineList.getInstance().CheckmachinelistResponse(response),
                    JsonParserForMachineList.getInstance().ParseResponseMessage(response),
                    JsonParserForMachineList.getInstance().getMachilistforbooking(activity,response));
         }

            @Override
            public void onFailure(Throwable error,String responseBody) {

//                String response = new String(responseBody);
//                Log.e("@Machine", "FAILED  >" + response);

                OnHttpResponseForMachineListBooking machineView = (OnHttpResponseForMachineListBooking) activity;
                machineView.onHttpFailedResponseMachineListBooking(error, responseBody, false, JsonParserForMachineView.getInstance().ParseResponseMessage(responseBody));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_Machineries_Booking);
    }
}
