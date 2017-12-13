package com.sft.annam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.UiSettings;
import com.google.maps.android.ui.IconGenerator;
import com.sft.annam.Connection.HttpRequestHelperForMachine_types;
import com.sft.annam.Connection.HttpRequestHelperForMachine_types_book_later;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Fragment.Location_MOdel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sft.annam.Fragment.Machineries_Fragment;
import com.sft.annam.Model.Constants;
import com.sft.annam.Utilities.Utilities;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.Manifest;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Shafal on 30/08/2016.
 */

public class MapsActivity extends Fragment
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback {

    Location_MOdel loc_model;

    private GoogleMap mMap;
    String far_locate;
    Button nexthome;
    Button machines;
    TextView locAddress;

    // My New Variables
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    LatLng latLng;
    Marker currLocationMarker;

    int mapHeight, mapWidth;
    int flag=2;
    String message;
    int messageId;
    private final String PREFS_MESSAGE_STATUS = "msg";
    //String selectedDate, selectedTime;

    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.maps,container,false);
        nexthome = (Button) rootView.findViewById(R.id.bookButton);
        machines = (Button) rootView.findViewById(R.id.machineButton);
        locAddress = (TextView) rootView.findViewById(R.id.locaddress);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        FrameLayout myContainer = (FrameLayout) rootView.findViewById(R.id.map);
        mapHeight = myContainer.getHeight();
        mapWidth = myContainer.getWidth();
        CheckNetwork();

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            Toast.makeText(getActivity(), "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
//        }else{
//            showGPSDisabledAlertToUser();
//        }

        nexthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loc_model != null) {
                    flag=0;

                    //DialogFragment newFragment = new SelectDateFragment();
                    //newFragment.show(getFragmentManager(), "DatePicker");
                    addLocNBookNow();

                } else {
                    Toast.makeText(getActivity(), "Please select your location", Toast.LENGTH_LONG).show();
                }

            }
        });
        machines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loc_model != null) {
                    DataBaseHelperFor_location.addfarmerlocatin(getActivity(), loc_model);
                    Toast.makeText(getActivity(), "Selected location is " + far_locate, Toast.LENGTH_LONG).show();
                    Fragment fragment = new Machineries_Fragment();
                    Utilities.getInstance(getActivity()).changeHomeFragment(
                            fragment, "Machineries_Fragment", getActivity());
                } else {
                    Toast.makeText(getActivity(), "Please select your location", Toast.LENGTH_LONG).show();
                }

            }
        });
        getActivity().setTitle("Annam");
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //hasNewMessage();
    }

    private void hasNewMessage() {
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);

        String url = "http://192.168.1.18/debug/AnnamAgroTech/farmer/message.php";

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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getString("status").equals("1")){
                message = jsonObject.getString("message");
                messageId = Integer.parseInt(jsonObject.getString("id"));
            } else{
                message = "";
                messageId = 0;
            }
            SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_MESSAGE_STATUS, Context.MODE_PRIVATE).edit();
            SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(PREFS_MESSAGE_STATUS, Context.MODE_PRIVATE);
            if (sharedpreferences.getInt("messageID",0) < messageId){
                editor.putBoolean("hasMessageID",true);
                editor.putInt("messageID",messageId);
                editor.putBoolean("hasReadMessage",false);
                editor.apply();
            }
        } catch (JSONException e) {
            message = "";
            messageId = 0;
        }
        SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(PREFS_MESSAGE_STATUS, Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("hasMessageID",false)){
            Log.e("MyLog","Prefs Found");
            if (sharedpreferences.getInt("messageID",0) < messageId){
                Log.e("MyLog","If Condition");
                showWelcomeMessage();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_MESSAGE_STATUS, Context.MODE_PRIVATE).edit();
                editor.putBoolean("hasReadMessage",true);
                editor.apply();
            } else if (sharedpreferences.getInt("messageID",0) == messageId){
                Log.e("MyLog","Else If Condition");
                if(!sharedpreferences.getBoolean("hasReadMessage",false)){
                    showWelcomeMessage();
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_MESSAGE_STATUS, Context.MODE_PRIVATE).edit();
                    editor.putBoolean("hasReadMessage",true);
                    editor.apply();
                }
            }
        } else {
            Log.e("MyLog","No Prefs Found");
            showWelcomeMessage();
        }
    }

    private void showWelcomeMessage() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.popup_message);
        dialog.setTitle("Annam");
        dialog.setCanceledOnTouchOutside(false);

        Button button = (Button) dialog.findViewById(R.id.Button01);
        TextView textView = (TextView) dialog.findViewById(R.id.TextView01);
        textView.setText(message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void addLocNBookNow(){
        //Constants.setSelectedDate(selectedDate);
        //Constants.setSelectedTime(selectedTime);
        DataBaseHelperFor_location.addfarmerlocatin(getActivity(), loc_model);
        Toast.makeText(getActivity(), "Selected location is " + far_locate, Toast.LENGTH_LONG).show();
        if (flag == 0){
            flag = 2;
            if(Utilities.getInstance(getActivity()).isNetAvailable()){
                HttpRequestHelperForMachine_types machine_types=new HttpRequestHelperForMachine_types();
                machine_types.machine_types(getActivity());
            }else{
                CheckNetwork();
            }
        } else if (flag == 1) {
            flag = 2;
            if (Utilities.getInstance(getActivity()).isNetAvailable()) {
                HttpRequestHelperForMachine_types_book_later machine_types_later = new HttpRequestHelperForMachine_types_book_later();
                machine_types_later.machine_types_booklater(getActivity());
            } else {
               CheckNetwork();
            }
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap gMap) {
        UiSettings mUiSettings;
        mMap = gMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        //mMap.setMyLocationEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        } else {

            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            buildMap();
            gMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    LatLng center = cameraPosition.target;
                    onMapMoved(center);
                }
            });
        }
    }

    protected synchronized void buildGoogleApiClient() {
        //Toast.makeText(this, "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            //LocationAddress locationAddress = new LocationAddress();
            LocationAddress.getAddressFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    getContext(), new GeocoderHandler(getActivity()));
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        if (latLng != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(14).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    LatLng center = cameraPosition.target;
                    onMapMoved(center);
                }
            });
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getActivity(), "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();
        //If you only need one location, unregister the listener
        //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "onConnectionFailed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 123) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // -----------------------------------------------------------------------------------------------------------------------------------------------------------
                getActivity().finish();
            } else {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    showalert();
                    return;
                }
                mMap.setMyLocationEnabled(true);
                buildMap();
            }
        }
    }

    public void showalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Sorry!\nLocation Permission is crucial for this app");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                getActivity().finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void buildMap() {
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    public void onMapMoved(LatLng latLng){
        mMap.clear();
        loc_model = null;
        double lat = latLng.latitude;
        double lng = latLng.longitude;
        String rState = null;
        String rCountry = null;
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        String result = null;
        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append(", ");
                }
                sb.setLength(Math.max(sb.length() - 2, 0));
                result = sb.toString();
                rState = addressList.get(0).getAdminArea();
                rCountry = addressList.get(0).getCountryName();
            }
        } catch (IOException e) {
            CheckNetwork();
            Log.e("Map Error", "Unable connect to Geocoder", e);
        } finally {
            int addLength = 0;
            String city = "";
            String state = "";
            String country = "";
            String latitude = String.valueOf(lat);
            String longitude = String.valueOf(lng);
            if (result != null && result != "") {
                String[] address = result.trim().split("\\s*,\\s*");
                addLength = address.length - 1;
                if (addLength > 1) {
                    country = address[addLength];
                    addLength--;
                    state = address[addLength];
                    addLength--;
                    for (int i=0; i < addLength; i++){
                        city = city + address[i] + ", ";
                    }
                    city = city + address[addLength];
                } else if (addLength == 1) {
                    city = address[0];
                    state = address[1];
                    country = rCountry;
                } else if (addLength == 0){
                    city = address[0];
                    state = rState;
                    country = rCountry;
                } else {
                    city = null;
                    state = null;
                    country = null;
                }
                if (city == null) {
                    far_locate = "Please select a valid location";
                } else{
                    far_locate = city;
                }

            } else {
                result = "Unable to get address for your location";
                far_locate = "Unable to get address for your location";
                city = null;
                state = null;
                country = null;
            }
            if (city != null){
                loc_model = new Location_MOdel(city, state, country, latitude, longitude);
            }
            LatLng kochi = new LatLng(lat+0.003000, lng);

            IconGenerator factory = new IconGenerator(getContext());
            factory.setStyle(IconGenerator.STYLE_GREEN);
            Bitmap icon = factory.makeIcon("Your Selected Location");

            //mMap.addMarker(new MarkerOptions().position(kochi).title(far_locate).icon(BitmapDescriptorFactory.fromBitmap(icon)));

            //CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(mMap.getCameraPosition().zoom).build();
            //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            locAddress.setText(result);
        }
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
            locAddress = (TextView) activity.findViewById(R.id.locaddress);
            locAddress.setText(locationAddress);
        }
    }

