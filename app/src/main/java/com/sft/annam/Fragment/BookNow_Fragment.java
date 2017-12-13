package com.sft.annam.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.github.mrengineer13.snackbar.SnackBar;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.sft.annam.Adapter.MachineCategoriesAdapter;
import com.sft.annam.Connection.HttpRequestHelperForMachineListForBooking;
import com.sft.annam.Connection.HttpRequestHelperForMachine_types;
import com.sft.annam.Connection.HttpRequestHelperForMachine_types_book_later;
import com.sft.annam.Connection.HttprequestForKarshgasenaClick;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.DataBaseHelper.DatabaseHelperFor_Book;
import com.sft.annam.Home;
import com.sft.annam.Interfaces.LocationUpdate;
import com.sft.annam.LocationSelect;
import com.sft.annam.Model.BookNowFrag_model;
import com.sft.annam.Model.Book_Confirm_Constant;
import com.sft.annam.Model.Const_Model;
import com.sft.annam.Model.Constants;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.MachineCategories;
import com.sft.annam.Model.Machine_types_model;
import com.sft.annam.R;
import com.sft.annam.TextView_Lato;
import com.sft.annam.Utilities.LocationListner;
import com.sft.annam.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by JESNA on 7/12/2016.
 */
public class BookNow_Fragment extends Fragment implements MachineCategoriesAdapter.ItemClickCallback {

    private final String PREFS_TEMP_DATA = "TempData";

    private MachineCategoriesAdapter adapter;
    private List<MachineCategories> machineCategoriesList;

    String machine_type, machine_id, quantity, pickupRequired;
    Location_MOdel location_mOdel;
    String location;
    ArrayList<Machine_types_model> machine_types_models_array = new ArrayList<Machine_types_model>();

    String latitude, longtude;
    private Location_MOdel loc_model;
    TextView_Lato myLocation;
    private Object mGoogleApiClient;
     View rootView;
    private LocationListner gps;
    LocationUpdate locationUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView recyclerView;
        rootView = inflater.inflate(R.layout.booknow_screen, container, false);

        myLocation = (TextView_Lato) rootView.findViewById(R.id.myLocation);


        CheckNetwork(rootView);



        Bundle machinetype_bundle = getArguments();

        if (machinetype_bundle != null) {
            machine_types_models_array = machinetype_bundle.getParcelableArrayList("machine_types");
        } else {


            setLocation(rootView);


            addLocNBookNow();
        }

        getActivity().setTitle("Annam");

