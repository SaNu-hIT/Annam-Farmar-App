package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 8/1/2016.
 */
public class Krishibhavan_Model implements Parcelable{
    String krishi_id;
    String krishi_name;

    protected Krishibhavan_Model(Parcel in) {
        krishi_id = in.readString();
        krishi_name = in.readString();
    }

    public static final Creator<Krishibhavan_Model> CREATOR = new Creator<Krishibhavan_Model>() {
        @Override
        public Krishibhavan_Model createFromParcel(Parcel in) {
            return new Krishibhavan_Model(in);
        }

        @Override
        public Krishibhavan_Model[] newArray(int size) {
            return new Krishibhavan_Model[size];
        }
    };

    public String getKrishi_id() {
        return krishi_id;
    }

    public Krishibhavan_Model(String krishi_id, String krishi_name) {
        this.krishi_id = krishi_id;
        this.krishi_name = krishi_name;
    }

    public void setKrishi_id(String krishi_id) {

        this.krishi_id = krishi_id;
    }

    public String getKrishi_name() {
        return krishi_name;
    }

    public void setKrishi_name(String krishi_name) {
        this.krishi_name = krishi_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(krishi_id);
        parcel.writeString(krishi_name);
    }

    @Override
    public String toString() {
        return "Krishibhavan_Model{" +
                "krishi_id='" + krishi_id + '\'' +
                "krishi_name='" + krishi_name + '\'' +
                '}';
    }
}
