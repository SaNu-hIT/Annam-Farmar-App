package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/18/2016.
 */
public class Farmer_type implements Parcelable {
    String farmerttype_id;
    String farmertype_name;

    protected Farmer_type(Parcel in) {
        farmerttype_id = in.readString();
        farmertype_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(farmerttype_id);
        dest.writeString(farmertype_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Farmer_type> CREATOR = new Creator<Farmer_type>() {
        @Override
        public Farmer_type createFromParcel(Parcel in) {
            return new Farmer_type(in);
        }

        @Override
        public Farmer_type[] newArray(int size) {
            return new Farmer_type[size];
        }
    };

    public String getFarmertype_name() {
        return farmertype_name;
    }

    public Farmer_type(String farmerttype_id, String farmertype_name) {
        this.farmerttype_id = farmerttype_id;
        this.farmertype_name = farmertype_name;
    }

    public void setFarmertype_name(String farmertype_name) {

        this.farmertype_name = farmertype_name;
    }

    public String getFarmerttype_id() {
        return farmerttype_id;
    }

    public void setFarmerttype_id(String farmerttype_id) {
        this.farmerttype_id = farmerttype_id;
    }

    @Override
    public String toString() {
        return "Farmer_type{" +
                "id='" + farmerttype_id + '\'' +
                ", name='" + farmertype_name + '\'' +
                '}';
    }
}
