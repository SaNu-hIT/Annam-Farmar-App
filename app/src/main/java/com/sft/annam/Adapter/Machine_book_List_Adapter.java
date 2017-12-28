package com.sft.annam.Adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Fragment.BookNow_Confirm;
import com.sft.annam.Fragment.Location_MOdel;
import com.sft.annam.Model.BookNow_TempData_Model;
import com.sft.annam.Model.Const_Model;
import com.sft.annam.Model.MachineListModel_Booking;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by JESNA on 7/13/2016.
 */
public class Machine_book_List_Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    FragmentActivity context;
    int total;
    int cliembtotal;
    int cleantotal;

    int totalpay;
    boolean flagloc = true;
    ArrayList<MachineListModel_Booking> machinreis_model = new ArrayList<MachineListModel_Booking>();
    BookNow_TempData_Model tempData_model;
    boolean[] hiddenStateON;
    boolean day, land_area, no_of_trees_toclimb, no_of_trees_to_clean, with_fuel, without_fuel, with_driver, without_friver, coconut_in_kg, pestiside_in_kg,land_type;
    String rate_per_clean = "",rate_per_clim="";
int expected_rate;
    private String ratePerDay;
boolean agreeimplats=false;
    private int mSelectedItem=-1;

    public Machine_book_List_Adapter(FragmentActivity activity,
                                     ArrayList<MachineListModel_Booking> machineries_model_array,
                                     BookNow_TempData_Model tempData_model) {

        context = activity;
        this.machinreis_model = machineries_model_array;
        this.tempData_model = tempData_model;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hiddenStateON = new boolean[machineries_model_array.size()];
    }

    @Override
    public int getCount() {
        return machinreis_model.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news, parent, false);
            holder = new Holder();
            initViews(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Holder myholder = holder;


        setHiddenLayout(myholder, position);

        setItemData(myholder, position,convertView);
        setRadioGroup(myholder);
        setQuantityLayout(myholder);
//        setPickupRequired(myholder);

        setFuelNDriver(myholder);

//
        setListeners(myholder, position);

        if(mSelectedItem == position)
        {

            myholder.body_layout.setVisibility(View.VISIBLE);
//                myholder.ibDown.setVisibility(View.GONE);
            myholder.ibUp.setVisibility(View.GONE);
//            holder.img_EventIcon.setBorderWidth(5);
//            holder.img_EventIcon.setBorderColor(context.getResources().getColor(R.color.colorPrimary));

        }
        else
        {
            myholder.body_layout.setVisibility(View.GONE);
//                myholder.ibDown.setVisibility(View.GONE);
            myholder.ibUp.setVisibility(View.GONE);
        }






        return convertView;
    }

    private void setListeners(final Holder myholder, final int position) {

        myholder.tvBookingDateNTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBookingDateNTime(myholder);
            }
        });


        myholder.inof_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("info")
