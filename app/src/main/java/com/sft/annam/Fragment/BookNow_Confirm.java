package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sft.annam.AppEULA;
import com.sft.annam.Connection.HttpRequestHelper;
import com.sft.annam.Connection.HttpRequestHelperForSubmitBooking;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.DataBaseHelper.DatabaseHelperFor_Book;
import com.sft.annam.DataBaseHelper.DatabaseHelperFor_Book_Later;
import com.sft.annam.Model.BookNowFrag_model;
import com.sft.annam.Model.Book_Confirm_Constant;
import com.sft.annam.Model.Book_Later_Const;
import com.sft.annam.Model.Book_Later_Model;
import com.sft.annam.Model.Const_Model;
import com.sft.annam.Model.Constants;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.SubmitBookNow_Model;
import com.sft.annam.R;
import com.sft.annam.TermsAndConditions;
import com.sft.annam.TextView_Lato;
import com.sft.annam.Utilities.SessionManager;
import com.sft.annam.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by JESNA on 7/13/2016.
 */
public class BookNow_Confirm extends Fragment {
    Button btn_booknow_confirm;
    TextView txtrate_day, machine_type, txt_tnc;
    TextView_Lato conf_machine_name;
    LinearLayout lyLand, lyTrees, lyCopra,summary_booking_period_layout,pick_up_layout,booiking_date,land_condition_linear,summary_rate_day_new_layout;
    TextView tvLandArea, tvLandCondition, tvTreesClimb, tvTreesClean, tvTotalCopra, tvPickup, tvFuel, tvDriver;
    BookNowFrag_model bookNowFrag_model;
    ImageView booknonow_image;
    private ProgressDialog loading;
    TextView txLocation, txBookingDate, txBookingPeriod;
    String strBookOrPlan;
    String strMachineName;

    String strMachineId;
    String strMachineTypeName;
    String strMachineTypeId;
    String strPickupRequired;
    String strRatePerHour;
    String strTotalAmount;
    String strMachineImageURL;
    CheckBox termsandconditions;
    TextView_Lato Tterm1,Tterm2,Tterm3,Tterm4;
    LinearLayout booiking_time;
    TextView arrival_time,booking_date;
    double extimate_rate;
    LinearLayout clean_layout,cliemb_layout,land_area_layout;
    String strBookingDate=null, strBookingTime="", strDayOrHour="", strDays, strHours="", strMinutes="", strQuantity, strTotalLandArea = null, strTreesToClimb = null, strTreesToClean = null, strTotalCopra = null;
    boolean bDry = false, bWet = false, bLumpy = false, bWithFuel=false, bWithDriver=false;
    String strDry = "false", strWet = "false", strLumpy = "false", strFuel = "false", strDriver = "false";
    SessionManager sessionManager;
    private String ratePerDay="";
    private String rateper_clean="";
    private String rate_per_climb="";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout. z, container, false);
        fechdetails();
         strBookingDate=""; strBookingTime=""; strDayOrHour=""; strDays="";strTotalAmount="";

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());



