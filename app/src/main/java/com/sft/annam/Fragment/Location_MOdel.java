package com.sft.annam.Fragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 8/9/2016.
 */
public class Location_MOdel implements Parcelable {
    String place;
    String state;
    String country;
    String latitude;

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

    String longitude;

    public Location_MOdel(String place, String state, String country,String latitude,String longitude) {
        this.place = place;
        this.state = state;
        this.country = country;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    protected Location_MOdel(Parcel in) {
        place = in.readString();
        state = in.readString();
        country = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Location_MOdel> CREATOR = new Creator<Location_MOdel>() {
        @Override
        public Location_MOdel createFromParcel(Parcel in) {
            return new Location_MOdel(in);
        }

        @Override
        public Location_MOdel[] newArray(int size) {
            return new Location_MOdel[size];
        }
    };

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(place);
        parcel.writeString(state);
        parcel.writeString(country);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
    }
    @Override
    public String toString() {
        return "Location_MOdel{" +
                "place='" + place + '\'' +
                "state='" + state + '\'' +
                "country='" + country + '\'' +
                "latitude='" + latitude + '\'' +
                "longitude='" + longitude + '\'' +
              '\'' +
                '}';
    }
}
