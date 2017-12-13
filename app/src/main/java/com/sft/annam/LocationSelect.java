package com.sft.annam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Window;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.Fragment.Location_MOdel;

public class LocationSelect extends AppCompatActivity {
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Location_MOdel loc_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location_select);
        Log.e("dsav", "Place: ");

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e("fsdfa", "onActivityResult: ");
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("place", ""+place);
                String city=place.getName().toString();


                String address=place.getAddress().toString();


                String[] namesList = address.split(",");


                String state=namesList[namesList.length-2];
                String country=namesList[namesList.length-1];
                Log.e("state: ",state );
                Log.e("country: ",country );


                String latitude= String.valueOf(place.getLatLng().latitude);
                String longitude= String.valueOf(place.getLatLng().longitude);

                loc_model = new Location_MOdel(city, state, country, latitude, longitude);

                DataBaseHelperFor_location.addfarmerlocatin(this, loc_model);

                finish();



            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("loge", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