//                        .setContentText("Info text goes here")
//                        .setConfirmText("ok")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
//                        .show();


            }
        });
        myholder.bBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendDataToNextScreen(myholder,position);


            }
        });

        myholder.bPlanNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (validate(myholder)) {
//
//                    String strBookOrPlan;
//                    String strMachineName;
//                    String strMachineId;
//                    String strMachineTypeName;
//                    String strMachineTypeId;
//                    String strPickupRequired;
//                    String strRatePerHour;
//                    String strTotalAmount;
//                    String strMachineImageURL;
//
//
//                    String strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes, strTotalLandArea = "", strTreesToClimb = "", strTreesToClean = "", strTotalCopra = "";
//                    boolean bDry = false, bWet = false, bLumpy = false, bWithFuel, bWithDriver, bLicense;
//
//                    strBookOrPlan = "BOOK";
//
//                    strMachineName = machinreis_model.get(position).getMachine_name();
//                    strMachineId = tempData_model.getMachine_id();
//                    strMachineTypeName = machinreis_model.get(position).getMachine_type();
//                    strMachineTypeId = machinreis_model.get(position).getMachine_id();
//                    strPickupRequired = tempData_model.getPickupRequired();
//                    strRatePerHour = machinreis_model.get(position).getMachine_rate_hour();
//
//                    rate_per_clean=machinreis_model.get(position).getRate_per_tree_clean();
//                    rate_per_clim=machinreis_model.get(position).getRate_per_tree_climb();
//
//
//
//                    ratePerDay=myholder.tvRatePerDay.getText().toString();
////                    Log.e("Rate", "onClick: "+ratePerDay);
//
//
//                    strMachineImageURL = machinreis_model.get(position).getMachine_image();
//                    Log.e("strMachineImageURL", "strMachineImageURL: "+strMachineImageURL);
//                    strBookingDate = myholder.selectedDate;
//                    strBookingTime = myholder.selectedTime;
//
//
//                    String  cleanamounts=myholder.etTreesToClean.getText().toString();
//                    String  climbamounss=myholder.etTreesToClimb.getText().toString();
//                    int clearnamout = 0;
//                    int cliemb=0;
//                    if(!cleanamounts.equals(""))
//                    {
//                        clearnamout= Integer.parseInt(cleanamounts);
//                    }
//                    if(!climbamounss.equals(""))
//                    {
//                        cliemb= Integer.parseInt(climbamounss);
//                    }
//
//
//
//                    int rate= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_climb());
//                    int rate_clean= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_clean());
//                    String strday=myholder.etTreesToClimb.getText().toString();
//
//                    int total=(clearnamout*rate_clean)+ (cliemb*rate);
//                    Log.e( "Total: ",""+total );
//                    myholder.totalAmount = String.valueOf(total);
//
//
//
//
//                    strTotalAmount = myholder.totalAmount;
//
//
//                    strDays = myholder.etBookingDays.getText().toString();
//
//                    if(strDays.equals(""))
//                    {
//                        strDayOrHour = "Hour";
//                    }
//                    else
//                    {
//                        strDayOrHour = "Day";
//                    }
//
//
//                    strHours = myholder.etBookingHours.getText().toString();
//                    strMinutes = myholder.etBookingMinutes.getText().toString();
//
//
//
//
//                    strTotalLandArea = myholder.etLandArea.getText().toString();
//                    bDry = myholder.cbDry.isChecked();
//                    bWet = myholder.cbWet.isChecked();
//                    bLumpy = myholder.cbLumpy.isChecked();
//
//                    strTreesToClean = myholder.etTreesToClean.getText().toString();
//                    strTreesToClimb = myholder.etTreesToClimb.getText().toString();
//
//                    strTotalCopra = myholder.etCopra.getText().toString();
//
//
//
//                    bWithFuel = myholder.fuelFlag.equals("WithFuel");
//                    bWithDriver = myholder.driverFlag.equals("WithDriver");
//
//
//                    bLicense = bWithDriver && myholder.cbLicense.isChecked();
//
//
//
//
//
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("BookOrPlan", strBookOrPlan);
//                    bundle.putString("MachineName", strMachineName);
//                    bundle.putString("MachineId", strMachineId);
//                    bundle.putString("MachineTypeName", strMachineTypeName);
//                    bundle.putString("MachineTypeId", strMachineTypeId);
//                    bundle.putString("MachineImageURL", strMachineImageURL);
//                    bundle.putString("PickupRequired", strPickupRequired);
//                    bundle.putString("RatePerHour", strRatePerHour);
//                    Log.e( "onClick: ",""+strTotalAmount );
//                    bundle.putString("TotalAmount", strTotalAmount);
//                    bundle.putString("BookingDate", strBookingDate);
//                    bundle.putString("BookingTime", strBookingTime);
//                    bundle.putString("DayOrHour", strDayOrHour);
//                    bundle.putString("Days", strDays);
//                    bundle.putString("RatePerDay", ratePerDay);
//                    bundle.putString("Hours", strHours);
//                    bundle.putString("Minutes", strMinutes);
//                    bundle.putString("RatePerClean", rate_per_clean);
//
//
//                    bundle.putString("RarePerClimb", rate_per_clim);
//
//
//                    bundle.putString("TotalLandArea", strTotalLandArea);
//                    bundle.putBoolean("Dry", bDry);
//                    bundle.putBoolean("Wet", bWet);
//                    bundle.putBoolean("Lumpy", bLumpy);
//
//                    bundle.putString("TreesToClimb", strTreesToClimb);
//                    bundle.putString("TreesToClean", strTreesToClean);
//
//
//                    Log.e( "strTreesToClimb: ",""+strTreesToClimb );
//                    Log.e( "strTreesToClean: ",""+strTreesToClean );
//
//                    bundle.putString("TotalCopra", strTotalCopra);
//
//                    bundle.putBoolean("WithFuel", bWithFuel);
//                    bundle.putBoolean("WithDriver", bWithDriver);
//
//                    Const_Model.setMachine_name(machinreis_model.get(position).getMachine_type());
//                    Const_Model.setMachine_details(machinreis_model.get(position).getMachine_description());
//
//                    Fragment fragment = new BookNow_Confirm();
//                    fragment.setArguments(bundle);
//                    Utilities.getInstance(context).changeHomeFragment(
//                            fragment, "BookNow_Confirm", context);
//                } else {
//                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
//                }



                showAlertNoFeacture();
            }
        });
//        myholder.etTreesToClimb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b)
//                {int rate= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_climb());
//                    String strday=myholder.etTreesToClimb.getText().toString();
//                    int climb=0;
//                    if(strday.equals(""))
//                    {
//                        climb =0;
//
//
//                    }
//                    else
//                    {
//                        climb= Integer.parseInt(strday);
//                    }
//                    int total=rate*climb;
//                    totalpay+=total;
//                    myholder.totalAmount = String.valueOf(totalpay);
////                    String temp= "₹" + total + "\nTotal";
//                    Log.e( "climb ",""+totalpay );
////                    myholder.tvTotalRate.setText(temp);
//
//                }
//            }
//        });
//
//        myholder.etTreesToClean.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b)
//                {
//                    int rate= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_clean());
//                    String strday=myholder.etTreesToClean.getText().toString();
//                    int climb=0;
//                    if(strday.equals(""))
//                    {
//                        climb =0;
//
//
//                    }
//                    else
//                    {
//                        climb= Integer.parseInt(strday);
//                    }
//                    int total=rate*climb;
//                    totalpay+=total;
//                    String temp= "₹" + totalpay + "\nTotal";
//                    myholder.totalAmount = String.valueOf(totalpay);
//                    Log.e( "clean ",temp );
////                    myholder.tvTotalRate.setText(temp);
////                    myholder.tvTotalRate.setText(temp);
//                }
//            }
//        });


//

