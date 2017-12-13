package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/18/2016.
 */
public class Panchayath_Model implements Parcelable {
    String panchayath_id;
    String panchayath_name;

    public Panchayath_Model(String panchayath_id, String panchayath_name) {
        this.panchayath_id = panchayath_id;
        this.panchayath_name = panchayath_name;
    }

    protected Panchayath_Model(Parcel in) {
        panchayath_id = in.readString();
        panchayath_name = in.readString();
    }

    public static final Creator<Panchayath_Model> CREATOR = new Creator<Panchayath_Model>() {
        @Override
        public Panchayath_Model createFromParcel(Parcel in) {
            return new Panchayath_Model(in);
        }

        @Override
        public Panchayath_Model[] newArray(int size) {
            return new Panchayath_Model[size];
        }
    };

    public String getPanchayath_id() {
        return panchayath_id;
    }

    public void setPanchayath_id(String panchayath_id) {
        this.panchayath_id = panchayath_id;
    }

    public String getPanchayath_name() {
        return panchayath_name;
    }

    public void setPanchayath_name(String panchayath_name) {
        this.panchayath_name = panchayath_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(panchayath_id);
        parcel.writeString(panchayath_name);
    }

    public String toString() {
        return "Panchayath_Model{" +
                "id='" + panchayath_id + '\'' +
                ", name='" + panchayath_name + '\'' +
                '}';
    }
}