//        bDry = false, bWet = false, bLumpy = false, bWithFuel, bWithDriver;
        Bundle bundle = getArguments();
        if (bundle != null) {
            strBookOrPlan = bundle.getString("BookOrPlan");
            strMachineName = bundle.getString("MachineName");
            strMachineId = bundle.getString("MachineTypeId");
            strMachineTypeName = bundle.getString("MachineTypeName");
            strMachineTypeId = bundle.getString("MachineId");
            strMachineImageURL = bundle.getString("MachineImageURL");
            Log.e( "strMachineImageURL: ",strMachineImageURL );
            strPickupRequired = bundle.getString("PickupRequired");
            strRatePerHour= bundle.getString("RatePerHour");
            strTotalAmount = bundle.getString("TotalAmount");
            strBookingDate = bundle.getString("BookingDate");
            strBookingTime = bundle.getString("BookingTime");
            strDayOrHour = bundle.getString("DayOrHour");
            ratePerDay= bundle.getString("RatePerDay");
            rateper_clean= bundle.getString("RatePerClean");



            rate_per_climb = bundle.getString("RarePerClimb");

            Log.e("strTotalAmount: ",""+strTotalAmount );


            Log.e("Rate per day", "onCreateView: "+ratePerDay );

sessionManager=new SessionManager(getContext());



                strDays = bundle.getString("Days");

                strHours = bundle.getString("Hours");
                strMinutes = bundle.getString("Minutes");

            strQuantity = bundle.getString("Quantity");

                    strTotalLandArea = bundle.getString("TotalLandArea");


                    bDry = bundle.getBoolean("Dry");
                    bWet = bundle.getBoolean("Wet");
                    bLumpy = bundle.getBoolean("Lumpy");

                    strTreesToClimb = bundle.getString("TreesToClimb");
                    strTreesToClean = bundle.getString("TreesToClean");




                    strTotalCopra = bundle.getString("TotalCopra");

            bWithFuel = bundle.getBoolean("WithFuel");
            bWithDriver = bundle.getBoolean("WithDriver");

            Log.e( "strTreesToClimb: ", ""+strTreesToClimb);
            Log.e( "strTreesToClean: ", ""+strTreesToClean);


        }

        initViews(rootView);

        booking_date.setText(formattedDate);




        setBundleDataOnViews();

Terms(getActivity(),strMachineId);

        btn_booknow_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
