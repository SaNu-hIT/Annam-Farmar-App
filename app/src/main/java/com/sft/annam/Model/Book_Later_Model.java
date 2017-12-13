package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 8/1/2016.
 */
public class Book_Later_Model implements Parcelable{


    String far_later_loc;
    String far_later_lat;
    String far_later_long;
    String far_later_fromdate;
    String far_later_todate;
    String far_later_landarea;
    String far_later_mac_type;
    String far_later_krishi;

    public Book_Later_Model(String far_later_loc, String far_later_lat, String far_later_long,
                            String far_later_fromdate, String far_later_todate,
                            String far_later_landarea, String far_later_mac_type, String far_later_krishi) {
        this.far_later_loc = far_later_loc;
        this.far_later_lat = far_later_lat;
        this.far_later_long = far_later_long;
        this.far_later_fromdate = far_later_fromdate;
        this.far_later_todate = far_later_todate;
        this.far_later_landarea = far_later_landarea;
        this.far_later_mac_type = far_later_mac_type;
        this.far_later_krishi = far_later_krishi;
    }

    protected Book_Later_Model(Parcel in) {
        far_later_loc = in.readString();
        far_later_lat = in.readString();
        far_later_long = in.readString();
        far_later_fromdate = in.readString();
        far_later_todate = in.readString();
        far_later_landarea = in.readString();
        far_later_mac_type = in.readString();
        far_later_krishi = in.readString();
    }

    public static final Creator<Book_Later_Model> CREATOR = new Creator<Book_Later_Model>() {
        @Override
        public Book_Later_Model createFromParcel(Parcel in) {
            return new Book_Later_Model(in);
        }

        @Override
        public Book_Later_Model[] newArray(int size) {
            return new Book_Later_Model[size];
        }
    };

    public String getFar_later_lat() {
        return far_later_lat;
    }

    public void setFar_later_lat(String far_later_lat) {
        this.far_later_lat = far_later_lat;
    }

    public String getFar_later_loc() {
        return far_later_loc;
    }

    public void setFar_later_loc(String far_later_loc) {
        this.far_later_loc = far_later_loc;
    }

    public String getFar_later_long() {
        return far_later_long;
    }

    public void setFar_later_long(String far_later_long) {
        this.far_later_long = far_later_long;
    }

    public String getFar_later_fromdate() {
        return far_later_fromdate;
    }

    public void setFar_later_fromdate(String far_later_fromdate) {
        this.far_later_fromdate = far_later_fromdate;
    }

    public String getFar_later_todate() {
        return far_later_todate;
    }

    public void setFar_later_todate(String far_later_todate) {
        this.far_later_todate = far_later_todate;
    }

    public String getFar_later_landarea() {
        return far_later_landarea;
    }

    public void setFar_later_landarea(String far_later_landarea) {
        this.far_later_landarea = far_later_landarea;
    }

    public String getFar_later_mac_type() {
        return far_later_mac_type;
    }

    public void setFar_later_mac_type(String far_later_mac_type) {
        this.far_later_mac_type = far_later_mac_type;
    }

    public String getFar_later_krishi() {
        return far_later_krishi;
    }

    public void setFar_later_krishi(String far_later_krishi) {
        this.far_later_krishi = far_later_krishi;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(far_later_loc);
        parcel.writeString(far_later_lat);
        parcel.writeString(far_later_long);
        parcel.writeString(far_later_fromdate);
        parcel.writeString(far_later_todate);
        parcel.writeString(far_later_landarea);
        parcel.writeString(far_later_mac_type);
        parcel.writeString(far_later_krishi);
    }


    @Override
    public String toString() {
        return "Book_Later_Model{" +
                "far_later_loc='" + far_later_loc + '\'' +
                "far_later_lat='" + far_later_lat + '\'' +
                "far_later_long='" + far_later_long + '\'' +
                "far_later_fromdate='" + far_later_fromdate + '\'' +
                "far_later_todate='" + far_later_todate + '\'' +
                "far_later_landarea='" + far_later_landarea + '\'' +
                "far_later_mac_type='" + far_later_mac_type + '\'' +
                "far_later_krishi='" + far_later_krishi + '\'' +

                '}';
    }

}
