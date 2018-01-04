package com.sft.annam;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mrengineer13.snackbar.SnackBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_Machineries;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Fragment.Bill_Fragment;
import com.sft.annam.Fragment.BookLater_Fragment;
import com.sft.annam.Fragment.BookNow_Confirm;
import com.sft.annam.Fragment.BookNow_Fragment;
import com.sft.annam.Fragment.BookingLater_Confirm_fragment;
import com.sft.annam.Fragment.Booking_Status_Fragment;
import com.sft.annam.Fragment.ChangePassword_Fragment;
import com.sft.annam.Fragment.History_Fragment;
import com.sft.annam.Fragment.Home_Main_Fragment;
import com.sft.annam.Fragment.KarshagasenaBookingConfirm;
import com.sft.annam.Fragment.Karshakasena_Fragment;
import com.sft.annam.Fragment.Location_MOdel;
import com.sft.annam.Fragment.Machine_Booking_List_Fragment;
import com.sft.annam.Fragment.Machineries_Fragment;
import com.sft.annam.Fragment.Profile_Details_Fragment;
import com.sft.annam.Fragment.Profile_Fragment;
import com.sft.annam.Fragment.Profile_Fragment_New;
import com.sft.annam.Interfaces.OnHttpReponseForSbmit_LAterBooking;
import com.sft.annam.Interfaces.OnHttpResponceForKarshgasenaBooking;
import com.sft.annam.Interfaces.OnHttpResponseForChangePassword;
import com.sft.annam.Interfaces.OnHttpResponseForMachineListBooking;
import com.sft.annam.Interfaces.OnHttpResponseForMachineView;
import com.sft.annam.Interfaces.OnHttpResponseForProfile;
import com.sft.annam.Interfaces.OnHttpResponseForSubmitBooking;
import com.sft.annam.Interfaces.OnHttpResponseListenerForMachineTypes;
import com.sft.annam.Interfaces.OnHttpResponseListenerForMachineTypes_booklater;
import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.MachineListModel_Booking;
import com.sft.annam.Model.Machine_types_model;
import com.sft.annam.Model.Machineries_Model;
import com.sft.annam.Model.Profile_View_Model;
import com.sft.annam.Utilities.LocationListner;
import com.sft.annam.Utilities.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class Home extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        OnHttpResponseForProfile,
        OnHttpResponseListenerForMachineTypes,
        OnHttpResponseForMachineView,
        OnHttpResponseForMachineListBooking,
        OnHttpResponseListenerForMachineTypes_booklater,
        OnHttpReponseForSbmit_LAterBooking,
        OnHttpResponseForChangePassword,
        OnHttpResponseForSubmitBooking, OnHttpResponceForKarshgasenaBooking, GoogleApiClient.ConnectionCallbacks, SnackBar.OnMessageClickListener {
    LocationRequest mLocationRequest;
    private LocationListner gps;
    Location_MOdel location_mOdel;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    public static Toolbar toolbar;
    public static NavigationView navigationView;
    public static DrawerLayout drawer;
    Fragment fragment;
    private static final String SHOWCASE_ID = "Home";
    private GoogleApiClient mGoogleApiClient;
    private LatLng latLng;
    private Location_MOdel loc_model;
    private LocationManager locationManager;
    private Location loc;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //  super.onSaveInstanceState(outState);
    }

    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