boolean tick=termsandconditions.isChecked();


                if(tick) {

                    FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
                    String fechid = fetchId.getUser_id();

                    //bookNowFrag_model=DatabaseHelperFor_Book.getProfileOffline(getActivity());

                    if (Utilities.getInstance(getActivity()).isNetAvailable()) {
                        Book_Confirm_Constant.machineName = strMachineName;
                        Book_Confirm_Constant.machineID = strMachineId;
                        Book_Confirm_Constant.machineTypeID = strMachineTypeId;
                        Book_Confirm_Constant.machineTypeName = strMachineTypeName;
                        Book_Confirm_Constant.machineImageURL = strMachineImageURL;


                        Constants.setSelectedDate(strBookingDate);
                        Constants.setSelectedTime(strBookingTime);

                        if (strBookOrPlan.equals("PLAN")) {


//                        setBookLater();
//modified by saneesh NOTE: booking
                            Location_MOdel location_mOdel = DataBaseHelperFor_location.getProfileOffline(getActivity());
                            String place = location_mOdel.getPlace();
                            String state = location_mOdel.getState();
                            String contry = location_mOdel.getCountry();
                            String location = place + "," + state + "," + contry;
                            SubmitBookNow_Model submitBookNow_model = new SubmitBookNow_Model(fechid,
                                    strMachineId, strMachineTypeId, strPickupRequired, strTotalAmount,
                                    strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes,
                                    strQuantity, strTotalLandArea, strDry, strWet, strLumpy, strTreesToClimb,
                                    strTreesToClean, strTotalCopra, strFuel, strDriver, location, location_mOdel.getLatitude(), location_mOdel.getLongitude());

                            HttpRequestHelperForSubmitBooking submitBooking = new HttpRequestHelperForSubmitBooking();
                            submitBooking.submitbooking(getActivity(), submitBookNow_model);


//
//                            Location_MOdel location_mOdel = DataBaseHelperFor_location.getProfileOffline(getActivity());
//                            String place = location_mOdel.getPlace();
//                            String state = location_mOdel.getState();
//                            String contry = location_mOdel.getCountry();
//                            String location = place + "," + state + "," + contry;
//                            SubmitBookNow_Model submitBookNow_model = new SubmitBookNow_Model(fechid,
//                                    strMachineId, strMachineTypeId, strPickupRequired, strTotalAmount,
//                                    strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes,
//                                    strQuantity, strTotalLandArea, strDry, strWet, strLumpy, strTreesToClimb,
//                                    strTreesToClean, strTotalCopra, strFuel, strDriver, location, location_mOdel.getLatitude(), location_mOdel.getLongitude());
//
//                            HttpRequestHelperForSubmitBooking submitBooking = new HttpRequestHelperForSubmitBooking();
//                            Log.e("submitBookNow_model",submitBookNow_model.toString());
//                            submitBooking.submitbooking(getActivity(), submitBookNow_model);
                        } else {


                            Location_MOdel location_mOdel = DataBaseHelperFor_location.getProfileOffline(getActivity());

if(location_mOdel!=null) {


    String place = location_mOdel.getPlace();
    String state = location_mOdel.getState();
    String contry = location_mOdel.getCountry();
    String location = place + "," + state + "," + contry;
    Book_Confirm_Constant.location = location;
    SubmitBookNow_Model submitBookNow_model = new SubmitBookNow_Model(fechid,
            strMachineId, strMachineTypeId, strPickupRequired, strTotalAmount,
            strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes,
            strQuantity, strTotalLandArea, strDry, strWet, strLumpy, strTreesToClimb,
            strTreesToClean, strTotalCopra, strFuel, strDriver, location, location_mOdel.getLatitude(), location_mOdel.getLongitude());

    HttpRequestHelperForSubmitBooking submitBooking = new HttpRequestHelperForSubmitBooking();
    submitBooking.submitbooking(getActivity(), submitBookNow_model);
}
else {

    Snackbar snackbar = Snackbar
            .make(getView(), "Please select your location", Snackbar.LENGTH_LONG)
            .setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e( "onClick: ",
                            "Farmar location");
                }
            });

    snackbar.show();

}
                        }
                    } else {
                        Toast.makeText(getActivity(), "network is not available", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Pleace accept the terms and condition", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AppEULA(getActivity()).show();

//                Log.e("onClick: ",strMachineId );

//                new TermsAndConditions(getActivity()).Terms(strMachineId);
            }
        });


        return rootView;
    }

    private void initViews(View rootView) {
        conf_machine_name = (TextView_Lato) rootView.findViewById(R.id.summary_machine_name);
        machine_type = (TextView) rootView.findViewById(R.id.summary_machine_type);
        txtrate_day = (TextView) rootView.findViewById(R.id.summary_rate_day_new);
        booknonow_image = (ImageView) rootView.findViewById(R.id.summary_image_view);
        //txtTotAmount=(TextView) rootView.findViewById(R.id.txtTotAmount);

        btn_booknow_confirm = (Button) rootView.findViewById(R.id.summary_button);

        txLocation = (TextView) rootView.findViewById(R.id.summary_location);
        txBookingDate = (TextView) rootView.findViewById(R.id.summary_booking_date);
        txBookingPeriod = (TextView) rootView.findViewById(R.id.summary_booking_period);

        txt_tnc = (TextView) rootView.findViewById(R.id.tnc);
        booiking_time= (LinearLayout) rootView.findViewById(R.id.booiking_time);
        summary_rate_day_new_layout= (LinearLayout) rootView.findViewById(R.id.summary_rate_day_new_layout);

        lyLand = (LinearLayout) rootView.findViewById(R.id.land_Layout);
        lyTrees = (LinearLayout) rootView.findViewById(R.id.tree_Layout);
        lyCopra = (LinearLayout) rootView.findViewById(R.id.kg_layout);
        tvLandArea = (TextView) rootView.findViewById(R.id.summary_land_area);
        tvLandCondition = (TextView) rootView.findViewById(R.id.summary_land_condition);
        tvTreesClean = (TextView) rootView.findViewById(R.id.summary_tree_clean);
        tvTreesClimb = (TextView) rootView.findViewById(R.id.summary_tree_climb);
        tvTotalCopra = (TextView) rootView.findViewById(R.id.summary_kg);
        tvPickup = (TextView) rootView.findViewById(R.id.pickup_status);
        tvFuel = (TextView) rootView.findViewById(R.id.fuel_status);
        booking_date = (TextView) rootView.findViewById(R.id.booking_date);
        summary_booking_period_layout= (LinearLayout) rootView.findViewById(R.id.summary_booking_period_layout);
        tvDriver = (TextView) rootView.findViewById(R.id.driver_status);
        pick_up_layout= (LinearLayout) rootView.findViewById(R.id.pick_up_layout);
        arrival_time= (TextView) rootView.findViewById(R.id.arrival_time);
        booiking_date= (LinearLayout) rootView.findViewById(R.id.booiking_date);
        clean_layout= (LinearLayout) rootView.findViewById(R.id.tree_clean_layout);
        cliemb_layout= (LinearLayout) rootView.findViewById(R.id.tree_climb_layout);
        land_condition_linear= (LinearLayout) rootView.findViewById(R.id.land_condition_linear);
        land_area_layout=(LinearLayout) rootView.findViewById(R.id.land_area_layout);
        termsandconditions= (CheckBox) rootView.findViewById(R.id.termsandconditions);
        Tterm1= (TextView_Lato) rootView.findViewById(R.id.term1);
        Tterm2= (TextView_Lato) rootView.findViewById(R.id.term2);
        Tterm3= (TextView_Lato) rootView.findViewById(R.id.term3);
        Tterm4= (TextView_Lato) rootView.findViewById(R.id.term4);

    }

    private void setBundleDataOnViews() {





        URI uri = null;
        try {
            uri = new URI(strMachineImageURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String imageURL = String.valueOf(uri);
        Picasso.with(getActivity())
                .load(imageURL)
                .placeholder(R.drawable.tillerharvester)   // optional
                .error(R.drawable.tillerharvester)      // optional
                .resize(400, 400)                        // optional
                .into(booknonow_image);
        conf_machine_name.setText(strMachineName);
        machine_type.setText(strMachineTypeName);
        txLocation.setText(Book_Confirm_Constant.location);



        if(strTotalAmount.equals("0"))
        {
            summary_rate_day_new_layout.setVisibility(View.GONE);
        }
        if(strBookingDate.equals("")) {
            booiking_date.setVisibility(LinearLayout.GONE);
            booiking_time.setVisibility(LinearLayout.GONE);
        }else {


            txBookingDate.setText(strBookingDate);
            arrival_time.setText(strBookingTime);
        }


                String days = strDays;
                if(!days.equals("")) {

                    summary_booking_period_layout.setVisibility(View.VISIBLE);

                    txBookingPeriod.setText(days+"Days");
                    if(!ratePerDay.equals(""))
                    {
                        String rate_in_str=ratePerDay.substring(1, ratePerDay.indexOf(" "));
                        double rate= Double.parseDouble(rate_in_str);
                        double day= Double.parseDouble(days);
                        extimate_rate = day* rate;
                        Log.e("extimate_rate: ",""+extimate_rate);
                        String estrate= "₹"+String.valueOf(extimate_rate);

                        if (extimate_rate==0) {

                            txtrate_day.setVisibility(View.GONE);
//                            txtrate_day.setVisibility(View.VISIBLE);
//                            txtrate_day.setText(estrate);

                        }
                        else
                        {
                            summary_rate_day_new_layout.setVisibility(View.VISIBLE);
                            txtrate_day.setVisibility(View.VISIBLE);
                            txtrate_day.setText(estrate);


                        }

                    }


                }
                else
                {
                    summary_booking_period_layout.setVisibility(View.GONE);
                }

                if(strHours.equals("")|| strMinutes.equals(""))
                {
                    if(days.equals("")) {
                        summary_booking_period_layout.setVisibility(View.GONE);

txBookingPeriod.setVisibility(View.GONE);



                    }

                }
                else {


                    String hoursNMints = strHours + " Hours " + strMinutes + " Minutes";
                    txBookingPeriod.setText(hoursNMints);
                    int rate_per_hour= Integer.parseInt(strRatePerHour);
                    int hours= Integer.parseInt(strHours);
                    int minitue= Integer.parseInt(strMinutes);
                    int houramount=rate_per_hour*hours;
                    int  one_minituewamount=rate_per_hour/60;
                    int minitueamount=one_minituewamount*minitue;
                    int total_amount=houramount+minitueamount;
                    String totalamountin_pre= "₹"+String.valueOf(total_amount);

                    if (total_amount==0) {

                        txtrate_day.setVisibility(View.GONE);
//                            txtrate_day.setVisibility(View.VISIBLE);
//                            txtrate_day.setText(estrate);

                    }
                    else
                    {
//                        summary_booking_period_layout.setVisibility(View.VISIBLE);
                        txtrate_day.setVisibility(View.VISIBLE);
                        txtrate_day.setText(totalamountin_pre);


                    }





                }

                if (!strTotalLandArea.equals("")) {

                    tvLandArea.setText(strTotalLandArea);
                }
                else
                {
                    land_area_layout.setVisibility(View.GONE);

                }




        if (!strTreesToClimb.equals("")) {

            summary_booking_period_layout.setVisibility(View.GONE);

            cliemb_layout.setVisibility(View.VISIBLE);
            tvTreesClimb.setText(strTreesToClimb);
            Log.e("ratePerDay in cocunut: ",rateper_clean);
//            String rate_in_str=ratePerDay.substring(1, ratePerDay.indexOf("\n"));
            if(rate_per_climb.equals(""))
            {
                rate_per_climb="0";
            }
            int ratepercliemb= Integer.parseInt(rate_per_climb);
            int trees= Integer.parseInt(strTreesToClimb);
int total_amounts=ratepercliemb*trees;

            String totalamount="₹"+String.valueOf(total_amounts);
            if (total_amounts==0) {

                txtrate_day.setVisibility(View.GONE);
//                            txtrate_day.setVisibility(View.VISIBLE);
//                            txtrate_day.setText(estrate);

            }
            else
            {
//                summary_booking_period_layout.setVisibility(View.VISIBLE);
                txtrate_day.setVisibility(View.VISIBLE);
                txtrate_day.setText(strTotalAmount);


            }







        }
        else
        {
            cliemb_layout.setVisibility(View.GONE);

        }



        if (!strTreesToClean.equals("")) {
            summary_booking_period_layout.setVisibility(View.GONE);
            clean_layout.setVisibility(View.VISIBLE);



            tvTreesClean.setText(strTreesToClean);
            Log.e("ratePeeCliemb  ", rateper_clean);
//            String rate_in_str=strRatePerHour.substring(1, strRatePerHour.indexOf(" "));
if(rateper_clean.equals(""))
{
    rateper_clean="0";
}
            int ratepercliemb= Integer.parseInt(rateper_clean);
            int trees= Integer.parseInt(strTreesToClean);
            int total_amounts=ratepercliemb*trees;

            String totalamount="₹"+String.valueOf(total_amounts);
            if (total_amounts==0) {

                txtrate_day.setVisibility(View.GONE);


            }
            else
            {
                summary_booking_period_layout.setVisibility(View.VISIBLE);
                txtrate_day.setVisibility(View.VISIBLE);
                txtrate_day.setText(strTotalAmount);


            }



        }
        else
        {

            clean_layout.setVisibility(View.GONE);

        }


        if (!strTotalCopra.equals("")) {


            tvTotalCopra.setText(strTotalCopra);


            summary_booking_period_layout.setVisibility(View.GONE);

            lyCopra.setVisibility(View.VISIBLE);
        }
        else
        {

            lyCopra.setVisibility(View.GONE);

        }




//
//        if (strTotalAmount!=null) {
//
//
//            txtrate_day.setText(strTotalAmount);
//        }
//        else
//        {
//            txtrate_day.setVisibility(View.GONE);
//
//        }



if(bWithFuel) {
            tvFuel.setText("With Fuel");
    tvFuel.setVisibility(View.VISIBLE);
}
else
{
    tvFuel.setText("Without Fuel");

}

if(bWithDriver)
{
    tvDriver.setText("With Driver");
    tvDriver.setVisibility(View.VISIBLE);
}
else
{
    tvDriver.setText("With out Driver");
}
if(!bWithFuel&&!bWithDriver){
    pick_up_layout.setVisibility(View.GONE);


}
//
//            tvFuel.setText("Without Fuel");
//
//            tvDriver.setText("With Driver");
//
//
//
//            tvDriver.setText("Without Driver");



        if(bDry)
        {
            tvLandCondition.setText("Dry");
            strDry="true";



        }
if(bWet)
{
    tvLandCondition.setText("Wet");
    strWet="true";
}

    if(bLumpy)
    {
        tvLandCondition.setText("Lumpy");
        strLumpy="true";

    }
 if(!bDry&&!bWet&&!bLumpy)
 {
     land_condition_linear.setVisibility(View.GONE);

 }
 String ss=txBookingPeriod.getText().toString();
        Log.e("txBookingPeriod", ss );
 if(ss.equals(""))
 {
     summary_booking_period_layout.setVisibility(View.GONE);

 }


    }

    private void setBookLater() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();


        Book_Later_Model model = Book_Later_Const.getConsBookLaterModel();
//        Book_Later_Model model = new Book_Later_Model();
        String loc = model.getFar_later_loc();
        String fromDate = model.getFar_later_fromdate();
        String toDate = model.getFar_later_todate();
        String landArea = model.getFar_later_landarea();
        String krishi = model.getFar_later_krishi();


//if(!fechid.equals("")&&!loc.equals("")&&!fromDate.equals("")&&!toDate.equals("")&&!landArea.equals("")&&!krishi.equals("")) {

        String tempURL = "http://annamagrotech.com/webservice/farmer/submitbooklater.php?" +
                "farmer_id=" + fechid +
                "&farmer_land_loc=" + loc +
                "&from_date=" + fromDate +
                "&to_date=" + toDate +
                "&land_area=" + landArea +
                "&machinery_type=" + strMachineId +
                "&krishibhavan=" + krishi;

        Log.e("URL", tempURL);
        String url = fixURL(tempURL);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
//}
//        else
//{
//    Toast.makeText(getContext(),"Error in getting location", Toast.LENGTH_SHORT).show();
//}
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
        String data_avail = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")) {
                Toast.makeText(getContext(), "Booking Successful", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), "New Booking ID : " + jsonObject.getString("Bookingid"), Toast.LENGTH_SHORT).show();
                int id = jsonObject.getInt("Bookingid");
                Bundle bundle = new Bundle();
                bundle.putString("booking_id", Integer.toString(id));
                Fragment fragment = new BookingLater_Confirm_fragment();
                fragment.setArguments(bundle);
                Utilities.getInstance(getActivity()).changeHomeFragment(
                        fragment, "BookingLater_Confirm_fragment", getActivity());
            } else {
                Toast.makeText(getContext(), "Error in Connection", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fechdetails() {
        bookNowFrag_model = DatabaseHelperFor_Book.getProfileOffline(getActivity());
    }


    public void Terms(final Context mContext,String machine_id){
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
//                        JSONObject message=respon.getString("terms");
                        JSONArray array=respon.getJSONArray("terms");
                        if (array.length()!=0)
                        {
                            JSONObject terms=array.getJSONObject(0);
                            String  terms1=terms.getString("term1");
                            String  terms2=terms.getString("term2");
                            String  terms3=terms.getString("term3");
                            String  terms4=terms.getString("term4");
                            setTerms(terms1,terms2,terms3,terms4);


                        }

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

    private void setTerms(String terms1, String terms2, String terms3, String terms4) {




if(!terms1.equals(""))
{
    Tterm1.setVisibility(View.VISIBLE);
    Tterm1.setText("1."+terms1);
}
else
{
    Tterm1.setVisibility(View.GONE);

}
        if(!terms2.equals(""))
        {
            Tterm2.setVisibility(View.VISIBLE);
            Tterm2.setText("2."+terms2);

        }
        else
        {
            Tterm2.setVisibility(View.GONE);

        }
        if(!terms3.equals(""))
        {
            Tterm3.setVisibility(View.VISIBLE);
            Tterm3.setText("3."+terms3);

        }
        else
        {
            Tterm3.setVisibility(View.GONE);

        }
        if(!terms4.equals(""))
        {
            Tterm4.setVisibility(View.VISIBLE);
            Tterm4.setText("4."+terms4);

        }
        else
        {
            Tterm4.setVisibility(View.GONE);

        }


    }


}
