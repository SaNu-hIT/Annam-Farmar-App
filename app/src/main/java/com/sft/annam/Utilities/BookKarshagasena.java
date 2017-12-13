package com.sft.annam.Utilities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.sft.annam.Adapter.Machine_book_List_Adapter;
import com.sft.annam.Model.FetchId;
import com.sft.annam.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

public class BookKarshagasena extends AppCompatActivity {
    public TextView tvBookingDateNTime;
    public EditText etBookingDays, etBookingHours, etBookingMinutes;
    String selectedDate, selectedTime, totalAmount;
    String dayorhouralue = "0";
    EditText mlfb_EditTextHours, mlfb_EditTextMinutes, mlfb_EditTextDays;
    Button booknow_karshagasena;
    String ownerid;
    String karshagasenaid;
    String dayorhour;

    private RadioGroup rgDateNTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_karshagasena);
        Intent intent = getIntent();
        ownerid = intent.getStringExtra("OWNERID");
        karshagasenaid = intent.getStringExtra("KERSHAGASENAID");
//        position = intent.getStringExtra("POSITION");


        tvBookingDateNTime = (TextView) findViewById(R.id.mlfb_BookingDateNTime);
        rgDateNTime = (RadioGroup) findViewById(R.id.mlfb_RadioGroupDateNTime);
//        etBookingDays = (EditText) findViewById(R.id.mlfb_EditTextDays);
//        etBookingHours = (EditText) findViewById(R.id.mlfb_EditTextHours);
//        etBookingMinutes = (EditText) findViewById(R.id.mlfb_EditTextMinutes);


        etBookingDays = (EditText) findViewById(R.id.mlfb_EditTextDays);
        etBookingHours = (EditText) findViewById(R.id.mlfb_EditTextHours);
        etBookingMinutes = (EditText) findViewById(R.id.mlfb_EditTextMinutes);


        mlfb_EditTextDays = (EditText) findViewById(R.id.mlfb_EditTextDays);

        mlfb_EditTextHours = (EditText) findViewById(R.id.mlfb_EditTextHours);

        mlfb_EditTextMinutes = (EditText) findViewById(R.id.mlfb_EditTextMinutes);
        tvBookingDateNTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBookingDateNTime();
            }
        });
        setRadioGroup();

        booknow_karshagasena = (Button) findViewById(R.id.booknow_karshagasena);
        booknow_karshagasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate())
                {


book(ownerid,karshagasenaid,selectedDate,selectedTime,dayorhour,etBookingHours.getText().toString(),etBookingMinutes.getText().toString(),selectedTime);
//                     selectedDate, selectedTime, totalAmount;

// http://annamagrotech.com/webservice/farmer/karshakasenabooking.php?farmer_id=381&date=17/7/2017&day_or_hour=&hourvalue=&minutevalue=&dayvalue=0&karshakasena_id=76&time=3:57

                }

            }
        });


    }

    private boolean validate() {
        boolean flag=false;
        if (tvBookingDateNTime.getText().toString().equals(""))
        {
            flag=true;
            Toast.makeText(this, "Select date", Toast.LENGTH_SHORT).show();
        }else
        {
            flag=false;


            tvBookingDateNTime.setError(null);
        }

        if (etBookingDays.getText().toString().equals(""))
        {

            flag=true;
            if(etBookingHours.getText().toString().equals(""))
            {
                flag=true;
                Toast.makeText(this, "Select day or houre", Toast.LENGTH_SHORT).show();

            }
            else
            {
                dayorhour="hours";

            }

        }
        else
        {
            dayorhour="Day";
        }

        return flag;


    }

    private void setBookingDateNTime() {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    private void setRadioGroup() {

        rgDateNTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.mlfb_RadioDays:

//                        holder.mlfb_RadioTime.setVisibility(LinearLayout.GONE);
                        mlfb_EditTextHours.setVisibility(View.GONE);

                        mlfb_EditTextMinutes.setVisibility(View.GONE);

                        mlfb_EditTextDays.setVisibility(LinearLayout.VISIBLE);

                        dayorhouralue = "0";

                        Log.e("GAD", "onCheckedChanged day: " + checkedId);
                        break;

                    case R.id.mlfb_RadioTime:
                        mlfb_EditTextDays.setVisibility(View.GONE);

//                        holder.mlfb_RadioDays.setVisibility(LinearLayout.GONE);

                        mlfb_EditTextHours.setVisibility(LinearLayout.VISIBLE);

                        mlfb_EditTextMinutes.setVisibility(LinearLayout.VISIBLE);
                        Log.e("GAD", "onCheckedChanged time: " + checkedId);

                        dayorhouralue = "1";
                        break;
                }
            }
        });
    }

    private void book(String ownerid, String karshagasenaid, String date, String time, String dayorhour, String hourvalue, String minutevalue, String dayvalue) {

        FetchId fetchId = Utilities.getInstance(this).fetchId();
        final String fechid = fetchId.getUser_id();

//        String url = "http://annamagrotech.com/webservice/farmer/karshakasenabooking.php?farmer_id="+fechid
//                + "&owner_id=" + karshakaItem.getOwnerid()
//                + "&karshakasena_id=" + karshakaItem.getKarshakasena_id();

        String url = "http://annamagrotech.com/webservice/farmer/karshakasenabooking.php?farmer_id=" + fechid + "&date=" + date + "&day_or_hour=" + dayorhour + "&hourvalue=" + hourvalue + "&minutevalue=" + minutevalue + "&dayvalue=" + dayvalue + "&karshakasena_id=" + karshagasenaid + "&time=" + time;
String fixurl=fixURL(url);
        StringRequest stringRequest = new StringRequest(fixurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        Log.e("Karshgasena book: ", response);
        String data_avail = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ValidFragment")
    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        Machine_book_List_Adapter.Holder holder;


        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd + 1);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);


            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            //txtFromDate.setText(day+"/"+month+"/"+year);
            selectedDate = day + "/" + month + "/" + year;
            DialogFragment newFragment = new SelectTimeFragment();
            newFragment.show(getFragmentManager(), "TimePicker");
        }

    }

    @SuppressLint("ValidFragment")
    private class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        Machine_book_List_Adapter.Holder holder;

        public SelectTimeFragment() {

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
                    selectedTime = hour + ":" + "0" + minute + " AM";
                } else {
                    selectedTime = hour + ":" + minute + " AM";
                }
            } else {
                hour = hour - 12;
                if (minute < 10) {
                    selectedTime = hour + ":" + "0" + minute + " PM";
                } else {
                    selectedTime = hour + ":" + minute + " PM";
                }
            }
            String dateNTime = selectedDate + " " + selectedTime;
            Log.e("VJHVJH", dateNTime);
            tvBookingDateNTime.setText(dateNTime);


        }
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

}