//
//        myholder.etTreesToClimb.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                cliembtotal=0;
//
//                if(s.length() != 0) {
//
//                    int rate= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_climb());
//                    String strday=myholder.etTreesToClimb.getText().toString();
//
//                    cliembtotal=rate;
////                    int climb=0;
////                    if(strday.equals(""))
////                    {
////                        climb =0;
////
////
////                    }
////                    else
////                    {
////                        climb= Integer.parseInt(strday);
////                    }
////                    int total=rate*climb;
////                    cliembtotal+=total;
////
////
//////                    myholder.totalAmount = String.valueOf(cliembtotal);
////                    String temp= "₹" + cliembtotal + "\nTotal";
////                    Log.e( "climb: ",""+totalpay );
////                    myholder.tvTotalRate.setText(temp);
////
//                }
//
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//
//            }
//        });
//
//
//
//
//        myholder.etTreesToClean.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                cleantotal=0;
//
//                if(s.length() != 0) {
//
//                    int rate= Integer.parseInt(machinreis_model.get(position).getRate_per_tree_clean());
//                    String strday=myholder.etTreesToClean.getText().toString();
//                    cleantotal=rate;
////                    int climb=0;
////                    if(strday.equals(""))
////                    {
////                        climb =0;
////
////
////                    }
////                    else
////                    {
////                        climb= Integer.parseInt(strday);
////                    }
////                    int total=rate*climb;
////                    cleantotal+=total;
////                    String temp= "₹" + cleantotal + "\nTotal";
//////                    myholder.totalAmount = String.valueOf(totalpay);
////                    Log.e( "clean: ",temp );
////                    myholder.tvTotalRate.setText(temp);
////                    myholder.etTreesToClean.setText(temp);
//                }
//
//
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//
//            }
//        });


        myholder.etBookingDays.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int rateperday = Integer.parseInt(machinreis_model.get(position).getMachine_rate_perday());
                    String strdays = myholder.etBookingDays.getText().toString();
                    int days = 0;
                    if (strdays.equals(""))
                        days = 0;
                    else
                        days = Integer.parseInt(strdays);
                    int total = rateperday * days;
                    String temp = "₹" + total + "\nTotal";
                    myholder.tvTotalRate.setText(temp);
                }
            }
        });
        myholder.etBookingMinutes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int rate = Integer.parseInt(machinreis_model.get(position).getMachine_rate_hour());
                    String strHrs = myholder.etBookingHours.getText().toString();
                    String strMints = myholder.etBookingMinutes.getText().toString();
                    int hours = 0;
                    int mints = 0;
                    if (strHrs.equals(""))
                        hours = 0;
                    else
                        hours = Integer.parseInt(strHrs);
                    if (strMints.equals(""))
                        mints = 0;
                    else
                        mints = Integer.parseInt(strMints);
                    int total = rate * (hours + (mints / 60));
                    myholder.totalAmount = String.valueOf(total);
                    String temp = "₹" + total + "\nTotal";
//                    myholder.tvTotalRate.setText(temp);


                    Log.e("etBookingMinutes: ","ToT:"+total );
                }
            }
        });


        myholder.etBookingHours.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String hours_txt = myholder.etBookingHours.getText().toString();
                    String minute_txt = myholder.etBookingMinutes.getText().toString();
                    int hours = 0;
                    int mints = 0;

                    if (!hours_txt.equals("")) {
                        hours = Integer.parseInt(myholder.etBookingHours.getText().toString());
                    }
                    if (!minute_txt.equals("")) {
                        mints = Integer.parseInt(myholder.etBookingMinutes.getText().toString());
                    }


                    int rate = Integer.parseInt(machinreis_model.get(position).getMachine_rate_hour());
