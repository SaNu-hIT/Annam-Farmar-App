package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/18/2016.
 */
public class Crops_type_Model implements Parcelable{
    String crops_id;
    String crops_name;

    protected Crops_type_Model(Parcel in) {
        crops_id = in.readString();
        crops_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(crops_id);
        dest.writeString(crops_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Crops_type_Model> CREATOR = new Creator<Crops_type_Model>() {
        @Override
        public Crops_type_Model createFromParcel(Parcel in) {
            return new Crops_type_Model(in);
        }

        @Override
        public Crops_type_Model[] newArray(int size) {
            return new Crops_type_Model[size];
        }
    };

    public String getCrops_id() {
        return crops_id;
    }

    public Crops_type_Model(String crops_id, String crops_name) {
        this.crops_id = crops_id;
        this.crops_name = crops_name;
    }

    public void setCrops_id(String crops_id) {
        this.crops_id = crops_id;
    }

    public String getCrops_name() {
        return crops_name;
    }

    public void setCrops_name(String crops_name) {
        this.crops_name = crops_name;
    }

    @Override
    public String toString() {
        return "Crops_types{" +
                "id='" + crops_id + '\'' +
                ", name='" + crops_name + '\'' +
                '}';
    }
}
