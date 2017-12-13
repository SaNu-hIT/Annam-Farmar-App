package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/21/2016.
 */


public class Profile_View_Model implements Parcelable {
    String farmer_id;
    String farmer_name;

    String farmer_address;
    String farmer_phone;

    public Profile_View_Model(String farmer_id, String farmer_name, String farmer_address,
                              String farmer_email, String farmer_phone, String farmer_pachayath_loc,
                              String farmer_type, String farmer_crop_name) {
        this.farmer_id = farmer_id;
        this.farmer_name = farmer_name;
        this.farmer_address = farmer_address;
        this.farmer_email = farmer_email;
        this.farmer_phone = farmer_phone;
        this.farmer_pachayath_loc = farmer_pachayath_loc;
        this.farmer_type = farmer_type;
        this.farmer_crop_name = farmer_crop_name;
    }

    protected Profile_View_Model(Parcel in) {
        farmer_id = in.readString();
        farmer_name = in.readString();
        farmer_address = in.readString();
        farmer_phone = in.readString();
        farmer_email = in.readString();
        farmer_pachayath_loc = in.readString();
        farmer_type = in.readString();
        farmer_crop_name = in.readString();
    }

    public static final Creator<Profile_View_Model> CREATOR = new Creator<Profile_View_Model>() {
        @Override
        public Profile_View_Model createFromParcel(Parcel in) {
            return new Profile_View_Model(in);
        }

        @Override
        public Profile_View_Model[] newArray(int size) {
            return new Profile_View_Model[size];
        }
    };

    public String getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getFarmer_address() {
        return farmer_address;
    }

    public void setFarmer_address(String farmer_address) {
        this.farmer_address = farmer_address;
    }

    public String getFarmer_phone() {
        return farmer_phone;
    }

    public void setFarmer_phone(String farmer_phone) {
        this.farmer_phone = farmer_phone;
    }

    public String getFarmer_email() {
        return farmer_email;
    }

    public void setFarmer_email(String farmer_email) {
        this.farmer_email = farmer_email;
    }

    public String getFarmer_pachayath_loc() {
        return farmer_pachayath_loc;
    }

    public void setFarmer_pachayath_loc(String farmer_pachayath_loc) {
        this.farmer_pachayath_loc = farmer_pachayath_loc;
    }

    public String getFarmer_type() {
        return farmer_type;
    }

    public void setFarmer_type(String farmer_type) {
        this.farmer_type = farmer_type;
    }

    public String getFarmer_crop_name() {
        return farmer_crop_name;
    }

    public void setFarmer_crop_name(String farmer_crop_name) {
        this.farmer_crop_name = farmer_crop_name;
    }

    String farmer_email;
    String farmer_pachayath_loc;
    String farmer_type;
    String farmer_crop_name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(farmer_id);
        parcel.writeString(farmer_name);
        parcel.writeString(farmer_address);
        parcel.writeString(farmer_phone);
        parcel.writeString(farmer_email);
        parcel.writeString(farmer_pachayath_loc);
        parcel.writeString(farmer_type);
        parcel.writeString(farmer_crop_name);
    }

    @Override
    public String toString() {
        return "Profile_View_Model{" +
                "farmer_id='" + farmer_id + '\'' +
                "farmer_name='" + farmer_name + '\'' +
                "farmer_address='" + farmer_address + '\'' +
                "farmer_email='" + farmer_email + '\'' +
                "farmer_phone='" + farmer_phone + '\'' +
                "farmer_pachayath_loc='" + farmer_pachayath_loc + '\'' +
                "farmer_type='" + farmer_type + '\'' +
                "farmer_crop_name='" + farmer_crop_name + '\'' +
                '}';
    }
}
