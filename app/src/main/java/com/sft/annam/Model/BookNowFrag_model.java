package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/29/2016.
 */
public class BookNowFrag_model  implements Parcelable{
    String far_location;
    String far_date;
    String far_time;
    String far_land_area;
    String far_latitude;
    String far_mac_type_id;
    String far_krishibhavanid;
    String day_hr_value;
    String far_longitude;
    String far_days;
    String far_min;
    String far_hrs;

    public String getFar_mac_type_id() {
        return far_mac_type_id;
    }

    public void setFar_mac_type_id(String far_mac_type_id) {
        this.far_mac_type_id = far_mac_type_id;
    }

    public String getFar_krishibhavanid() {
        return far_krishibhavanid;
    }

    public void setFar_krishibhavanid(String far_krishibhavanid) {
        this.far_krishibhavanid = far_krishibhavanid;
    }

    public String getDay_hr_value() {
        return day_hr_value;
    }

    public void setDay_hr_value(String day_hr_value) {
        this.day_hr_value = day_hr_value;
    }


    public String getFar_latitude() {
        return far_latitude;
    }

    public void setFar_latitude(String far_latitude) {
        this.far_latitude = far_latitude;
    }

    public String getFar_longitude() {
        return far_longitude;
    }

    public void setFar_longitude(String far_longitude) {
        this.far_longitude = far_longitude;
    }

    public String getFar_location() {
        return far_location;
    }

    public void setFar_location(String far_location) {
        this.far_location = far_location;
    }

    public String getFar_date() {
        return far_date;
    }

    public void setFar_date(String far_date) {
        this.far_date = far_date;
    }

    public String getFar_time() {
        return far_time;
    }

    public void setFar_time(String far_time) {
        this.far_time = far_time;
    }

    public String getFar_land_area() {
        return far_land_area;
    }

    public void setFar_land_area(String far_land_area) {
        this.far_land_area = far_land_area;
    }

    public String getFar_days() {
        return far_days;
    }

    public void setFar_days(String far_days) {
        this.far_days = far_days;
    }

    public String getFar_min() {
        return far_min;
    }

    public void setFar_min(String far_min) {
        this.far_min = far_min;
    }

    public String getFar_hrs() {
        return far_hrs;
    }

    public void setFar_hrs(String far_hrs) {
        this.far_hrs = far_hrs;
    }

    public BookNowFrag_model(String far_location, String far_date, String far_time,
                             String far_land_area, String far_days, String far_min,
                             String far_hrs,String far_latitude,String far_longitude,String day_hr_value,String far_mac_type_id,String far_krishibhavanid) {
        this.far_location = far_location;
        this.far_date = far_date;
        this.far_time = far_time;
        this.far_land_area = far_land_area;
        this.far_days = far_days;
        this.far_min = far_min;
        this.far_hrs = far_hrs;
        this.far_latitude=far_latitude;
        this.far_longitude=far_longitude;
        this.day_hr_value=day_hr_value;
        this.far_mac_type_id=far_mac_type_id;
        this.far_krishibhavanid=far_krishibhavanid;
    }

    protected BookNowFrag_model(Parcel in) {
        far_location = in.readString();
        far_date = in.readString();
        far_time = in.readString();
        far_land_area = in.readString();
        far_days = in.readString();
        far_min = in.readString();
        far_hrs = in.readString();
        far_latitude = in.readString();
        far_longitude = in.readString();
        far_mac_type_id=in.readString();
        day_hr_value=in.readString();
    }

    public static final Creator<BookNowFrag_model> CREATOR = new Creator<BookNowFrag_model>() {
        @Override
        public BookNowFrag_model createFromParcel(Parcel in) {
            return new BookNowFrag_model(in);
        }

        @Override
        public BookNowFrag_model[] newArray(int size) {
            return new BookNowFrag_model[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(far_location);
        parcel.writeString(far_date);
        parcel.writeString(far_time);
        parcel.writeString(far_land_area);
        parcel.writeString(far_days);
        parcel.writeString(far_min);
        parcel.writeString(far_hrs);
        parcel.writeString(far_latitude);
        parcel.writeString(far_longitude);
        parcel.writeString(day_hr_value);
        parcel.writeString(far_mac_type_id);
    }
}
