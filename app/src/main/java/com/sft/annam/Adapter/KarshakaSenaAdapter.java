package com.sft.annam.Adapter;

/**
 * Created by SFT on 15/2/2017.
 */
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Fragment.BookNow_Confirm;
import com.sft.annam.Fragment.KarshagasenaBookingConfirm;
import com.sft.annam.Fragment.Location_MOdel;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.KarshakaItem_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.BookKarshagasena;
import com.sft.annam.Utilities.Utilities;
import com.sft.annam.customfonts.MyTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anjush on 14/2/2017.
 */

public class KarshakaSenaAdapter extends RecyclerView.Adapter<KarshakaSenaAdapter.MyViewholder> {

    private List<KarshakaItem_Model> karshakaItemList;
    FragmentActivity context;
    FragmentManager fragmentManager;
    private Location_MOdel location_mOdel;

    public KarshakaSenaAdapter(FragmentActivity context, List<KarshakaItem_Model> karshakaItemList, FragmentManager fragmentManager) {
        this.context = context;
        this.karshakaItemList = karshakaItemList;
        this.fragmentManager=fragmentManager;
    }


    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.karshakasena_card, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewholder holder, final int position) {
        setRadioGroup(holder);
        KarshakaItem_Model karshakaItem = karshakaItemList.get(position);
        holder.title.setText(karshakaItem.getTitle());
        holder.location.setText(karshakaItem.getLocation());
        holder.numbFppl.setText(karshakaItem.getNumbFppl());
        holder.desc.setText(karshakaItem.getDesc());


        holder.tvBookingDateNTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBookingDateNTime(holder);
            }
        });

        holder.openbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.book.setVisibility(View.VISIBLE);
                holder.openbooking.setVisibility(View.GONE);
                holder.visible_layout.setVisibility(View.VISIBLE);
            }
        });

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"working "+position ,Toast.LENGTH_SHORT).show();

                KarshakaItem_Model karshakaItem = karshakaItemList.get(position);
                FetchId fetchId = Utilities.getInstance(context).fetchId();
                final String fechid = fetchId.getUser_id();

                String onwerid=karshakaItem.getOwnerid();
                String karshagsenaid=karshakaItem.getKarshakasena_id();

                String etBookingHours = holder.mlfb_EditTextHours.getText().toString();

                String etBookingMinutes = holder.mlfb_EditTextHours.getText().toString();
                String dayvalues = holder.mlfb_EditTextDays.getText().toString();
                String selecteddate=holder.selectedDate;
                String selecttime=holder.selectedTime;
                String location=karshakaItem.getLocation();





                if (!validate(holder)) {


                    location_mOdel = DataBaseHelperFor_location.getProfileOffline(context);
                    String loc=location_mOdel.getPlace();
                    String latitude=location_mOdel.getLatitude();
                    String longitude=location_mOdel.getLongitude();




                    Bundle bundle = new Bundle();
                    bundle.putString("onwerid", onwerid);
                    bundle.putString("karshagsenaid", karshagsenaid);
                    bundle.putString("selecteddate", selecteddate);
                    bundle.putString("selecttime", selecttime);


                    bundle.putString("dayorhour",  holder.dayorhour);
                    bundle.putString("BookingHours", etBookingHours);
                    bundle.putString("BookingMinutes", etBookingMinutes);
                    bundle.putString("dayvalues", dayvalues);
                    bundle.putString("location", loc);
                    bundle.putString("latitude", latitude);
                    bundle.putString("longitude", longitude);


                    bundle.putString("Natureofjob",holder.desc.getText().toString() );
                    bundle.putString("number_people", holder.numbFppl.getText().toString());
                    bundle.putString("selecttime", selecttime);








                    Fragment fragment = new KarshagasenaBookingConfirm();
                    fragment.setArguments(bundle);
                    Utilities.getInstance(context).changeHomeFragment(
                            fragment, "BookNow_Confirm", context);
//
//                    book(onwerid, karshagsenaid, selecteddate, selecttime, holder.dayorhour, etBookingHours,
//                            etBookingMinutes, dayvalues,location);
                }
            }

        });


    }


    private void setBookingDateNTime(MyViewholder finalHolder) {
        DialogFragment newFragment = new SelectDateFragment(finalHolder);
        newFragment.show(fragmentManager ,"DatePicker");
    }



    private void books(int position, MyViewholder holder,String selectedDate,String selectedTime,String dayorhour,String etBookingHours,String etBookingMinutes,String dayvalue) {
        KarshakaItem_Model karshakaItem = karshakaItemList.get(position);
        FetchId fetchId = Utilities.getInstance(context).fetchId();
        final String fechid = fetchId.getUser_id();

        String onwerid=karshakaItem.getOwnerid();
        String karshagsenaid=karshakaItem.getKarshakasena_id();



    }

    private void showJSON(String response) {

        Log.e( "Karshgasena book: ",response );
        String data_avail="";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")){
                Toast.makeText(context,jsonObject.getString("errorCode"),Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,jsonObject.getString("errorCode"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return karshakaItemList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        public MyTextView title;
        public MyTextView location;
        public MyTextView numbFppl;
        public MyTextView desc;
        public Button book;
        String selectedDate, selectedTime, totalAmount;
        String dayorhouralue = "0";
        String ownerid="";
        String karshagasenaid="";
        String dayorhour="";
        String dayvalue="";
        public TextView tvBookingDateNTime;
        Button openbooking;
LinearLayout visible_layout;
         RadioGroup rgDateNTime;
        EditText mlfb_EditTextHours, mlfb_EditTextMinutes, mlfb_EditTextDays;
        Button booknow_karshagasena;

        public MyViewholder(View itemView) {
            super(itemView);
            title = (MyTextView) itemView.findViewById(R.id.job);
            location = (MyTextView) itemView.findViewById(R.id.location);
            numbFppl = (MyTextView) itemView.findViewById(R.id.tv_labours);
            desc = (MyTextView)itemView.findViewById(R.id.work_nature);
            book = (Button) itemView.findViewById(R.id.karshakasena_book_btn);

            tvBookingDateNTime = (TextView) itemView.findViewById(R.id.mlfb_BookingDateNTime);
            rgDateNTime = (RadioGroup) itemView.findViewById(R.id.mlfb_RadioGroupDateNTime);
//        etBookingDays = (EditText) findViewById(R.id.mlfb_EditTextDays);
//        etBookingHours = (EditText) findViewById(R.id.mlfb_EditTextHours);
//        etBookingMinutes = (EditText) findViewById(R.id.mlfb_EditTextMinutes);

//
//            etBookingDays = (EditText) itemView.findViewById(R.id.mlfb_EditTextDays);
//            etBookingHours = (EditText) itemView.findViewById(R.id.mlfb_EditTextHours);
//            etBookingMinutes = (EditText) itemView.findViewById(R.id.mlfb_EditTextMinutes);
//

            mlfb_EditTextDays = (EditText) itemView.findViewById(R.id.mlfb_EditTextDays);

            mlfb_EditTextHours = (EditText) itemView.findViewById(R.id.mlfb_EditTextHours);

            mlfb_EditTextMinutes = (EditText) itemView.findViewById(R.id.mlfb_EditTextMinutes);
            visible_layout = (LinearLayout) itemView.findViewById(R.id.visible_layout);
            openbooking = (Button) itemView.findViewById(R.id.openbooking);


        }
    }
    private void setRadioGroup(final MyViewholder holder) {

        holder.rgDateNTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.mlfb_RadioDays:

//                        holder.mlfb_RadioTime.setVisibility(LinearLayout.GONE);
                        holder.mlfb_EditTextHours.setVisibility(View.GONE);

                        holder.mlfb_EditTextMinutes.setVisibility(View.GONE);
                        holder.mlfb_EditTextHours.setText("");

                        holder.mlfb_EditTextMinutes.setText("");
                        holder.mlfb_EditTextDays.setVisibility(LinearLayout.VISIBLE);

                        holder.dayorhouralue = "0";

                        Log.e("GAD", "onCheckedChanged day: " + checkedId);
                        break;

                    case R.id.mlfb_RadioTime:
                        holder.mlfb_EditTextDays.setVisibility(View.GONE);
                        holder.mlfb_EditTextDays.setText("");

//                        holder.mlfb_RadioDays.setVisibility(LinearLayout.GONE);

                        holder.mlfb_EditTextHours.setVisibility(LinearLayout.VISIBLE);

                        holder.mlfb_EditTextMinutes.setVisibility(LinearLayout.VISIBLE);
                        Log.e("GAD", "onCheckedChanged time: " + checkedId);

                        holder.dayorhouralue = "1";
                        break;
                }
            }
        });
    }
    private void book(String ownerid, String karshagasenaid, String date, String time, String dayorhour, String hourvalue, String minutevalue, String dayvalue,String location) {

        FetchId fetchId = Utilities.getInstance(context).fetchId();
        final String fechid = fetchId.getUser_id();


        String url = "http://annamagrotech.com/webservice/farmer/karshakasenabooking.php?farmer_id=" + fechid + "&date=" + date + "&day_or_hour=" + dayorhour + "&hourvalue=" + hourvalue + "&minutevalue=" + minutevalue + "&dayvalue=" + dayvalue + "&karshakasena_id=" + karshagasenaid + "&time=" + time + "&farmer_land_loc=" + location;
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
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
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


    @SuppressLint("ValidFragment")
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        MyViewholder holder;

        public SelectDateFragment(MyViewholder finalHolder) {
            this.holder = finalHolder;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=   new DatePickerDialog(getActivity(), this, yy, mm, dd+1);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);


            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            //txtFromDate.setText(day+"/"+month+"/"+year);
            holder.selectedDate = day + "/" + month + "/" + year;
            DialogFragment newFragment = new SelectTimeFragment(holder);
            newFragment.show(getFragmentManager(), "TimePicker");
        }

    }

    @SuppressLint("ValidFragment")
    public static class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        MyViewholder holder;

        public SelectTimeFragment(MyViewholder holder) {
            this.holder = holder;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar now = Calendar.getInstance();
            return new TimePickerDialog(getActivity(), this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
/*            Calendar now = Calendar.getInstance();
            if (hour > now.get(Calendar.HOUR_OF_DAY)){
                populateSetTime(hour, minute);
            } else if (hour == now.get(Calendar.HOUR_OF_DAY)) {
                if (minute > now.get(Calendar.MINUTE)){
                    populateSetTime(hour, minute);
                } else {
                    //txtFromTime.setText("Please select a valid time");
                    Log.e("My Log","Please select a valid time");
                }
            } else {
                //txtFromTime.setText("Please select a valid time");
                Log.e("My Log","Please select a valid time");
            }*/
            populateSetTime(hour, minute);
        }

        public void populateSetTime(int hour, int minute) {
            if (hour < 13) {
                if (minute < 10) {
                    holder.selectedTime = hour + ":" + "0" + minute + "AM";
                } else {
                    holder.selectedTime = hour + ":" + minute + "AM";
                }
            } else {
                hour = hour - 12;
                if (minute < 10) {
                    holder.selectedTime = hour + ":" + "0" + minute + "PM";
                } else {
                    holder.selectedTime = hour + ":" + minute + "PM";
                }
            }
            String dateNTime = holder.selectedDate + " " + holder.selectedTime;
            Log.e("VJHVJH",dateNTime);
            holder.tvBookingDateNTime.setText(dateNTime);


        }
    }


    private boolean validate(MyViewholder holders) {
        boolean flag=false;
        if (holders.tvBookingDateNTime.getText().toString().equals(""))
        {
            flag=true;
            Toast.makeText(context, "Select date", Toast.LENGTH_SHORT).show();
        }else
        {
            flag=false;


            holders.tvBookingDateNTime.setError(null);
        }

        if (holders.mlfb_EditTextDays.getText().toString().equals(""))
        {


            if(holders.mlfb_EditTextHours.getText().toString().equals(""))
            {
                flag=true;
                Toast.makeText(context, "Select day or hours", Toast.LENGTH_SHORT).show();

            }
            else
            {
                holders.dayorhour="hours";



            }

        }
        else
        {
            holders.dayorhour="Day";


            holders.dayvalue=holders.mlfb_EditTextDays.getText().toString();
        }

        return flag;


    }
}