//                    int hours = Integer.parseInt(myholder.etBookingHours.getText().toString());
//                    int mints = Integer.parseInt(myholder.etBookingMinutes.getText().toString());
                    if (hours != 0 && mints != 0) {

                       int  total = rate * (hours + (mints / 60));
                        myholder.totalAmount = String.valueOf(total);
                        String temp = "₹" + total + "\nTotal";
//                        myholder.tvTotalRate.setText(temp);
                    }

                    Log.e("etBookingHours: ","ToT:"+total );
                }
            }
        });




    }

    private void sendDataToNextScreen(Holder myholder,int position) {


        if (validate(myholder)) {

            String strBookOrPlan;
            String strMachineName;
            String strMachineId;
            String strMachineTypeName;
            String strMachineTypeId;
            String strPickupRequired;
            String strRatePerHour;
            String strTotalAmount;
            String strMachineImageURL;


            String strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes, strTotalLandArea = "", strTreesToClimb = "", strTreesToClean = "", strTotalCopra = "";
            boolean bDry = false, bWet = false, bLumpy = false, bWithFuel, bWithDriver, bLicense;

            strBookOrPlan = "BOOK";

            strMachineName = machinreis_model.get(position).getMachine_name();
            strMachineId = tempData_model.getMachine_id();
            strMachineTypeName = machinreis_model.get(position).getMachine_type();
            strMachineTypeId = machinreis_model.get(position).getMachine_id();
            strPickupRequired = tempData_model.getPickupRequired();
            strRatePerHour = machinreis_model.get(position).getMachine_rate_hour();

            rate_per_clean=machinreis_model.get(position).getRate_per_tree_clean();
            rate_per_clim=machinreis_model.get(position).getRate_per_tree_climb();



            ratePerDay=myholder.tvRatePerDay.getText().toString();
//                    Log.e("Rate", "onClick: "+ratePerDay);


            strMachineImageURL = machinreis_model.get(position).getMachine_image();
            Log.e("strMachineImageURL", "strMachineImageURL: "+strMachineImageURL);
            strBookingDate = myholder.selectedDate;
            strBookingTime = myholder.selectedTime;


            String  cleanamounts=myholder.etTreesToClean.getText().toString();
            String  climbamounss=myholder.etTreesToClimb.getText().toString();
            int clearnamout = 0;
            int cliemb=0;
            if(!cleanamounts.equals(""))
            {
                clearnamout= Integer.parseInt(cleanamounts);
            }
            if(!climbamounss.equals(""))
            {
                cliemb= Integer.parseInt(climbamounss);
            }

            if(!machinreis_model.get(position).getRate_per_tree_climb().equals("")) {

                int rate = Integer.parseInt(machinreis_model.get(position).getRate_per_tree_climb());
                int rate_clean = Integer.parseInt(machinreis_model.get(position).getRate_per_tree_clean());
                String strday = myholder.etTreesToClimb.getText().toString();

                int total = (clearnamout * rate_clean) + (cliemb * rate);
                Log.e("Total: ", "" + total);
                myholder.totalAmount = String.valueOf(total);
            }else
            {
                myholder.totalAmount="0";
            }




            strTotalAmount = myholder.totalAmount;


            strDays = myholder.etBookingDays.getText().toString();

            if(strDays.equals(""))
            {
                strDayOrHour = "Hour";
            }
            else
            {
                strDayOrHour = "Day";
            }


            strHours = myholder.etBookingHours.getText().toString();
            strMinutes = myholder.etBookingMinutes.getText().toString();




            strTotalLandArea = myholder.etLandArea.getText().toString();
            bDry = myholder.cbDry.isChecked();
            bWet = myholder.cbWet.isChecked();
            bLumpy = myholder.cbLumpy.isChecked();

            strTreesToClean = myholder.etTreesToClean.getText().toString();
            strTreesToClimb = myholder.etTreesToClimb.getText().toString();

            strTotalCopra = myholder.etCopra.getText().toString();



            bWithFuel = myholder.fuelFlag.equals("WithFuel");
            bWithDriver = myholder.driverFlag.equals("WithDriver");


            bLicense = bWithDriver && myholder.cbLicense.isChecked();






            Bundle bundle = new Bundle();
            bundle.putString("BookOrPlan", strBookOrPlan);
            bundle.putString("MachineName", strMachineName);
            bundle.putString("MachineId", strMachineId);
            bundle.putString("MachineTypeName", strMachineTypeName);
            bundle.putString("MachineTypeId", strMachineTypeId);
            bundle.putString("MachineImageURL", strMachineImageURL);
            bundle.putString("PickupRequired", strPickupRequired);
            bundle.putString("RatePerHour", strRatePerHour);
            Log.e( "onClick: ",""+strTotalAmount );
            bundle.putString("TotalAmount", strTotalAmount);
            bundle.putString("BookingDate", strBookingDate);
            bundle.putString("BookingTime", strBookingTime);
            bundle.putString("DayOrHour", strDayOrHour);
            bundle.putString("Days", strDays);
            bundle.putString("RatePerDay", ratePerDay);
            bundle.putString("Hours", strHours);
            bundle.putString("Minutes", strMinutes);
            bundle.putString("RatePerClean", rate_per_clean);


            bundle.putString("RarePerClimb", rate_per_clim);


            bundle.putString("TotalLandArea", strTotalLandArea);
            bundle.putBoolean("Dry", bDry);
            bundle.putBoolean("Wet", bWet);
            bundle.putBoolean("Lumpy", bLumpy);

            bundle.putString("TreesToClimb", strTreesToClimb);
            bundle.putString("TreesToClean", strTreesToClean);


            Log.e( "strTreesToClimb: ",""+strTreesToClimb );
            Log.e( "strTreesToClean: ",""+strTreesToClean );

            bundle.putString("TotalCopra", strTotalCopra);

            bundle.putBoolean("WithFuel", bWithFuel);
            bundle.putBoolean("WithDriver", bWithDriver);

            Const_Model.setMachine_name(machinreis_model.get(position).getMachine_type());
            Const_Model.setMachine_details(machinreis_model.get(position).getMachine_description());

            Fragment fragment = new BookNow_Confirm();
            fragment.setArguments(bundle);
            Utilities.getInstance(context).changeHomeFragment(
                    fragment, "BookNow_Confirm", context);
        } else {
            Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
    }


    private void showAlertNoFeacture() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Please use Book Now");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private boolean validate(Holder myholder) {






        boolean flag = true;


        String strBookingDate, strDays, strHours, strMinutes, strTotalLandArea, strTreesToClean, strTreesToClimb, strTotalCopra;


        boolean bDry, bWet, bLumpy, bFuel, bDriver, bWithoutDriver, bLicense;


    //date valifatiom

        strBookingDate = myholder.tvBookingDateNTime.getText().toString();

        if (strBookingDate.equalsIgnoreCase("Enter Date-Time")) {
            myholder.tvBookingDateNTime.setError(context.getString(R.string.error_field_required));

            flag = false;
        } else {
            myholder.tvBookingDateNTime.setError(null);
        }


if(day) {

    if (myholder.dayorhouralue.equals("0")) {
        strDays = myholder.etBookingDays.getText().toString();
        if (TextUtils.isEmpty(strDays)) {
            myholder.etBookingDays.setError(context.getString(R.string.error_field_required));

            flag = false;
        } else {
            myholder.etBookingDays.setError(null);
        }
    } else {
        strHours = myholder.etBookingHours.getText().toString();
        strMinutes = myholder.etBookingMinutes.getText().toString();


        if (TextUtils.isEmpty(strHours)) {
            myholder.etBookingHours.setError(context.getString(R.string.error_field_required));

            flag = false;
        } else {
            myholder.etBookingHours.setError(null);
        }
        if (TextUtils.isEmpty(strMinutes)) {

            strMinutes = "0";

        } else {
            myholder.etBookingMinutes.setError(null);
        }
    }

}

        if(land_area) {

            strTotalLandArea = myholder.etLandArea.getText().toString();
                    if (TextUtils.isEmpty(strTotalLandArea)||strTotalLandArea.equals("0")) {
                        myholder.etLandArea.setError(context.getString(R.string.error_field_required));

                        flag = false;
                    } else {
                        myholder.etLandArea.setError(null);
                    }
                }


boolean check_anyobne_is_connecetd_cleran=false;
        boolean check_anyobne_is_connecetd_climb=false;

        strTreesToClean = myholder.etTreesToClean.getText().toString();
                strTreesToClimb = myholder.etTreesToClimb.getText().toString();
                if (no_of_trees_to_clean)
                {
                    if (TextUtils.isEmpty(strTreesToClean)) {
//                        myholder.etTreesToClean.setError(context.getString(R.string.error_field_required));

//                        flag = false;
                        check_anyobne_is_connecetd_cleran=true;
                    } else {
                        myholder.etTreesToClean.setError(null);
                    }
        }
        if(no_of_trees_toclimb) {

            if (TextUtils.isEmpty(strTreesToClimb)) {
//                myholder.etTreesToClimb.setError(context.getString(R.string.error_field_required));

                check_anyobne_is_connecetd_climb=true;

//                flag = false;
            } else {
                myholder.etTreesToClimb.setError(null);
            }
        }

        if(check_anyobne_is_connecetd_cleran&&check_anyobne_is_connecetd_climb)
        {
            myholder.etTreesToClean.setError(context.getString(R.string.error_field_required));
            myholder.etTreesToClimb.setError(context.getString(R.string.error_field_required));
            flag=false;
        }



        if(coconut_in_kg) {

            strTotalCopra = myholder.etCopra.getText().toString();
            if (coconut_in_kg) {
                if (TextUtils.isEmpty(strTotalCopra)) {
                    myholder.etCopra.setError(context.getString(R.string.error_field_required));

                    flag = false;
                } else {
                    myholder.etCopra.setError(null);
                }
            }
        }





        return flag;
    }

    private void setHiddenLayout(final Holder myholder, final int position) {
//        if (!hiddenStateON[position]) {
//            myholder.lyToHide.setVisibility(LinearLayout.GONE);
//            myholder.lyDownButton.setVisibility(LinearLayout.VISIBLE);
//        } else {
//            myholder.lyToHide.setVisibility(LinearLayout.VISIBLE);
//            myholder.lyDownButton.setVisibility(LinearLayout.GONE);
//        }
//
//        myholder.ibDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                myholder.body_layout.setVisibility(View.VISIBLE);
//                myholder.ibDown.setVisibility(View.GONE);
//                myholder.ibUp.setVisibility(View.VISIBLE);
//
//
//
//
//            }
//        });
//
        myholder.ibUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myholder.body_layout.setVisibility(View.GONE);
                myholder.ibUp.setVisibility(View.GONE);
                myholder.ibDown.setVisibility(View.VISIBLE);

            }
        });


        myholder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedItem=position;
                myholder.body_layout.setVisibility(View.VISIBLE);
