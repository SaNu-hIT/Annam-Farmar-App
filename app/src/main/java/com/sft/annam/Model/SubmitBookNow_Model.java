package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SFT on 31/1/2017.
 */
public class SubmitBookNow_Model  implements Parcelable {
    String far_id;
    String far_mac_id;
    String far_mac_type_id;
    String far_pickup;
    String far_total_amount;
    String far_date;
    String far_time;
    String day_hr_value;
    String far_days;
    String far_hrs;
    String far_min;
    String far_quantity;
    String far_land_area;
    String far_dry;
    String far_wet;
    String far_lumpy;
    String far_trees_climb;
    String far_trees_clean;
    String far_copras;
    String far_fuel;
    String far_driver;
    String far_location;
    String far_latitude;
    String far_longitude;

    public String getFar_mac_type_id() {
        return far_mac_type_id;
    }

    public void setFar_mac_type_id(String far_mac_type_id) {
        this.far_mac_type_id = far_mac_type_id;
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

    public String getFar_id() {
        return far_id;
    }

    public void setFar_id(String far_id) {
        this.far_id = far_id;
    }

    public String getFar_mac_id() {
        return far_mac_id;
    }

    public void setFar_mac_id(String far_mac_id) {
        this.far_mac_id = far_mac_id;
    }

    public String getFar_pickup() {
        return far_pickup;
    }

    public void setFar_pickup(String far_pickup) {
        this.far_pickup = far_pickup;
    }

    public String getFar_total_amount() {
        return far_total_amount;
    }

    public void setFar_total_amount(String far_total_amount) {
        this.far_total_amount = far_total_amount;
    }

    public String getFar_quantity() {
        return far_quantity;
    }

    public void setFar_quantity(String far_quantity) {
        this.far_quantity = far_quantity;
    }

    public String getFar_dry() {
        return far_dry;
    }

    public void setFar_dry(String far_dry) {
        this.far_dry = far_dry;
    }

    public String getFar_wet() {
        return far_wet;
    }

    public void setFar_wet(String far_wet) {
        this.far_wet = far_wet;
    }

    public String getFar_lumpy() {
        return far_lumpy;
    }

    public void setFar_lumpy(String far_lumpy) {
        this.far_lumpy = far_lumpy;
    }

    public String getFar_trees_climb() {
        return far_trees_climb;
    }

    public void setFar_trees_climb(String far_trees_climb) {
        this.far_trees_climb = far_trees_climb;
    }

    public String getFar_trees_clean() {
        return far_trees_clean;
    }

    public void setFar_trees_clean(String far_trees_clean) {
        this.far_trees_clean = far_trees_clean;
    }

    public String getFar_copras() {
        return far_copras;
    }

    public void setFar_copras(String far_copras) {
        this.far_copras = far_copras;
    }

    public String getFar_fuel() {
        return far_fuel;
    }

    public void setFar_fuel(String far_fuel) {
        this.far_fuel = far_fuel;
    }

    public String getFar_driver() {
        return far_driver;
    }

    public void setFar_driver(String far_driver) {
        this.far_driver = far_driver;
    }

    public SubmitBookNow_Model(String far_id, String far_mac_id, String far_mac_type_id, String far_pickup,
                               String far_total_amount, String far_date, String far_time, String day_hr_value,
                               String far_days, String far_hrs, String far_min, String far_quantity,
                               String far_land_area, String far_dry, String far_wet, String far_lumpy,
                               String far_trees_climb, String far_trees_clean, String far_copras,
                               String far_fuel, String far_driver,
                               String far_location, String far_latitude, String far_longitude) {
        this.far_id = far_id;
        this.far_mac_id = far_mac_id;
        this.far_mac_type_id=far_mac_type_id;
        this.far_pickup = far_pickup;
        this.far_total_amount = far_total_amount;
        this.far_date = far_date;
        this.far_time = far_time;
        this.day_hr_value=day_hr_value;
        this.far_days = far_days;
        this.far_hrs = far_hrs;
        this.far_min = far_min;
        this.far_quantity = far_quantity;
        this.far_land_area = far_land_area;
        this.far_dry = far_dry;
        this.far_wet = far_wet;
        this.far_lumpy = far_lumpy;
        this.far_trees_climb = far_trees_climb;
        this.far_trees_clean = far_trees_clean;
        this.far_copras = far_copras;
        this.far_fuel = far_fuel;
        this.far_driver = far_driver;
        this.far_location = far_location;
        this.far_latitude=far_latitude;
        this.far_longitude=far_longitude;
    }

    protected SubmitBookNow_Model(Parcel in) {
        far_id = in.readString();
        far_mac_id = in.readString();
        far_mac_type_id=in.readString();
        far_pickup = in.readString();
        far_total_amount = in.readString();
        far_date = in.readString();
        far_time = in.readString();
        day_hr_value = in.readString();
        far_days = in.readString();
        far_hrs = in.readString();
        far_min = in.readString();
        far_quantity = in.readString();
        far_land_area = in.readString();
        far_dry = in.readString();
        far_wet = in.readString();
        far_lumpy = in.readString();
        far_trees_climb = in.readString();
        far_trees_clean = in.readString();
        far_copras = in.readString();
        far_fuel = in.readString();
        far_driver = in.readString();
        far_location = in.readString();
        far_latitude = in.readString();
        far_longitude = in.readString();
    }

    public static final Creator<SubmitBookNow_Model> CREATOR = new Creator<SubmitBookNow_Model>() {
        @Override
        public SubmitBookNow_Model createFromParcel(Parcel in) {
            return new SubmitBookNow_Model(in);
        }

        @Override
        public SubmitBookNow_Model[] newArray(int size) {
            return new SubmitBookNow_Model[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(far_id);
        parcel.writeString(far_mac_id);
        parcel.writeString(far_mac_type_id);
        parcel.writeString(far_pickup);
        parcel.writeString(far_total_amount);
        parcel.writeString(far_date);
        parcel.writeString(far_time);
        parcel.writeString(day_hr_value);
        parcel.writeString(far_days);
        parcel.writeString(far_hrs);
        parcel.writeString(far_min);
        parcel.writeString(far_quantity);
        parcel.writeString(far_land_area);
        parcel.writeString(far_dry);
        parcel.writeString(far_wet);
        parcel.writeString(far_lumpy);
        parcel.writeString(far_trees_climb);
        parcel.writeString(far_trees_clean);
        parcel.writeString(far_copras);
        parcel.writeString(far_fuel);
        parcel.writeString(far_driver);
        parcel.writeString(far_location);
        parcel.writeString(far_latitude);
        parcel.writeString(far_longitude);
    }
}