View vies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annam_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        CheckNetwork();
        final LocationManager manager = (LocationManager)getSystemService    (Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
            Toast.makeText(this, "GPS is disabled!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "GPS is enabled!", Toast.LENGTH_LONG).show();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.isDrawerOpen(GravityCompat.START);
                }
            }
        });
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        Activity currActivity = this;
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.homemaincontainer);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if ((f instanceof Machineries_Fragment)
                || (f instanceof ChangePassword_Fragment)
                || (f instanceof KarshagasenaBookingConfirm)
                || (f instanceof BookNow_Confirm)
                || (f instanceof Machine_Booking_List_Fragment)
                || (f instanceof Profile_Details_Fragment)
                || (f instanceof History_Fragment)
                || (f instanceof Booking_Status_Fragment)
                || (f instanceof Karshakasena_Fragment)
                ) {
            Fragment fragment = new BookNow_Fragment();
            Utilities.getInstance(this).changeHomeFragment(
                    fragment, "BookNow_Fragment", this);

            if (getSupportActionBar() != null) {

            }


        } else if ((f instanceof BookNow_Fragment)) {

            //Intent intent = new Intent(Home.this, MapsActivity.class);
            //startActivity(intent);
            showalert();
            //finish();


        } else if ((f instanceof BookingLater_Confirm_fragment)) {

            showalertCantGoBACk();
        } else {
            super.onBackPressed();

        }
    }

    public void showalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showalertCantGoBACk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Please complete your booking");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Sign out ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                String PREFS_LOGIN_STATUS = "LOGIN";
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.putInt("user_id", 0);
                //editor.putString("name", login_model.getUsername());
                editor.commit();

                Intent intent = new Intent(getBaseContext(), Login_Signup_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+917560812281"));
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return true;
//            }
//            startActivity(intent);

            if (isPermissionGranted()) {
                call_action();
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v("TAG", "Permission is granted");
                    return true;
                } else {

                    Log.v("TAG", "Permission is revoked");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 6);
                    return false;
                }
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 6: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void call_action() {
        String phnum = "+917560812281";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phnum));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            fragment = new BookNow_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "MapsActivity", Home.this);
            // Handle the camera action
/*
        } else if (id == R.id.nav_booknow) {
            if (Utilities.getInstance(Home.this).isNetAvailable()) {

                HttpRequestHelperForMachine_types machine_types = new HttpRequestHelperForMachine_types();
                machine_types.machine_types(Home.this);
            }
            else {
                Toast.makeText(Home.this, "network is not available", Toast.LENGTH_SHORT).show();
            }
*/
        } /*else if (id == R.id.nav_booklater) {
            if (Utilities.getInstance(Home.this).isNetAvailable()) {

                HttpRequestHelperForMachine_types_book_later machine_types_later = new HttpRequestHelperForMachine_types_book_later();
                machine_types_later.machine_types_booklater(Home.this);
            } else {
                Toast.makeText(Home.this, "network is not available", Toast.LENGTH_SHORT).show();
            }


        }*/ else if (id == R.id.nav_machineries) {
            fragment = new Machineries_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "Machineries_Fragment", Home.this);

        }