//                myholder.ibDown.setVisibility(View.GONE);
                myholder.ibUp.setVisibility(View.VISIBLE);

                notifyDataSetChanged();



            }
        });


    }

    private void setFuelNDriver(final Holder myholder) {

        myholder.lyOfLicense.setVisibility(View.GONE);

        myholder.lyOfWithDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myholder.lyOfWithDriver.setBackgroundResource(R.drawable.fill_light_green);
                myholder.lyOfWithoutDriver.setBackgroundResource(R.drawable.book_a_service_bg);
                myholder.lyOfLicense.setVisibility(View.GONE);
                myholder.driverFlag = "WithDriver";
                myholder.piockUp.setVisibility(View.GONE);

            }
        });

        myholder.lyOfWithoutDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myholder.lyOfWithDriver.setBackgroundResource(R.drawable.book_a_service_bg);
                myholder.lyOfWithoutDriver.setBackgroundResource(R.drawable.fill_light_green);
                myholder.lyOfLicense.setVisibility(View.VISIBLE);
                myholder.driverFlag = "WithoutDriver";
                myholder.piockUp.setVisibility(View.VISIBLE);
            }
        });

        myholder.lyOfWithFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myholder.lyOfWithFuel.setBackgroundResource(R.drawable.fill_light_green);
                myholder.lyOfWithoutFuel.setBackgroundResource(R.drawable.book_a_service_bg);
                myholder.fuelFlag = "WithFuel";
            }
        });

        myholder.lyOfWithoutFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myholder.lyOfWithFuel.setBackgroundResource(R.drawable.book_a_service_bg);
                myholder.lyOfWithoutFuel.setBackgroundResource(R.drawable.fill_light_green);
                myholder.fuelFlag = "WithoutFuel";
            }
        });
    }

//    private void setPickupRequired(Holder myholder) {
//        if (tempData_model.getPickupRequired().equals("true"))
//            myholder.lyOfPickupRequired.setVisibility(View.VISIBLE);
//        else
//            myholder.lyOfPickupRequired.setVisibility(View.GONE);
//    }

    private void setQuantityLayout(final Holder myholder) {
//        if (tempData_model.getQuantity().equalsIgnoreCase("Acres")) {
//
//            myholder.lyOfTrees.setVisibility(View.GONE);
//            myholder.lyOfCopra.setVisibility(View.GONE);
            myholder.cbDry.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //is chkIos checked?
                    if (((CheckBox) v).isChecked()) {
                        myholder.cbWet.setChecked(false);
                        myholder.cbLumpy.setChecked(false);
                    }
                }
            });
            myholder.cbWet.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //is chkIos checked?
                    if (((CheckBox) v).isChecked()) {
                        myholder.cbDry.setChecked(false);
                        myholder.cbLumpy.setChecked(false);
                    }
                }
            });
            myholder.cbLumpy.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //is chkIos checked?
                    if (((CheckBox) v).isChecked()) {
                        myholder.cbDry.setChecked(false);
                        myholder.cbWet.setChecked(false);
                    }
                }
            });
