package com.sft.annam.Connection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.sft.annam.Interfaces.OnHttpReponseForSbmit_LAterBooking;
import com.sft.annam.JsonParser.JsonParserForSubmitBooking;
import com.sft.annam.Model.Book_Later_Model;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * Created by JESNA on 7/25/2016.
 */
public class HttpRequestHelperForSubmitBooking_Later {
    public void submitbookinglater(final FragmentActivity activity, String farmer_id,Book_Later_Model book_later_model){
        RequestParams params= new RequestParams();
        params.put("farmer_id",farmer_id);
        params.put("farmer_land_loc",book_later_model.getFar_later_loc());
        params.put("latitude",book_later_model.getFar_later_lat());
        params.put("longitude",book_later_model.getFar_later_long());
        params.put("from_date",book_later_model.getFar_later_fromdate());
        params.put("to_date",book_later_model.getFar_later_todate());
        params.put("land_area",book_later_model.getFar_later_landarea());
        params.put("machinery_type",book_later_model.getFar_later_mac_type());
        params.put("krishibhavan",book_later_model.getFar_later_krishi());


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
                Log.d("response>>>>>>>>>>>>>>",""+response);
                OnHttpReponseForSbmit_LAterBooking submitBooking=(OnHttpReponseForSbmit_LAterBooking)activity;
                submitBooking.onHttpSuccessfulReponseForSbmit_LAterBooking(response,
                        JsonParserForSubmitBooking.getInstance().ChecksubmitresponseResponse(response),
                        JsonParserForSubmitBooking.getInstance().ParseResponseMessage(response),
                        JsonParserForSubmitBooking.getInstance().ParseBookingId(response) );
            }

            @Override
            public void onFailure(Throwable error,String responseBody) {
                String response=responseBody;
                OnHttpReponseForSbmit_LAterBooking submitBooking=(OnHttpReponseForSbmit_LAterBooking)activity;
                submitBooking.onHttpFailedReponseForSbmit_LAterBooking(error,response,false,
                        JsonParserForSubmitBooking.getInstance().getErrorMessage(response));

            }
        };
        HttpRequestHelper.post(activity, params, handler, HttpRequestHelper.AnnamFarer_SubmitBooking_LATER_URL);
    }
}
