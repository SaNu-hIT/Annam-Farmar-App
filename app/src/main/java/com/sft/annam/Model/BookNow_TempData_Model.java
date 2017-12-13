package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SFT on 12/1/2017.
 */
public class BookNow_TempData_Model implements Parcelable {
    String fullLocation;
    String latitude;
    String longitude;
    String machine_id;
    String quantity;
    String pickupRequired;

    public BookNow_TempData_Model(String fullLocation, String latitude, String longitude,
                                  String machine_id, String quantity, String pickupRequired){
        this.fullLocation = fullLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.machine_id = machine_id;
        this.quantity = quantity;
        this.pickupRequired = pickupRequired;
    }

    public static final Creator<BookNow_TempData_Model> CREATOR = new Creator<BookNow_TempData_Model>() {
        @Override
        public BookNow_TempData_Model createFromParcel(Parcel in) {
            return new BookNow_TempData_Model(in);
        }

        @Override
        public BookNow_TempData_Model[] newArray(int size) {
            return new BookNow_TempData_Model[size];
        }
    };

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPickupRequired() {
        return pickupRequired;
    }

    public void setPickupRequired(String pickupRequired) {
        this.pickupRequired = pickupRequired;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullLocation);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(machine_id);
        parcel.writeString(quantity);
        parcel.writeString(pickupRequired);
    }

    protected BookNow_TempData_Model(Parcel in) {
        fullLocation = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        machine_id = in.readString();
        quantity = in.readString();
        pickupRequired = in.readString();
    }
}