//        else if (id == R.id.nav_krishisena) {
//            fragment = new Karshakasena_Fragment();
//            Utilities.getInstance(Home.this).changeHomeFragment(
//                    fragment, "Karshakasena_Fragment", Home.this);
//
//        }
        else if (id == R.id.nav_profile) {
            fragment = new Profile_Details_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "Profile_Details_Fragment", Home.this);

        } else if (id == R.id.nav_changepassword) {
            fragment = new ChangePassword_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "ChangePassword_Fragment", Home.this);

        } else if (id == R.id.nav_history) {
            fragment = new History_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "History_Fragment", Home.this);

        } else if (id == R.id.nav_bookingstatus) {
            fragment = new Booking_Status_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "Booking_Status_Fragment", Home.this);

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
            Home.this.finish();
        } else if (id == R.id.nav_logout)
        {
            signOut();

        }else if (id == R.id.bill) {

            fragment = new Bill_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "Booking_Status_Fragment", Home.this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onHttpSuccessfulResponseProfile(String responseBody, boolean responseStatus, String responseResultMsg,
                                                ArrayList<Profile_View_Model> profile_models) {
        if (responseStatus) {

            if (profile_models != null) {
                Fragment fragment = new Profile_Fragment_New();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("profileview", profile_models);
                fragment.setArguments(bundle);
                Utilities.getInstance(this).changeHomeFragment(
                        fragment, "Profile_Fragment", this);
            }

        } else {

        }
    }

    @Override
    public void onHttpFailedResponseProfile(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulResponseMachineView(String responseBody, boolean responseStatus, String responseResultMsg,
                                                    ArrayList<Machineries_Model> machineries_model) {

        Log.e("RSEP>>>", responseBody);
        if (responseStatus) {
            if (!machineries_model.isEmpty()) {
                if (DataBaseHelperFor_Machineries.addService(Home.this, machineries_model)) {
//                    Fragment fragment = new Machineries_Fragment();
//                    Utilities.getInstance(this).changeHomeFragment(
//                            fragment, "Machineries_Fragment", this);
                }
            }
        }
    }

    @Override
    public void onHttpFailedResponseMachineView(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulResponseMachineTypes(String responseBody, boolean responseStatus, String responseResultMsg,
                                                     ArrayList<Machine_types_model> machine_types_model, ArrayList<Krishibhavan_Model> krishibhavanModels) {
        if (responseStatus) {

            Fragment fragment = new BookNow_Fragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("machine_types", machine_types_model);
            bundle.putParcelableArrayList("krishibhavanforbook", krishibhavanModels);
            Log.e( "onHttpS","hai" );
            fragment.setArguments(bundle);
            Utilities.getInstance(this).changeHomeFragment(
                    fragment, "BookNow_Fragment", this);


        }

    }

    @Override
    public void onHttpFailedResponseMachineTypes(Throwable throwable, String responseBody,
                                                 boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulResponseMachineListBooking(String responseBody,
                                                           boolean responseStatus, String responseResultMsg,
                                                           ArrayList<MachineListModel_Booking> machine_booking_model) {

        if (responseStatus) {
            if (!machine_booking_model.isEmpty()) {
                Fragment fragment = new Machine_Booking_List_Fragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("machine_types_book", machine_booking_model);


                fragment.setArguments(bundle);
                Utilities.getInstance(this).changeHomeFragment(
                        fragment, "Machine_Booking_List_Fragment", this);
            }
        }
    }

    @Override
    public void onHttpFailedResponseMachineListBooking(Throwable throwable,
                                                       String responseBody, boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulResponseSubmitbooking(String responseBody,
                                                      boolean responseStatus, String responseResultMsg, String bookingid) {


        Log.e( "Responce: ", responseBody);
        Log.e( "Responce: ", ""+responseStatus);

        Log.e( "responseResultMsg: ", responseResultMsg);
        Log.e( "bookingid: ", bookingid);
        if (responseStatus) {

            Bundle bundle = new Bundle();
            bundle.putString("booking_id", bookingid);
            if(!bookingid.equals("")) {
                Fragment fragment = new BookingLater_Confirm_fragment();
                 /*fragment.setArguments(bundle);*/

                fragment.setArguments(bundle);
                Utilities.getInstance(this).changeHomeFragment(
                        fragment, "BookingLater_Confirm_fragment", this);
            }
            else
            {
                Toast.makeText(this, "Booking is not done", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onHttpFailedResponseSubmitbooking(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {

        Toast.makeText(this, "No Booking is not done", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onHttpSuccessfulResponseMachineTypes_booklater
            (String responseBody, boolean responseStatus, String responseResultMsg,
             ArrayList<Machine_types_model> machine_types_model,
             ArrayList<Krishibhavan_Model> krishibhavan_modelsl) {



        if (responseStatus) {
            Fragment fragment = new BookLater_Fragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("machine_types_book_later", machine_types_model);
            bundle.putParcelableArrayList("krishibhavan_book_later", krishibhavan_modelsl);

            fragment.setArguments(bundle);
            Utilities.getInstance(this).changeHomeFragment(
                    fragment, "BookLater_Fragment", this);
        }

    }

    @Override
    public void onHttpFailedResponseMachineTypes_booklater(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulReponseForSbmit_LAterBooking(String responseBody,
                                                             boolean responseStatus, String responseResultMsg, String bookingid) {
        if (responseStatus) {
            Fragment fragment = new BookingLater_Confirm_fragment();

            Utilities.getInstance(this).changeHomeFragment(
                    fragment, "BookingLater_Confirm_fragment", this);
        }
    }

    @Override
    public void onHttpFailedReponseForSbmit_LAterBooking(Throwable throwable, String responseBody,
                                                         boolean resposeStatus, String responseResultMessage) {

    }

    @Override
    public void onHttpSuccessfulForChangePassword(String responseBody,
                                                  boolean responseStatus, String responseResultMsg, String customerId) {
        if (responseStatus) {
            Fragment fragment = new BookNow_Fragment();
            Utilities.getInstance(this).changeHomeFragment(
                    fragment, "BookNow Fragment", this);
        }
    }

    @Override
    public void onHttpFailedForChangePassword(Throwable throwable, String responseBody,
                                              boolean resposeStatus, String responseResultMessage) {

        Toast.makeText(this, ""+responseResultMessage, Toast.LENGTH_LONG).show();

    }


    @Override
    public void KarshgasenaSuccess() {


        fragment = new Karshakasena_Fragment();
        Utilities.getInstance(Home.this).changeHomeFragment(
                fragment, "Karshakasena_Fragment", Home.this);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {


        Log.e( "onConnected: ","" );

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 3);

        }
         else {


            getLocation();
        }


        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
//        Log.e("onConnected: ", "" + mLastLocation.getProvider());
        if (mLastLocation != null) {

            String latitude = String.valueOf(mLastLocation.getLatitude());
            String longitude = String.valueOf(mLastLocation.getLongitude());
            SetLocation(latitude, longitude);


        } else if (loc == null) {


//            mLocationRequest = new LocationRequest();
//            mLocationRequest.setInterval(5000); //5 seconds
//            mLocationRequest.setFastestInterval(3000); //3 seconds
//            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        }

    }


    private void SetLocation(String latitude, String longitude) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);


            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();


                loc_model = new Location_MOdel(city, state, country, latitude, longitude);
            } catch (IOException e) {
                e.printStackTrace();
            }



            location_mOdel = DataBaseHelperFor_location.getProfileOffline(this);



            if (loc_model != null && location_mOdel == null ) {

                Log.e("Check Faild", "SetLocation: " );


                DataBaseHelperFor_location.addfarmerlocatin(this, loc_model);

//            Fragment fragment = new Machineries_Fragment();
//            Utilities.getInstance(this).changeHomeFragment(
//                    fragment, "Machineries_Fragment", this);
            } else {

                Log.e("Check success", "SetLocation: " );
//            Toast.makeText(this, "Please select your location", Toast.LENGTH_LONG).show();
            }


            Log.e( "onConnected: ",""+mLastLocation.toString() );

            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            //LocationAddress locationAddress = new LocationAddress();
            LocationAddress.getAddressFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    this, new GeocoderHandler(this));
            return;
        }



    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onMessageClick(Parcelable token) {
        Toast.makeText(this, "Action", Toast.LENGTH_SHORT).show();
    }

    private static class GeocoderHandler extends Handler {
        private Activity activity;
        private GeocoderHandler(Activity activity) {
            this.activity = activity;
        }
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            TextView locAddress;
            Log.e( "locationAddress: ",locationAddress );
//            locAddress = (TextView) activity.findViewById(R.id.locaddress);
//
//            locAddress.setText(locationAddress);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {

                getLocation();

            }
        }
    }

    private void getLocation() {

        Log.e( "getLocation: ", "Invoke ");
        Log.e( "check for gps: ", "enable or disable  ");

        gps = new LocationListner(Home.this);


        if (gps.canGetLocation()) {


            String longitudes = String.valueOf(gps.getLongitude());
            String latitudes = String.valueOf(gps.getLatitude());
            SetLocation(latitudes, longitudes);


//                    Toast.makeText(getApplicationContext(),"Longitude:"+longitudes+"\nLatitude:"+latitudes,Toast.LENGTH_SHORT).show();
        } else {

            gps.showSettingsAlert();
        }
    }
    public void CheckNetwork()
    {
        if(!isNetworkAvailable())
        {
            ShowMessage();
        }
        else
        {
            fragment = new BookNow_Fragment();
            Utilities.getInstance(Home.this).changeHomeFragment(
                    fragment, "MapsActivity", Home.this);
        }
    }

    public void ShowMessage()
    {


        new SnackBar.Builder(this)
                .withOnClickListener(this)
                .withMessage("No internet connection,please try again latter!")
                .withActionMessage("Action")
                .withTextColorId(R.color.red_btn_bg_color)
                .show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
