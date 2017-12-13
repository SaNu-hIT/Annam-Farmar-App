//package com.sft.annam.Adapter;
//
//import android.annotation.SuppressLint;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.app.TimePickerDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.sft.annam.Fragment.BookNow_Confirm;
//import com.sft.annam.Model.BookNow_TempData_Model;
//import com.sft.annam.Model.Const_Model;
//import com.sft.annam.Model.MachineListModel_Booking;
//import com.sft.annam.R;
//import com.sft.annam.Utilities.Utilities;
//import com.squareup.picasso.Picasso;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Calendar;
//
///**
// * Created by JESNA on 7/13/2016.
// */
//public class Machine_book_List_Adapter_old extends BaseAdapter {
//
//    private static LayoutInflater inflater = null;
//    FragmentActivity context;
//    boolean flagloc = true;
//    ArrayList<MachineListModel_Booking> machinreis_model = new ArrayList<MachineListModel_Booking>();
//    BookNow_TempData_Model tempData_model;
//    boolean[] hiddenStateON;
//    boolean day, land_area, no_of_trees_toclimb, no_of_trees_to_clean, with_fuel, without_fuel, with_driver, without_friver, coconut_in_kg, pestiside_in_kg;
//
//
//    public Machine_book_List_Adapter_old(FragmentActivity activity,
//                                         ArrayList<MachineListModel_Booking> machineries_model_array,
//                                         BookNow_TempData_Model tempData_model) {
//
//        context = activity;
//        this.machinreis_model = machineries_model_array;
//        this.tempData_model = tempData_model;
//        inflater = (LayoutInflater) context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        hiddenStateON = new boolean[machineries_model_array.size()];
//    }
//
//    @Override
//    public int getCount() {
//        return machinreis_model.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        Holder holder = null;
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.machine_list_for_book, parent, false);
//            holder = new Holder();
//            initViews(holder, convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (Holder) convertView.getTag();
//        }
//
//        final Holder myholder = holder;
//
//        setHiddenLayout(myholder, position);
//
//        setItemData(myholder, position);
//        setRadioGroup(myholder);
//        setQuantityLayout(myholder);
//        setPickupRequired(myholder);
//
//        setFuelNDriver(myholder);
//
//
//        setListeners(myholder, position);
//
//
//
//
//        return convertView;
//    }
//
//    private void setListeners(final Holder myholder, final int position) {
//
//        myholder.tvBookingDateNTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setBookingDateNTime(myholder);
//            }
//        });
//
//
//        myholder.bBookNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
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
//                    String strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes, strTotalLandArea = null, strTreesToClimb = null, strTreesToClean = null, strTotalCopra = null;
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
//                    strTotalAmount = myholder.totalAmount;
//                    strMachineImageURL = machinreis_model.get(position).getMachine_image();
//
//                    strBookingDate = myholder.selectedDate;
//                    strBookingTime = myholder.selectedTime;
//
//
//
//
//
//                    switch (myholder.dayorhouralue) {
//                        case "0":
//                            strDayOrHour = "Day";
//                            strDays = myholder.etBookingDays.getText().toString();
//                            strHours = null;
//                            strMinutes = null;
//                            break;
//                        case "1":
//                            strDayOrHour = "Hour";
//                            strDays = null;
//                            strHours = myholder.etBookingHours.getText().toString();
//                            strMinutes = myholder.etBookingMinutes.getText().toString();
//                            break;
//                        default:
//                            strDayOrHour = null;
//                            strDays = null;
//                            strHours = null;
//                            strMinutes = null;
//                            break;
//                    }
//
//                    switch (tempData_model.getQuantity()) {
//                        case "Acres":
//                            strTotalLandArea = myholder.etLandArea.getText().toString();
//                            bDry = myholder.cbDry.isChecked();
//                            bWet = myholder.cbWet.isChecked();
//                            bLumpy = myholder.cbLumpy.isChecked();
//                            break;
//                        case "Trees":
//                            strTreesToClean = myholder.etTreesToClean.getText().toString();
//                            strTreesToClimb = myholder.etTreesToClimb.getText().toString();
//                            break;
//                        case "Copras":
//                            strTotalCopra = myholder.etCopra.getText().toString();
//                            break;
//                    }
//
//
//                    bWithFuel = myholder.fuelFlag.equals("WithFuel");
//                    bWithDriver = myholder.driverFlag.equals("WithDriver");
//
//
//                    bLicense = bWithDriver && myholder.cbLicense.isChecked();
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
//                    bundle.putString("TotalAmount", strTotalAmount);
//                    bundle.putString("BookingDate", strBookingDate);
//                    bundle.putString("BookingTime", strBookingTime);
//                    bundle.putString("DayOrHour", strDayOrHour);
//                    assert strDayOrHour != null;
//                    if (strDayOrHour.equals("Day")) {
//                        bundle.putString("Days", strDays);
//                    } else if (strDayOrHour.equals("Hour")) {
//                        bundle.putString("Hours", strHours);
//                        bundle.putString("Minutes", strMinutes);
//                    }
//                    bundle.putString("Quantity", tempData_model.getQuantity());
//                    switch (tempData_model.getQuantity()) {
//                        case "Acres":
//                            bundle.putString("TotalLandArea", strTotalLandArea);
//                            bundle.putBoolean("Dry", bDry);
//                            bundle.putBoolean("Wet", bWet);
//                            bundle.putBoolean("Lumpy", bLumpy);
//                            break;
//                        case "Trees":
//                            bundle.putString("TreesToClimb", strTreesToClimb);
//                            bundle.putString("TreesToClean", strTreesToClean);
//                            break;
//                        case "Copras":
//                            bundle.putString("TotalCopra", strTotalCopra);
//                            break;
//                    }
//                    bundle.putBoolean("WithFuel", bWithFuel);
//                    bundle.putBoolean("WithDriver", bWithDriver);
//
//                    //Const_Model.setMachine_name(machinreis_model.get(position).getMachine_type());
//                    //Const_Model.setMachine_details(machinreis_model.get(position).getMachine_description());
//
//                    Fragment fragment = new BookNow_Confirm();
//                    fragment.setArguments(bundle);
//                    Utilities.getInstance(context).changeHomeFragment(
//                            fragment, "BookNow_Confirm", context);
//                } else {
//                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        myholder.bPlanNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (validate(myholder)) {
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
//                    String strBookingDate, strBookingTime, strDayOrHour, strDays, strHours, strMinutes, strTotalLandArea = null, strTreesToClimb = null, strTreesToClean = null, strTotalCopra = null;
//                    boolean bDry = false, bWet = false, bLumpy = false, bWithFuel, bWithDriver;
//
//                    strBookOrPlan = "PLAN";
//
//                    strMachineName = machinreis_model.get(position).getMachine_name();
//                    strMachineId = tempData_model.getMachine_id();
//                    strMachineTypeName = machinreis_model.get(position).getMachine_type();
//                    strMachineTypeId = machinreis_model.get(position).getMachine_id();
//                    strPickupRequired = tempData_model.getPickupRequired();
//                    strRatePerHour = machinreis_model.get(position).getMachine_rate_hour();
//                    strTotalAmount = myholder.totalAmount;
//                    strMachineImageURL = machinreis_model.get(position).getMachine_image();
//
//                    strBookingDate = myholder.selectedDate;
//                    strBookingTime = myholder.selectedTime;
//                    switch (myholder.dayorhouralue) {
//                        case "0":
//                            strDayOrHour = "Day";
//                            strDays = myholder.etBookingDays.getText().toString();
//                            strHours = null;
//                            strMinutes = null;
//                            break;
//                        case "1":
//                            strDayOrHour = "Hour";
//                            strDays = null;
//                            strHours = myholder.etBookingHours.getText().toString();
//                            strMinutes = myholder.etBookingMinutes.getText().toString();
//                            break;
//                        default:
//                            strDayOrHour = null;
//                            strDays = null;
//                            strHours = null;
//                            strMinutes = null;
//                            break;
//                    }
//
//                    switch (tempData_model.getQuantity()) {
//                        case "Acres":
//                            strTotalLandArea = myholder.etLandArea.getText().toString();
//                            bDry = myholder.cbDry.isChecked();
//                            bWet = myholder.cbWet.isChecked();
//                            bLumpy = myholder.cbLumpy.isChecked();
//                            break;
//                        case "Trees":
//                            strTreesToClean = myholder.etTreesToClean.getText().toString();
//                            strTreesToClimb = myholder.etTreesToClimb.getText().toString();
//                            break;
//                        case "Copras":
//                            strTotalCopra = myholder.etCopra.getText().toString();
//                            break;
//                    }
//
//
//                    bWithFuel = myholder.fuelFlag.equals("WithFuel");
//                    bWithDriver = myholder.driverFlag.equals("WithDriver");
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
//                    bundle.putString("TotalAmount", strTotalAmount);
//                    bundle.putString("BookingDate", strBookingDate);
//                    bundle.putString("BookingTime", strBookingTime);
//                    bundle.putString("DayOrHour", strDayOrHour);
//                    assert strDayOrHour != null;
//                    if (strDayOrHour.equals("Day")) {
//                        bundle.putString("Days", strDays);
//                    } else if (strDayOrHour.equals("Hour")) {
//                        bundle.putString("Hours", strHours);
//                        bundle.putString("Minutes", strMinutes);
//                    }
//                    bundle.putString("Quantity", tempData_model.getQuantity());
//                    switch (tempData_model.getQuantity()) {
//                        case "Acres":
//                            bundle.putString("TotalLandArea", strTotalLandArea);
//                            bundle.putBoolean("Dry", bDry);
//                            bundle.putBoolean("Wet", bWet);
//                            bundle.putBoolean("Lumpy", bLumpy);
//                            break;
//                        case "Trees":
//                            bundle.putString("TreesToClimb", strTreesToClimb);
//                            bundle.putString("TreesToClean", strTreesToClean);
//                            break;
//                        case "Copras":
//                            bundle.putString("TotalCopra", strTotalCopra);
//                            break;
//                    }
//                    bundle.putBoolean("WithFuel", bWithFuel);
//                    bundle.putBoolean("WithDriver", bWithDriver);
//
//                    Fragment fragment = new BookNow_Confirm();
//
//                    fragment.setArguments(bundle);
//                    Utilities.getInstance(context).changeHomeFragment(
//                            fragment, "BookNow_Confirm", context);
//
//
//                } else {
//                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        myholder.etBookingDays.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    int rateperday = Integer.parseInt(machinreis_model.get(position).getMachine_rate_perday());
//                    String strdays = myholder.etBookingDays.getText().toString();
//                    int days = 0;
//                    if (strdays.equals(""))
//                        days = 0;
//                    else
//                        days = Integer.parseInt(strdays);
//                    int total = rateperday * days;
//                    String temp = "₹" + total + "\nTotal";
//                    myholder.tvTotalRate.setText(temp);
//                }
//            }
//        });
//        myholder.etBookingMinutes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    int rate = Integer.parseInt(machinreis_model.get(position).getMachine_rate_hour());
//                    String strHrs = myholder.etBookingHours.getText().toString();
//                    String strMints = myholder.etBookingMinutes.getText().toString();
//                    int hours = 0;
//                    int mints = 0;
//                    if (strHrs.equals(""))
//                        hours = 0;
//                    else
//                        hours = Integer.parseInt(strHrs);
//                    if (strMints.equals(""))
//                        mints = 0;
//                    else
//                        mints = Integer.parseInt(strMints);
//                    int total = rate * (hours + (mints / 60));
//                    myholder.totalAmount = String.valueOf(total);
//                    String temp = "₹" + total + "\nTotal";
//                    myholder.tvTotalRate.setText(temp);
//                }
//            }
//        });
//        myholder.etBookingHours.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    String hours_txt = myholder.etBookingHours.getText().toString();
//                    String minute_txt = myholder.etBookingMinutes.getText().toString();
//                    int hours = 0;
//                    int mints = 0;
//
//                    if (!hours_txt.equals("")) {
//                        hours = Integer.parseInt(myholder.etBookingHours.getText().toString());
//                    }
//                    if (!minute_txt.equals("")) {
//                        mints = Integer.parseInt(myholder.etBookingMinutes.getText().toString());
//                    }
//
//
//                    int rate = Integer.parseInt(machinreis_model.get(position).getMachine_rate_hour());
////                    int hours = Integer.parseInt(myholder.etBookingHours.getText().toString());
////                    int mints = Integer.parseInt(myholder.etBookingMinutes.getText().toString());
//                    if (hours != 0 && mints != 0) {
//
//                        int total = rate * (hours + (mints / 60));
//                        myholder.totalAmount = String.valueOf(total);
//                        String temp = "₹" + total + "\nTotal";
//                        myholder.tvTotalRate.setText(temp);
//                    }
//                }
//            }
//        });
//    }
//
//    private boolean validate(Holder myholder) {
//        boolean flag = true;
//        boolean nullFocusFlag = false;
//        View focusView = null;
//
//        String strBookingDate, strDays, strHours, strMinutes, strTotalLandArea, strTreesToClean, strTreesToClimb, strTotalCopra;
//        boolean bDry, bWet, bLumpy, bFuel, bDriver, bWithoutDriver, bLicense;
//
//        strBookingDate = myholder.tvBookingDateNTime.getText().toString();
//
//        bFuel = myholder.fuelFlag.equals("WithFuel") || myholder.fuelFlag.equals("WithoutFuel");
//
//        bDriver = myholder.driverFlag.equals("WithDriver") || myholder.driverFlag.equals("WithoutDriver");
//
//        bWithoutDriver = myholder.driverFlag.equals("WithoutDriver");
//
//        bLicense = myholder.cbLicense.isChecked();
//
//
//        if (strBookingDate.equalsIgnoreCase("Enter Date-Time")) {
//            myholder.tvBookingDateNTime.setError(context.getString(R.string.error_field_required));
//            focusView = myholder.tvBookingDateNTime;
//            flag = false;
//        } else {
//            myholder.tvBookingDateNTime.setError(null);
//        }
////        if(day) {
////            if (myholder.dayorhouralue.equals("0")) {
////                strDays = myholder.etBookingDays.getText().toString();
////                if (TextUtils.isEmpty(strDays)) {
////                    myholder.etBookingDays.setError(context.getString(R.string.error_field_required));
////                    focusView = myholder.etBookingDays;
////                    flag = false;
////                } else {
////                    myholder.etBookingDays.setError(null);
////                }
////            } else {
////                strHours = myholder.etBookingHours.getText().toString();
////                strMinutes = myholder.etBookingMinutes.getText().toString();
////                if (TextUtils.isEmpty(strHours)) {
////                    myholder.etBookingHours.setError(context.getString(R.string.error_field_required));
////                    focusView = myholder.etBookingHours;
////                    flag = false;
////                } else {
////                    myholder.etBookingHours.setError(null);
////                }
////                if (TextUtils.isEmpty(strMinutes)) {
//////                myholder.etBookingMinutes.setError(context.getString(R.string.error_field_required));
////                    strMinutes = "0";
//////                focusView = myholder.etBookingMinutes;
//////                flag = false;
////                } else {
//////                myholder.etBookingMinutes.setError(null);
////                }
////            }
////        }
//        switch (tempData_model.getQuantity()) {
//            case "Acres":
//                strTotalLandArea = myholder.etLandArea.getText().toString();
//                if(land_area) {
//                    if (TextUtils.isEmpty(strTotalLandArea)) {
//                        myholder.etLandArea.setError(context.getString(R.string.error_field_required));
//                        focusView = myholder.etLandArea;
//                        flag = false;
//                    } else {
//                        myholder.etLandArea.setError(null);
//                    }
//                }
//                bDry = myholder.cbDry.isChecked();
//                bWet = myholder.cbWet.isChecked();
//                bLumpy = myholder.cbLumpy.isChecked();
//
//                if (!bDry && !bWet && !bLumpy) {
//                    myholder.cbDry.setError(context.getString(R.string.error_field_required));
//                    myholder.cbWet.setError(context.getString(R.string.error_field_required));
//                    myholder.cbLumpy.setError(context.getString(R.string.error_field_required));
//                    focusView = myholder.cbDry;
//                    flag = false;
//                } else {
//                    myholder.cbDry.setError(null);
//                    myholder.cbWet.setError(null);
//                    myholder.cbLumpy.setError(null);
//                }
//                break;
//            case "Trees":
//                strTreesToClean = myholder.etTreesToClean.getText().toString();
//                strTreesToClimb = myholder.etTreesToClimb.getText().toString();
//                if (no_of_trees_to_clean)
//                {
//                    if (TextUtils.isEmpty(strTreesToClean)) {
//                        myholder.etTreesToClean.setError(context.getString(R.string.error_field_required));
//                        focusView = myholder.etTreesToClean;
//                        flag = false;
//                    } else {
//                        myholder.etTreesToClean.setError(null);
//                    }
//        }
//        if(no_of_trees_toclimb) {
//
//            if (TextUtils.isEmpty(strTreesToClimb)) {
//                myholder.etTreesToClimb.setError(context.getString(R.string.error_field_required));
//                focusView = myholder.etTreesToClimb;
//                flag = false;
//            } else {
//                myholder.etTreesToClimb.setError(null);
//            }
//        }
//                break;
//            case "Copras":
//                strTotalCopra = myholder.etCopra.getText().toString();
//                if(coconut_in_kg) {
//                    if (TextUtils.isEmpty(strTotalCopra)) {
//                        myholder.etCopra.setError(context.getString(R.string.error_field_required));
//                        focusView = myholder.etCopra;
//                        flag = false;
//                    } else {
//                        myholder.etCopra.setError(null);
//                    }
//                }
//                break;
//        }
//
////        if (flagloc) {
////            if (!bFuel || !bDriver) {
////                Toast.makeText(context, "Please select Fuel & Driver mode", Toast.LENGTH_SHORT).show();
////                nullFocusFlag = true;
////                flag = false;
////            }
////        }
//        if(without_friver) {
//            if (bWithoutDriver) {
//                if (!bLicense) {
//                    myholder.cbLicense.setError(context.getString(R.string.error_field_required));
//                    focusView = myholder.cbLicense;
//                    flag = false;
//                } else {
//                    myholder.cbLicense.setError(null);
//                }
//            }
//        }
//        if (!flag && !nullFocusFlag) {
//            focusView.requestFocus();
//        }
//        return flag;
//    }
//
//    private void setHiddenLayout(final Holder myholder, final int position) {
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
//                myholder.lyDownButton.setVisibility(View.GONE);
//                myholder.lyToHide.setVisibility(View.VISIBLE);
//                hiddenStateON[position] = true;
//            }
//        });
//
//        myholder.ibUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myholder.lyToHide.setVisibility(LinearLayout.GONE);
//                myholder.lyDownButton.setVisibility(LinearLayout.VISIBLE);
//                hiddenStateON[position] = false;
//            }
//        });
//
//
//
//
//    }
//
//    private void setFuelNDriver(final Holder myholder) {
//
//        myholder.lyOfLicense.setVisibility(View.GONE);
//
//        myholder.lyOfWithDriver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myholder.lyOfWithDriver.setBackgroundResource(R.drawable.fill_light_green);
//                myholder.lyOfWithoutDriver.setBackgroundResource(R.drawable.book_a_service_bg);
//                myholder.lyOfLicense.setVisibility(View.GONE);
//                myholder.driverFlag = "WithDriver";
//                myholder.piockUp.setVisibility(View.GONE);
//
//            }
//        });
//
//        myholder.lyOfWithoutDriver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myholder.lyOfWithDriver.setBackgroundResource(R.drawable.book_a_service_bg);
//                myholder.lyOfWithoutDriver.setBackgroundResource(R.drawable.fill_light_green);
//                myholder.lyOfLicense.setVisibility(View.VISIBLE);
//                myholder.driverFlag = "WithoutDriver";
//                myholder.piockUp.setVisibility(View.VISIBLE);
//            }
//        });
//
//        myholder.lyOfWithFuel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myholder.lyOfWithFuel.setBackgroundResource(R.drawable.fill_light_green);
//                myholder.lyOfWithoutFuel.setBackgroundResource(R.drawable.book_a_service_bg);
//                myholder.fuelFlag = "WithFuel";
//            }
//        });
//
//        myholder.lyOfWithoutFuel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myholder.lyOfWithFuel.setBackgroundResource(R.drawable.book_a_service_bg);
//                myholder.lyOfWithoutFuel.setBackgroundResource(R.drawable.fill_light_green);
//                myholder.fuelFlag = "WithoutFuel";
//            }
//        });
//    }
//
//    private void setPickupRequired(Holder myholder) {
//        if (tempData_model.getPickupRequired().equals("true"))
//            myholder.lyOfPickupRequired.setVisibility(View.VISIBLE);
//        else
//            myholder.lyOfPickupRequired.setVisibility(View.GONE);
//    }
//
//    private void setQuantityLayout(final Holder myholder) {
//        if (tempData_model.getQuantity().equalsIgnoreCase("Acres")) {
//            myholder.lyOfLandArea.setVisibility(View.VISIBLE);
//            myholder.lyOfTrees.setVisibility(View.GONE);
//            myholder.lyOfCopra.setVisibility(View.GONE);
//            myholder.cbDry.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    //is chkIos checked?
//                    if (((CheckBox) v).isChecked()) {
//                        myholder.cbWet.setChecked(false);
//                        myholder.cbLumpy.setChecked(false);
//                    }
//                }
//            });
//            myholder.cbWet.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    //is chkIos checked?
//                    if (((CheckBox) v).isChecked()) {
//                        myholder.cbDry.setChecked(false);
//                        myholder.cbLumpy.setChecked(false);
//                    }
//                }
//            });
//            myholder.cbLumpy.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    //is chkIos checked?
//                    if (((CheckBox) v).isChecked()) {
//                        myholder.cbDry.setChecked(false);
//                        myholder.cbWet.setChecked(false);
//                    }
//                }
//            });
//        } else if (tempData_model.getQuantity().equalsIgnoreCase("Trees")) {
//            myholder.lyOfLandArea.setVisibility(View.GONE);
////            myholder.lyOfTrees.setVisibility(View.VISIBLE);
//            myholder.lyOfCopra.setVisibility(View.GONE);
//        } else if (tempData_model.getQuantity().equalsIgnoreCase("Copra")) {
//            myholder.lyOfLandArea.setVisibility(View.GONE);
//            myholder.lyOfTrees.setVisibility(View.GONE);
//            myholder.lyOfCopra.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void setItemData(Holder myholder, int position)
//    {
//        String machanename = machinreis_model.get(position).getMachine_name();
//        if (machanename.equals("coconut tree climber")) {
//            flagloc = false;
//            myholder.vehicledetails.setVisibility(View.GONE);
//        }
//        String strMachineName = machinreis_model.get(position).getMachine_type() + "\n" + machinreis_model.get(position).getMachine_name();
//        myholder.tvMachineTitle.setText(strMachineName);
//
//        String str_MacTypee = machinreis_model.get(position).getMachine_description();
//        myholder.tvMachineDescription.setText(str_MacTypee);
//        Const_Model.setMachine_details(str_MacTypee);
//
//        String strRatePerDay = "₹" + machinreis_model.get(position).getMachine_rate_perday() + "\n Rate";
//        myholder.tvRatePerDay.setText(strRatePerDay);
//
//        String strRatePerHour = "₹" + machinreis_model.get(position).getMachine_rate_hour() + "\n Total";
//        myholder.tvTotalRate.setText(strRatePerHour);
//
//
//        String imgURL = machinreis_model.get(position).getMachine_image();
//        URI uri = null;
//        try {
//            uri = new URI(imgURL.replaceAll(" ", "%20"));
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        final String imageURL = String.valueOf(uri);
//
//
//        Picasso.with(context)
//                .load(imageURL)
//                .error(R.drawable.tillerharvester)      // optional
//                .resize(400, 400)                        // optional
//                .into(myholder.ivMachineImage);
//
//
//
//
//
//        no_of_trees_toclimb = machinreis_model.get(position).isNo_of_trees_toclimb();
//        no_of_trees_to_clean = machinreis_model.get(position).isNo_of_trees_to_clean();
//        with_fuel = machinreis_model.get(position).isWith_fuel();
//        without_fuel = machinreis_model.get(position).isWithout_fuel();
//        with_driver = machinreis_model.get(position).isWith_driver();
//        without_friver = machinreis_model.get(position).isWithout_friver();
//        coconut_in_kg = machinreis_model.get(position).isCoconut_in_kg();
//        pestiside_in_kg = machinreis_model.get(position).isPestiside_in_kg();
//        day = machinreis_model.get(position).isDay();
//        land_area = machinreis_model.get(position).isLand_area();
//
//
//
//        if (!coconut_in_kg) {
//
//            myholder.lyOfCopra.setVisibility(View.GONE);
//        }
//        if (pestiside_in_kg) {
//
//
//        }
//
//
//        if (!with_driver) {
//            myholder.lyOfWithDriver.setVisibility(View.GONE);
//        }
//        if (!without_friver) {
//
//            myholder.lyOfWithoutDriver.setVisibility(View.GONE);
//        }
//
//        if (!with_fuel) {
//            myholder.lyOfWithFuel.setVisibility(View.GONE);
//        }
//        if (!without_fuel) {
//            myholder.lyOfWithoutFuel.setVisibility(View.GONE);
//
//        }
//
//
//        if (!day) {
//
//            myholder.day_visibility.setVisibility(View.GONE);
//        }
//        if (!land_area) {
//
//
//            myholder.lyOfLandArea.setVisibility(View.GONE);
//
//
//        } else {
//            myholder.lyOfLandArea.setVisibility(View.VISIBLE);
//        }
//
//
//        if (!no_of_trees_toclimb) {
//            myholder.lyOfTrees.setVisibility(View.GONE);
//
//        }
//        if (!no_of_trees_to_clean) {
//            myholder.lyOfTrees.setVisibility(View.GONE);
//
//
//        }
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//    private void setBookingDateNTime(Holder finalHolder) {
//        DialogFragment newFragment = new SelectDateFragment(finalHolder);
//        newFragment.show(context.getSupportFragmentManager(), "DatePicker");
//    }
//
//    private void setRadioGroup(final Holder holder) {
//        holder.rgDateNTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//
//                switch (checkedId) {
//                    case R.id.mlfb_RadioDays:
//                        holder.lyOfDays.setVisibility(LinearLayout.VISIBLE);
//                        holder.lyOfHours.setVisibility(LinearLayout.GONE);
//                        holder.lyOfMinutes.setVisibility(LinearLayout.GONE);
//                        holder.dayorhouralue = "0";
//                        break;
//
//                    case R.id.mlfb_RadioTime:
//                        holder.lyOfHours.setVisibility(LinearLayout.VISIBLE);
//                        holder.lyOfMinutes.setVisibility(LinearLayout.VISIBLE);
//                        holder.lyOfDays.setVisibility(LinearLayout.GONE);
//                        holder.dayorhouralue = "1";
//                        break;
//                }
//            }
//        });
//    }
//
//    private void initViews(Holder holder, View convertView) {
//        holder.ivMachineImage = (ImageView) convertView.findViewById(R.id.mlfb_MachineImage);
//        holder.ivMachineIcon = (ImageView) convertView.findViewById(R.id.mlfb_MachineIcon);
//
//        holder.tvMachineTitle = (TextView) convertView.findViewById(R.id.mlfb_MachineTitle);
//        holder.tvMachineDescription = (TextView) convertView.findViewById(R.id.mlfb_MachineDescription);
//        holder.tvRatePerDay = (TextView) convertView.findViewById(R.id.mlfb_RatePerDay);
//        holder.tvTotalRate = (TextView) convertView.findViewById(R.id.mlfb_TotalRate);
//        holder.tvBookingDateNTime = (TextView) convertView.findViewById(R.id.mlfb_BookingDateNTime);
//
//        holder.lyDownButton = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfDownButton);
//        holder.lyToHide = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutToHide);
//        holder.lyOfDays = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfDays);
//        holder.lyOfHours = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfHours);
//        holder.lyOfMinutes = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfMinutes);
//        holder.lyOfLandArea = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfLandArea);
//        holder.lyOfTrees = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfTrees);
//        holder.lyOfCopra = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfCopra);
//        holder.lyOfWithFuel = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithFuel);
//        holder.lyOfWithoutFuel = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithoutFuel);
//        holder.lyOfWithDriver = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithDriver);
//        holder.lyOfWithoutDriver = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfWithoutDriver);
//        holder.lyOfLicense = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfLicense);
//        holder.lyOfPickupRequired = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfPickupRequired);
//        holder.lyOfUpButton = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfUpButton);
//        holder.vehicledetails = (LinearLayout) convertView.findViewById(R.id.vehicledetails);
//        holder.ibDown = (ImageButton) convertView.findViewById(R.id.mlfb_ButtonDown);
//        holder.ibUp = (ImageButton) convertView.findViewById(R.id.mlfb_ButtonUp);
//
//        holder.rgDateNTime = (RadioGroup) convertView.findViewById(R.id.mlfb_RadioGroupDateNTime);
//
//        holder.rbDays = (RadioButton) convertView.findViewById(R.id.mlfb_RadioDays);
//        holder.rbTime = (RadioButton) convertView.findViewById(R.id.mlfb_RadioTime);
//
//        holder.etBookingDays = (EditText) convertView.findViewById(R.id.mlfb_EditTextDays);
//        holder.etBookingHours = (EditText) convertView.findViewById(R.id.mlfb_EditTextHours);
//        holder.etBookingMinutes = (EditText) convertView.findViewById(R.id.mlfb_EditTextMinutes);
//        holder.etLandArea = (EditText) convertView.findViewById(R.id.mlfb_EditTextLandArea);
//        holder.etTreesToClimb = (EditText) convertView.findViewById(R.id.mlfb_EditTextTreesToClimb);
//        holder.etTreesToClean = (EditText) convertView.findViewById(R.id.mlfb_EditTextTreesToClean);
//        holder.etCopra = (EditText) convertView.findViewById(R.id.mlfb_EditTextCopra);
//
//        holder.cbDry = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxDry);
//        holder.cbWet = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxWet);
//        holder.cbLumpy = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxLumpy);
//        holder.cbLicense = (CheckBox) convertView.findViewById(R.id.mlfb_CheckBoxLicense);
//
//        holder.bBookNow = (Button) convertView.findViewById(R.id.mlfb_ButtonBookNow);
//        holder.bPlanNow = (Button) convertView.findViewById(R.id.mlfb_ButtonPlanNow);
//        holder.piockUp = (TextView) convertView.findViewById(R.id.pickup_id);
//        //------------------------ END ---------------------------
//
//
//        holder.visible_land_area = (LinearLayout) convertView.findViewById(R.id.mlfb_LayoutOfTrees);
//        holder.day_visibility = (LinearLayout) convertView.findViewById(R.id.day_visibility);
//    }
//
//    //TODO seperate the functuonality
//    public static class Holder {
//        public ImageView ivMachineImage, ivMachineIcon;
//        public TextView tvMachineTitle, tvMachineDescription, tvRatePerDay, tvTotalRate, tvBookingDateNTime, piockUp;
//        public LinearLayout lyDownButton, lyToHide, lyOfDays, lyOfHours, lyOfMinutes, lyOfLandArea, lyOfTrees,
//                lyOfCopra, lyOfWithFuel, lyOfWithoutFuel, lyOfWithDriver, lyOfWithoutDriver,
//                lyOfLicense, lyOfPickupRequired, lyOfUpButton;
//        public ImageButton ibDown, ibUp;
//        public RadioGroup rgDateNTime;
//        public RadioButton rbDays, rbTime;
//        public EditText etBookingDays, etBookingHours, etBookingMinutes, etLandArea, etTreesToClimb, etTreesToClean, etCopra;
//        public CheckBox cbDry, cbWet, cbLumpy, cbLicense;
//        public Button bBookNow, bPlanNow;
//        String dayorhouralue = "0";
//        LinearLayout day_visibility, visible_land_area;
//        String selectedDate, selectedTime, totalAmount;
//        String fuelFlag = "NotSet", driverFlag = "NotSet";
//        LinearLayout vehicledetails;
//    }
//
//    @SuppressLint("ValidFragment")
//    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//        Holder holder;
//
//        public SelectDateFragment(Holder finalHolder) {
//            this.holder = finalHolder;
//        }
//
//        @Override
//        @NonNull
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar calendar = Calendar.getInstance();
//            int yy = calendar.get(Calendar.YEAR);
//            int mm = calendar.get(Calendar.MONTH);
//            int dd = calendar.get(Calendar.DAY_OF_MONTH);
//            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
//        }
//
//        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
//            populateSetDate(yy, mm + 1, dd);
//        }
//
//        public void populateSetDate(int year, int month, int day) {
//            //txtFromDate.setText(day+"/"+month+"/"+year);
//            holder.selectedDate = day + "/" + month + "/" + year;
//            DialogFragment newFragment = new SelectTimeFragment(holder);
//            newFragment.show(getFragmentManager(), "TimePicker");
//        }
//
//    }
//
//    @SuppressLint("ValidFragment")
//    private class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
//        Holder holder;
//
//        public SelectTimeFragment(Holder holder) {
//            this.holder = holder;
//        }
//
//        @Override
//        @NonNull
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            Calendar now = Calendar.getInstance();
//            return new TimePickerDialog(getActivity(), this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
//        }
//
//        @Override
//        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
///*            Calendar now = Calendar.getInstance();
//            if (hour > now.get(Calendar.HOUR_OF_DAY)){
//                populateSetTime(hour, minute);
//            } else if (hour == now.get(Calendar.HOUR_OF_DAY)) {
//                if (minute > now.get(Calendar.MINUTE)){
//                    populateSetTime(hour, minute);
//                } else {
//                    //txtFromTime.setText("Please select a valid time");
//                    Log.e("My Log","Please select a valid time");
//                }
//            } else {
//                //txtFromTime.setText("Please select a valid time");
//                Log.e("My Log","Please select a valid time");
//            }*/
//            populateSetTime(hour, minute);
//        }
//
//        public void populateSetTime(int hour, int minute) {
//            if (hour < 13) {
//                if (minute < 10) {
//                    holder.selectedTime = hour + ":" + "0" + minute + " AM";
//                } else {
//                    holder.selectedTime = hour + ":" + minute + " AM";
//                }
//            } else {
//                hour = hour - 12;
//                if (minute < 10) {
//                    holder.selectedTime = hour + ":" + "0" + minute + " PM";
//                } else {
//                    holder.selectedTime = hour + ":" + minute + " PM";
//                }
//            }
//            String dateNTime = holder.selectedDate + "\n" + holder.selectedTime;
//            holder.tvBookingDateNTime.setText(dateNTime);
//            //Constants.setSelectedDate(selectedDate);
//            //Constants.setSelectedTime(selectedTime);
//
//        }
//    }
//}