//
    }

    private void setItemData(Holder myholder, int position,View conview)
    {
        String machanename = machinreis_model.get(position).getMachine_name();
        Log.e("machanename: ",machanename );
        if (machanename.equals("coconut tree climber")) {
            flagloc = false;
            myholder.vehicledetails.setVisibility(View.GONE);
        }
           if (machanename.equals("Agri Impliments")) {
               agreeimplats=true;
//            flagloc = false;
//          myholder.lyOfWithFuel.setVisibility(View.GONE);
//          myholder.lyOfWithFuel.setVisibility(View.GONE);
        }

        String strMachineName = machinreis_model.get(position).getMachine_type() + "\n" + machinreis_model.get(position).getMachine_name();


        String machanenames=machinreis_model.get(position).getMachine_type();
        String str_MacTypee = machinreis_model.get(position).getMachine_description();
        myholder.tvMachineDescription.setText(str_MacTypee);
        myholder.tvMachineTitle.setText(strMachineName);
        Const_Model.setMachine_details(str_MacTypee);

        String perday=machinreis_model.get(position).getMachine_rate_perday();

     String rateperCliemb=machinreis_model.get(position).getRate_per_tree_climb();
        String rateperClean=machinreis_model.get(position).getRate_per_tree_clean();
        String rate_per_coconut_kg=machinreis_model.get(position).getRate_per_coconut_kg();
        String rate_per_pesticide_kg=machinreis_model.get(position).getRate_per_pesticide_kg();
        Log.e("TAGS", "rate_per_coconut_kg: "+rate_per_coconut_kg );

        Log.e("TAGS", "setItemData: "+rate_per_coconut_kg );
        Log.e("TAGS", "rate_per_pesticide_kg: "+rate_per_pesticide_kg );

//        if(!perday.equals("0"))
//        {
//
//
//            String strRatePerDay = "₹" + machinreis_model.get(position).getMachine_rate_perday() + "\n Per Day";
//            String strRatePerHour = "₹" + machinreis_model.get(position).getMachine_rate_hour() + "\n Per Hour";
//
//            myholder.tvRatePerDay.setText(strRatePerDay);
//
//
//
//
//
//            myholder.tvTotalRate.setText(strRatePerHour);
//
//
//
//
//
//        }
//        if(!rateperCliemb.equals(""))
//        {
//
//
//            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_tree_climb() + "\n Per Climb";
//
//
//            myholder.tvRatePerDay.setText(strRatePerDay);
//
//            String strRatePerHour = "₹" + machinreis_model.get(position).getRate_per_tree_clean() + "\n Per Clean";
//
//
//
//
//
//
//
//            myholder.tvTotalRate.setText(strRatePerHour);
//
//
//
//        }

//        if(!rateperClean.equals("")) {
//
//
//            String strRatePerHour = "₹" + machinreis_model.get(position).getRate_per_tree_clean() + "\n Per Clean";
//
//
//
//
//
//
//
//            myholder.tvTotalRate.setText(strRatePerHour);
//
//
//        }
//        if(!rate_per_coconut_kg.equals("0")) {
//
//
//            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_coconut_kg() + "\n Per Kg";
//
//            myholder.tvRatePerDay.setText(strRatePerDay);
//
//
//
//
//
//
//        }
//
//        if(!rate_per_pesticide_kg.equals("0")) {
//
//
//            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_pesticide_kg() + "\n Per Kg";
//
//            myholder.tvRatePerDay.setText(strRatePerDay);
//
//
//
//
//
//
//        }



        String imgURL = machinreis_model.get(position).getMachine_image();
        URI uri = null;
        try {
            uri = new URI(imgURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String imageURL = String.valueOf(uri);


        Picasso.with(context)
                .load(imageURL)
                .error(R.drawable.tillerharvester)      // optional
                .resize(400, 400)                        // optional
                .into(myholder.ivMachineImage);





        no_of_trees_toclimb = machinreis_model.get(position).isNo_of_trees_toclimb();
        no_of_trees_to_clean = machinreis_model.get(position).isNo_of_trees_to_clean();
        with_fuel = machinreis_model.get(position).isWith_fuel();
        without_fuel = machinreis_model.get(position).isWithout_fuel();
        with_driver = machinreis_model.get(position).isWith_driver();
        without_friver = machinreis_model.get(position).isWithout_friver();
        coconut_in_kg = machinreis_model.get(position).isCoconut_in_kg();
        pestiside_in_kg = machinreis_model.get(position).isPestiside_in_kg();
        day = machinreis_model.get(position).isDay();
        land_area = machinreis_model.get(position).isLand_area();


        land_type=machinreis_model.get(position).isLand_type();



        if (coconut_in_kg) {
            Log.e("FLAG true", "setItemData: "+coconut_in_kg );

            myholder.lyOfCoprasssss.setVisibility(LinearLayout.VISIBLE);

            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_coconut_kg() + "\n Per Kg";

            myholder.tvRatePerDay.setText(strRatePerDay);

//            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_pesticide_kg() + "\n Per Kg";
//
//            myholder.tvRatePerDay.setText(strRatePerDay);




        }

        if (!pestiside_in_kg) {



        }
        if(land_type)
        {
            myholder.land_type_txt.setVisibility(LinearLayout.VISIBLE);
        }


        if (with_driver) {
            myholder.lyOfWithDriver.setVisibility(LinearLayout.VISIBLE);
            myholder.inof_imageviewss.setVisibility(LinearLayout.VISIBLE);
        }
        if (without_friver) {

            myholder.lyOfWithoutDriver.setVisibility(LinearLayout.VISIBLE);
            myholder.inof_imageviewss.setVisibility(LinearLayout.VISIBLE);
        }

        if (with_fuel) {
            myholder.lyOfWithFuel.setVisibility(LinearLayout.VISIBLE);
            myholder.inof_imageviewss.setVisibility(LinearLayout.VISIBLE);
        }
        if (without_fuel) {
            myholder.lyOfWithoutFuel.setVisibility(LinearLayout.VISIBLE);
            myholder.inof_imageviewss.setVisibility(LinearLayout.VISIBLE);

        }

if(agreeimplats)
{
    myholder.lyOfWithoutFuel.setVisibility(LinearLayout.GONE);
    myholder.inof_imageviewss.setVisibility(LinearLayout.GONE);
}


        if (day) {


            myholder.day_true_visibiliyu.setVisibility(LinearLayout.VISIBLE);

        }
        else
        {



        }
        if (land_area) {
myholder.visible_land_area.setVisibility(LinearLayout.VISIBLE);

            String strRatePerDay = "₹" + machinreis_model.get(position).getMachine_rate_perday() + "\n Per Day";
            String strRatePerHour = "₹" + machinreis_model.get(position).getMachine_rate_hour() + "\n Per Hour";

            myholder.tvRatePerDay.setText(strRatePerDay);





            myholder.tvTotalRate.setText(strRatePerHour);

        }


        if (no_of_trees_toclimb) {
            myholder.lyOfTreessss.setVisibility(LinearLayout.VISIBLE);

            String strRatePerDay = "₹" + machinreis_model.get(position).getRate_per_tree_climb() + "\n Per Climb";


            myholder.tvRatePerDay.setText(strRatePerDay);

            String strRatePerHour = "₹" + machinreis_model.get(position).getRate_per_tree_clean() + "\n Per Clean";







            myholder.tvTotalRate.setText(strRatePerHour);


        }
//        if (no_of_trees_to_clean) {
//            myholder.lyOfTrees.setVisibility(View.GONE);
//
//
//        }
//
//
//if(rate_per _hour)
//{
//
//
//    myholder.tvRatePerDay.setText("rate_per_day");
//    myholder.tvTotalRate.setText("rate_per_hour.");
//}
//
//        if(cocunut_tree)
//        {
//
//
//            myholder.tvRatePerDay.setText("rate_to climb");
//            myholder.tvTotalRate.setText("rate_to_clean.");
//        }
//








    }

    private void setBookingDateNTime(Holder finalHolder) {
        DialogFragment newFragment = new SelectDateFragment(finalHolder);
        newFragment.show(context.getSupportFragmentManager(), "DatePicker");
    }

    private void setRadioGroup(final Holder holder) {

        holder.rgDateNTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.mlfb_RadioDays:

//                        holder.mlfb_RadioTime.setVisibility(LinearLayout.GONE);
                        holder.mlfb_EditTextHours.setVisibility(View.GONE);

                        holder.mlfb_EditTextMinutes.setVisibility(View.GONE);

                        holder.mlfb_EditTextDays.setVisibility(LinearLayout.VISIBLE);

                        holder.dayorhouralue = "0";

                        Log.e("GAD", "onCheckedChanged day: "+checkedId);
                        break;

                    case R.id.mlfb_RadioTime:
                        holder.mlfb_EditTextDays.setVisibility(View.GONE);

//                        holder.mlfb_RadioDays.setVisibility(LinearLayout.GONE);

                        holder.mlfb_EditTextHours.setVisibility(LinearLayout.VISIBLE);

                        holder.mlfb_EditTextMinutes.setVisibility(LinearLayout.VISIBLE);
                        Log.e("GAD", "onCheckedChanged time: "+checkedId);

                        holder.dayorhouralue = "1";
                        break;
                }
            }
        });
    }

    private void initViews(Holder holder, View convertView) {
        holder.ivMachineImage = (ImageView) convertView.findViewById(R.id.mlfb_MachineImage);
        holder.ivMachineIcon = (ImageView) convertView.findViewById(R.id.mlfb_MachineIcon);
        holder.inof_imageview = (ImageView) convertView.findViewById(R.id.inof_imageview);

        holder.tvMachineTitle = (TextView) convertView.findViewById(R.id.mlfb_MachineTitle);
        holder.tvMachineDescription = (TextView) convertView.findViewById(R.id.mlfb_MachineDescription);
        holder.tvRatePerDay = (TextView) convertView.findViewById(R.id.mlfb_RatePerDay);
        holder.tvTotalRate = (TextView) convertView.findViewById(R.id.mlfb_TotalRate);
        holder.tvBookingDateNTime = (TextView) convertView.findViewById(R.id.mlfb_BookingDateNTime);


        holder.header_layout= (LinearLayout) convertView.findViewById(R.id.header_view);
        holder.body_layout= (LinearLayout) convertView.findViewById(R.id.child_view);


//
//        holder.lyDownButton = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfDownButton);
//        holder.lyToHide = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutToHide);
//
//        holder.lyOfHours = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfHours);
//
        holder.lyOfTreessss = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfTrees_new);
        holder.card_view = (LinearLayout) convertView.findViewById(R.id.card_view);
        holder.inof_imageviewss = (LinearLayout) convertView.findViewById(R.id.inof_imageviewss);
        holder.lyOfCoprasssss = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfCoprasss);
        holder.lyOfWithFuel = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithFuel);
        holder.lyOfWithoutFuel = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithoutFuel);
        holder.lyOfWithDriver = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithDriver);
        holder.lyOfWithoutDriver = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithoutDriver);
        holder.lyOfLicense = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfLicense);
        holder.lyOfPickupRequired_NEW = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfPickupRequired);
        holder.lyOfUpButton = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfUpButton);
        holder.vehicledetails = (LinearLayout) convertView.findViewById(R.id.vehicledetails);
        holder.ibDown = (ImageButton) convertView.findViewById(R.id.mlfb_ButtonDown);
        holder.ibUp = (ImageButton) convertView.findViewById(R.id.mlfb_ButtonUp);

        holder.rgDateNTime = (RadioGroup) convertView.findViewById(R.id.mlfb_RadioGroupDateNTime);

        holder.rbDays = (RadioButton) convertView.findViewById(R.id.mlfb_RadioDays);
        holder.rbTime = (RadioButton) convertView.findViewById(R.id.mlfb_RadioTime);

        holder.etBookingDays = (EditText) convertView.findViewById(R.id.mlfb_EditTextDays);
        holder.etBookingHours = (EditText) convertView.findViewById(R.id.mlfb_EditTextHours);
        holder.etBookingMinutes = (EditText) convertView.findViewById(R.id.mlfb_EditTextMinutes);
        holder.etLandArea = (EditText) convertView.findViewById(R.id.mlfb_EditTextLandArea);
        holder.etTreesToClimb = (EditText) convertView.findViewById(R.id.mlfb_EditTextTreesToClimb);
        holder.etTreesToClean = (EditText) convertView.findViewById(R.id.mlfb_EditTextTreesToClean);
        holder.etCopra = (EditText) convertView.findViewById(R.id.mlfb_EditTextCopra);

        holder.cbDry = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxDry);
        holder.cbWet = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxWet);
        holder.cbLumpy = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxLumpy);
        holder.cbLicense = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxLicense);

        holder.bBookNow = (Button) convertView.findViewById(R.id.mlfb_ButtonBookNow);
        holder.bPlanNow = (Button) convertView.findViewById(R.id.mlfb_ButtonPlanNow);
        holder.piockUp = (TextView) convertView.findViewById(R.id.pickup_id);
        //------------------------ END ---------------------------


        holder.visible_land_area = (LinearLayout) convertView.findViewById(R.id.visible_land_area);
        holder.day_visibility = (LinearLayout) convertView.findViewById(R.id.day_visibility);


        holder.date_and_time_layout_id = (LinearLayout) convertView.findViewById(R.id.date_and_time_layout_id);
