package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SFT on 21/9/2016.
 */
public class Booking_Status_Items_model implements Parcelable {
    String booking_id;
    String farmer_land_loc;
    String name;
    String phone;
    String machine_name;
    String bstatus;
    String booking_date;
    String arrival_time;
    String image;

    public Booking_Status_Items_model (String booking_id, String farmer_land_loc, String name, String phone, String machine_name, String bstatus, String booking_date, String arrival_time, String image){
        this.booking_id = booking_id;
        this.farmer_land_loc = farmer_land_loc;
        this.name = name;
        this.phone = phone;
        this.machine_name = machine_name;
        this.bstatus = bstatus;
        this.booking_date = booking_date;
        this.arrival_time = arrival_time;
        this.image = image;
    }

    protected Booking_Status_Items_model (Parcel in){
        booking_id = in.readString();
        farmer_land_loc = in.readString();
        name = in.readString();
        phone = in.readString();
        machine_name = in.readString();
        bstatus = in.readString();
        booking_date = in.readString();
        arrival_time = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(booking_id);
        parcel.writeString(farmer_land_loc);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(machine_name);
        parcel.writeString(bstatus);
        parcel.writeString(booking_date);
        parcel.writeString(arrival_time);
        parcel.writeString(image);
    }

    public static final Creator<Booking_Status_Items_model> CREATOR = new Creator<Booking_Status_Items_model>() {
        @Override
        public Booking_Status_Items_model createFromParcel(Parcel parcel) {
            return new Booking_Status_Items_model(parcel);
        }

        @Override
        public Booking_Status_Items_model[] newArray(int size) {
            return new Booking_Status_Items_model[size];
        }
    };

    @Override
    public String toString() {
        return "Booking_Status_Items_Model{" +
                "booking_id='" + booking_id + '\'' +
                "farmer_land_loc='" + farmer_land_loc + '\'' +
                "name='" + name + '\'' +
                "phone='" + phone + '\'' +
                "machine_name='" + machine_name + '\'' +
                "bstatus='" + bstatus + '\'' +
                "booking_date='" + booking_date + '\'' +
                "arrival_time='" + arrival_time + '\'' +
                "image='" + image + '\'' +

                '}';
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getFarmer_land_loc() {
        return farmer_land_loc;
    }

    public void setFarmer_land_loc(String farmer_land_loc) {
        this.farmer_land_loc = farmer_land_loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