/*
    @SuppressLint("ValidFragment")
    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            //txtFromDate.setText(day+"/"+month+"/"+year);
            //Log.e("My Log","Selected Date : " + day+"/"+month+"/"+year);
            selectedDate = day+"/"+month+"/"+year;
            DialogFragment newFragment = new SelectTimeFragment();
            newFragment.show(getFragmentManager(), "TimePicker");
        }

    }

    @SuppressLint("ValidFragment")
    private class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

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
            }* /
            populateSetTime(hour, minute);
        }

        public void populateSetTime(int hour, int minute) {
            if (hour < 13) {
                if (minute < 10){
                    selectedTime = hour + ":" + "0" + minute + " AM";
                } else {
                    selectedTime = hour + ":" + minute + " AM";
                }
            } else {
                hour = hour - 12;
                if (minute < 10){
                    selectedTime = hour + ":" + "0" + minute + " PM";
                } else {
                    selectedTime = hour + ":" + minute + " PM";
                }
            }
            addLocNBookNow();
        }
    }
    */


    private void showGPSDisabledAlertToUser(){



        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("GPS is disabled?")
                .setContentText("Would you like to enable it!")
                .setCancelText("NO")
                .setConfirmText("YES")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();


                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);

                    }
                })
                .show();
    }


    public void ShowMessage()
    {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Error")
                .setContentText("No internet connection,please try again latter")
                .setConfirmText("OK")
                .show();
    }

private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
            = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}


public void CheckNetwork()
{
    if(!isNetworkAvailable())

    {
        ShowMessage();
    }
}
}