//
//        holder.radio_button_time = (LinearLayout) convertView.findViewById(R.id.radio_button_time);

        holder.mlfb_EditTextDays = (EditText) convertView.findViewById(R.id.mlfb_EditTextDays);

        holder.mlfb_EditTextHours = (EditText) convertView.findViewById(R.id.mlfb_EditTextHours);

        holder.mlfb_EditTextMinutes = (EditText) convertView.findViewById(R.id.mlfb_EditTextMinutes);

        holder.body_layout.setVisibility(View.GONE);
        holder.mlfb_RadioTime= (RadioButton) convertView.findViewById(R.id.mlfb_RadioTime);


        holder.mlfb_RadioDays= (RadioButton) convertView.findViewById(R.id.mlfb_RadioDays);


        holder.time_edittext = (LinearLayout) convertView.findViewById(R.id.time_edittext);

        holder.day_true_visibiliyu = (LinearLayout) convertView.findViewById(R.id.day_true_visibiliyu);



        holder.land_type_txt = (LinearLayout) convertView.findViewById(R.id.land_type_txt);
    }

    //TODO seperate the functuonality
    public static class Holder {
        public ImageView ivMachineImage, ivMachineIcon;
        public TextView tvMachineTitle, tvMachineDescription, tvRatePerDay, tvTotalRate, tvBookingDateNTime, piockUp;
        public LinearLayout lyOfWithFuel, lyOfWithoutFuel, lyOfWithDriver, lyOfWithoutDriver,card_view,
                lyOfLicense, lyOfPickupRequired_NEW, lyOfUpButton;
        public ImageButton ibDown, ibUp;
        public RadioGroup rgDateNTime;
        public RadioButton rbDays, rbTime;
        public EditText etBookingDays, etBookingHours, etBookingMinutes, etLandArea, etTreesToClimb, etTreesToClean, etCopra;
        public CheckBox cbDry, cbWet, cbLumpy, cbLicense;
        public Button bBookNow, bPlanNow;
        String dayorhouralue = "0";
        LinearLayout day_visibility, visible_land_area;
        String selectedDate, selectedTime, totalAmount;
        String fuelFlag = "NotSet", driverFlag = "NotSet";
        LinearLayout vehicledetails;
        RadioButton mlfb_RadioTime,mlfb_RadioDays;



        LinearLayout inof_imageviewss,land_type_txt,header_layout,body_layout,date_and_time_layout_id,radio_button_time,lyOfCoprasssss,lyOfTreessss,time_edittext,day_true_visibiliyu;


        EditText mlfb_EditTextHours,mlfb_EditTextMinutes,mlfb_EditTextDays;
        ImageView inof_imageview;


    }

    @SuppressLint("ValidFragment")
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        Holder holder;

        public SelectDateFragment(Holder finalHolder) {
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
        Holder holder;

        public SelectTimeFragment(Holder holder) {
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
                    holder.selectedTime = hour + ":" + "0" + minute + " AM";
                } else {
                    holder.selectedTime = hour + ":" + minute + " AM";
                }
            } else {
                hour = hour - 12;
                if (minute < 10) {
                    holder.selectedTime = hour + ":" + "0" + minute + " PM";
                } else {
                    holder.selectedTime = hour + ":" + minute + " PM";
                }
            }
            String dateNTime = holder.selectedDate + " " + holder.selectedTime;
            Log.e("VJHVJH",dateNTime);
            holder.tvBookingDateNTime.setText(dateNTime);


        }
    }
}
