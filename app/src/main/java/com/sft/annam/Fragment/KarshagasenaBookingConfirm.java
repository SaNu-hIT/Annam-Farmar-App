package com.sft.annam.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Interfaces.OnHttpReponseForKarshagasernaBooking;
import com.sft.annam.Model.FetchId;
import com.sft.annam.R;
import com.sft.annam.TextView_Lato;
import com.sft.annam.Utilities.Utilities;
import com.sft.annam.customfonts.MyTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class KarshagasenaBookingConfirm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
String onwerid,karshagsenaid,selecteddate,dayorhour,BookingHours,BookingMinutes,dayvalues,locations,number_people,Natureofjob,selecttime;

MyTextView location;
MyTextView natureofjob;
MyTextView numberofpeople;
MyTextView date;
MyTextView days;
MyTextView hour;
MyTextView minute;
    Button confirm;


    LinearLayout hourlaout,minute_layout,day_layout;
    private ProgressDialog loading;
    private String latitude,longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_karshagasena_booking_confirm,container,false);
        setListner(rootView);


        Bundle bundle = getArguments();

        Log.e( "onCreate: ",""+bundle.toString() );
        if (bundle != null) {
            onwerid = bundle.getString("onwerid");
            karshagsenaid = bundle.getString("karshagsenaid");
            selecteddate = bundle.getString("selecteddate");
            dayorhour = bundle.getString("dayorhour");
            BookingHours = bundle.getString("BookingHours");
            BookingMinutes = bundle.getString("BookingMinutes");

            dayvalues = bundle.getString("dayvalues");
            locations = bundle.getString("location");

            Natureofjob = bundle.getString("Natureofjob");
            number_people = bundle.getString("number_people");
            latitude = bundle.getString("latitude");
            longitude = bundle.getString("longitude");
            selecttime=bundle.getString("selecttime");

            location.setText(locations);

            date.setText(selecteddate +" "+selecttime);
            days.setText(dayvalues);
            hour.setText(BookingHours);
            minute.setText(BookingMinutes);
            natureofjob.setText(Natureofjob);
            numberofpeople.setText(number_people);
            if(BookingHours.equals(""))
            {


                hourlaout.setVisibility(View.GONE);
            }
if(BookingMinutes.equals(""))
            {
                minute_layout.setVisibility(View.GONE);
            }

if(dayvalues.equals(""))
            {
                day_layout.setVisibility(View.GONE);
            }



        }

return rootView;




    }



    private void setListner(View rootView) {
        location= (MyTextView) rootView.findViewById(R.id.location);
        natureofjob= (MyTextView) rootView.findViewById(R.id.natureofjob);
        numberofpeople= (MyTextView) rootView.findViewById(R.id.numberofpeople);
        date= (MyTextView) rootView.findViewById(R.id.date);
        days= (MyTextView) rootView.findViewById(R.id.days);
        hour= (MyTextView) rootView.findViewById(R.id.hour);
        minute= (MyTextView) rootView.findViewById(R.id.minute);
        confirm= (Button) rootView.findViewById(R.id.confirm_button);

        hourlaout= (LinearLayout) rootView.findViewById(R.id.hourlaout);
        minute_layout= (LinearLayout) rootView.findViewById(R.id.minute_layout);
        day_layout= (LinearLayout) rootView.findViewById(R.id.day_layout);

        confirm.setOnClickListener(new
                                           View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                                       book(onwerid, karshagsenaid, selecteddate, selecttime, dayorhour, BookingHours,
                            BookingMinutes, dayvalues,locations,latitude,longitude);

                                               }
                                           });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    private void book(String ownerid, String karshagasenaid, String date, String time, String dayorhour, String hourvalue, String minutevalue, String dayvalue,String location,String latitudes,String longitude) {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();


        String url = "http://annamagrotech.com/webservice/farmer/karshakasenabooking.php?farmer_id=" + fechid + "&date=" + date + "&day_or_hour=" + dayorhour + "&hourvalue=" + hourvalue + "&minutevalue=" + minutevalue + "&dayvalue=" + dayvalue + "&karshakasena_id=" + karshagasenaid + "&time=" + time + "&farmer_land_loc=" + location+"&latitude="+latitudes+"&longitude"+longitude;
        String fixurl=fixURL(url);
        Log.e( "fixurl: ",fixurl );
        StringRequest stringRequest = new StringRequest(fixurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();



                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



    private String fixURL(String tempURL) {
        URI uri = null;
        try {
            uri = new URI(tempURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tempURL = String.valueOf(uri);
        uri = null;
        try {
            uri = new URI(tempURL.replaceAll("@", "%40"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tempURL = String.valueOf(uri);
        uri = null;
        try {
            uri = new URI(tempURL.replaceAll(",", "%2C"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return String.valueOf(uri);
    }
    private void showJSON(String response) {
        loading.cancel();

        Log.e( "Karshgasena book: ",response );
        String data_avail="";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");

            if (data_avail.equals("Success")){

showDialoges(jsonObject.getString("errorCode").toString());
            } else {
                showDialogess(jsonObject.getString("errorCode").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDialoges(String errorCode)
    {
//        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Success")
//                .setContentText(errorCode)
//                .setConfirmText("OK")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();

        Toast.makeText(getActivity(), ""+errorCode, Toast.LENGTH_SHORT).show();

                        Fragment fragment = new BookNow_Fragment();

                        Utilities.getInstance(getActivity()).changeHomeFragment(
                                fragment, "BookNow_Confirm", getActivity());

//                    }
//                })
//                .show();
    }
    private void showDialogess(String errorCode) {
//        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("Failed")
//                .setContentText(errorCode)
//                .setConfirmText("OK")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();


        Toast.makeText(getActivity(), ""+errorCode, Toast.LENGTH_SHORT).show();

                        Fragment fragment = new BookNow_Fragment();

                        Utilities.getInstance(getActivity()).changeHomeFragment(
                                fragment, "BookNow_Confirm", getActivity());

//                    }
//                })
//                .show();
    }


}