        Const_Model.booklaterFlag = false;



        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        machineCategoriesList = new ArrayList<>();
        adapter = new MachineCategoriesAdapter(getActivity(), machineCategoriesList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

        prepareMachineCategoryCards();
        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LocationSelect.class);
                startActivity(intent);
            }
        });


        return rootView;
    }


    private void setLocation(View rootView) {



        location_mOdel= DataBaseHelperFor_location.getProfileOffline(getActivity());
        Log.e( "setLocation: ", ""+location_mOdel);
        if(location_mOdel!=null) {
            String place = location_mOdel.getPlace();
            String state = location_mOdel.getState();
            String contry = location_mOdel.getCountry();
            latitude = location_mOdel.getLatitude();
            longtude = location_mOdel.getLongitude();
            location = place + "," + state + "," + contry;
            myLocation.setText(""+location);
            Log.e("location", "setLocation: "+location );
        }else
        {

//            setLocationManualy();
//            if(rootView!=null) {
//                Snackbar snackbar = Snackbar
//                        .make(rootView.getRootView(), "Please select your location", Snackbar.LENGTH_LONG)
//                        .setAction("OK", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Log.e("onClick: ",
//                                        "Farmar location");
//                            }
//                        });
//
//                snackbar.show();
//            }
//            else
//            {
//                Log.e( "setLocation: ","Cant pass" );
//            }

            Toast.makeText(getActivity(), "Set location manualy", Toast.LENGTH_SHORT).show();

        }
    }

    private void setLocationManualy() {

showGPSDisabledAlertToUser();

    }

    private void validateNMovetoKarshgasenaselect() {


        HttprequestForKarshgasenaClick httprequestForKarshgasenaClick=new HttprequestForKarshgasenaClick();
        httprequestForKarshgasenaClick.KarshgasenaClick(getActivity());
    }

    private void validateNMovetoMachineSelect() {
try {
    saveDataToPrefs();
    FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
    String fechid = fetchId.getUser_id();
    if (Utilities.getInstance(getActivity()).isNetAvailable()) {

        HttpRequestHelperForMachineListForBooking httptomachinelist = new HttpRequestHelperForMachineListForBooking();
         httptomachinelist.machinetypesforlistforBooking(getActivity(), fechid, machine_id);

    } else {
           Toast.makeText(getActivity(), "network is not available", Toast.LENGTH_SHORT).show();
    }
}catch(Exception e){
    Toast.makeText(getActivity(), "exception"+e.getMessage(),Toast.LENGTH_LONG).show();
}
    }

    private void saveDataToPrefs() {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_TEMP_DATA, Context.MODE_PRIVATE).edit();
        editor.putBoolean("hasData", true);
        editor.putString("fullLocation",myLocation.getText().toString());
        editor.putString("latitude",latitude);
        editor.putString("longitude",longtude);
        editor.putString("machine_id",machine_id);
        editor.putString("quantity",quantity);
        editor.putString("pickupRequired",pickupRequired);
        editor.apply();
    }

    //---------------------Added------------------------

    @Override
    public void onResume() {
        super.onResume();
        if(rootView!=null)
setLocation(rootView);
    }

    /**
     * Adding few albums for testing
     */
    private void prepareMachineCategoryCards() {
        int totalMachineTypes = machine_types_models_array.size();
        MachineCategories a;
        for (int i=0;i<totalMachineTypes;i++){
            String ss=machine_types_models_array.get(i).getImage();
            Log.e("IMAGE URL: ",ss );
            a = new MachineCategories(machine_types_models_array.get(i).getMachine_name(),machine_types_models_array.get(i).getImage());
            machineCategoriesList.add(a);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMachineClick(View view, int position) {

        machine_type = machine_types_models_array.get(position).getMachine_name();
        machine_id = machine_types_models_array.get(position).getMachine_type_id();
        quantity = machine_types_models_array.get(position).getQuantitiy();
        Log.e("MACANEID", machine_id);
        Log.e("machine_type", machine_type);


if(machine_id.equals("140")&&machine_type.equals("Karshaka Sena"))
{
    Log.e("onMachineClick: ","Karshgasena click" );

    validateNMovetoKarshgasenaselect();

}else {

    if (machine_types_models_array.get(position).getPickup_required().equals("0"))
        pickupRequired = "true";
    else
        pickupRequired = "false";
    validateNMovetoMachineSelect();
}


    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



    public void addLocNBookNow(){


            if(Utilities.getInstance(getActivity()).isNetAvailable()) {
                HttpRequestHelperForMachine_types machine_types = new HttpRequestHelperForMachine_types();
                machine_types.machine_types(getActivity());
            }


    }


    public void CheckNetwork(View v)
    {
        if(!isNetworkAvailable())

        {
            ShowMessage(v);
        }
    }

    public void ShowMessage(View v)
    {
final Snackbar snackbar = Snackbar
                .make(getView(), "No internet connection,please try again latter", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Snackbar snackbar1 = Snackbar.make(getView(), "Message is restored!", Snackbar.LENGTH_SHORT);
//                        snackbar1.show();

                    }
                });

        snackbar.show();
        Log.e( "ShowMessage: ", "No network connection");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showGPSDisabledAlertToUser(){


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Would you like to enable it!";

        builder.setMessage(message).setTitle("GPS is disabled ?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                getActivity().startActivity(new Intent(action));
                                d.dismiss();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.dismiss();

                            }
                        });
        builder.create().show();

    }

    private void getLocation() {

        Log.e( "getLocation: ", "Invoke ");
        Log.e( "check for gps: ", "enable or disable  ");

        gps = new LocationListner(getActivity());


        if (gps.canGetLocation()) {


            String longitudes = String.valueOf(gps.getLongitude());
            String latitudes = String.valueOf(gps.getLatitude());



//                    Toast.makeText(getApplicationContext(),"Longitude:"+longitudes+"\nLatitude:"+latitudes,Toast.LENGTH_SHORT).show();
        } else {

            gps.showSettingsAlert();
        }
    }





}
