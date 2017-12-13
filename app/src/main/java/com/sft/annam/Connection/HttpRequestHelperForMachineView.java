package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.sft.annam.Interfaces.OnHttpResponseForMachineView;
import com.sft.annam.JsonParser.JsonParserForMachineView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public class HttpRequestHelperForMachineView {

    public void machineView(final FragmentActivity activity,String farmerid) {
        RequestParams params = new RequestParams();
        params.put("farmer_id", farmerid);


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
                Log.e("url",responseBody);


                String response = responseBody;
                OnHttpResponseForMachineView machineView = (OnHttpResponseForMachineView) activity;
                machineView.onHttpSuccessfulResponseMachineView(response,
                        JsonParserForMachineView.getInstance().CheckmachineResponse(response),
                        JsonParserForMachineView.getInstance().ParseResponseMessage(response),
                        JsonParserForMachineView.getInstance().getMachineriesDetails(response));

            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response = responseBody;
                Log.e("@Machine", "FAILED  >" + response);
                OnHttpResponseForMachineView machineView= (OnHttpResponseForMachineView) activity;
                machineView.onHttpFailedResponseMachineView(error, response, false, JsonParserForMachineView.getInstance().ParseResponseMessage(response));

            }

        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_ViewMachineries);
    }
}
