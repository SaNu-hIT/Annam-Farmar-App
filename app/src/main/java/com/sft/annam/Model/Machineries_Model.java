package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/22/2016.
 */
public class Machineries_Model implements Parcelable {
    String machine_id;
    String machine_type;
    String machine_name;
    String rate_per_hour;
    String rate_per_day;
    String description;
    String machine_feature;
    String image;
    String rate_per_tree_climb;
    String rate_per_tree_clean;
    String rate_per_coconut_kg;
    String rate_per_pesticide_kg;
    String specification1;
    String specification2;
    String specification3;

    public Machineries_Model(String machine_id, String machine_type,
                             String machine_name, String rate_per_hour,
                             String rate_per_day, String description, String machine_feature, String image,String rate_per_tree_climb, String rate_per_tree_clean,
                             String rate_per_coconut_kg, String rate_per_pesticide_kg, String specification1, String specification2,String specification3) {
        this.machine_id = machine_id;
        this.machine_type = machine_type;
        this.machine_name = machine_name;
        this.rate_per_hour = rate_per_hour;
        this.rate_per_day = rate_per_day;
        this.description = description;
        this.machine_feature = machine_feature;
        this.image = image;
        this.rate_per_tree_climb=rate_per_tree_climb;
        this.rate_per_tree_clean=rate_per_tree_clean;
        this.rate_per_coconut_kg=rate_per_coconut_kg;
        this.rate_per_pesticide_kg=rate_per_pesticide_kg;
        this.specification1=specification1;
        this.specification2=specification2;
        this.specification3=specification3;
    }


    public String getRate_per_tree_climb() {
        return rate_per_tree_climb;
    }

    public void setRate_per_tree_climb(String rate_per_tree_climb) {
        this.rate_per_tree_climb = rate_per_tree_climb;
    }

    public String getRate_per_tree_clean() {
        return rate_per_tree_clean;
    }

    public void setRate_per_tree_clean(String rate_per_tree_clean) {
        this.rate_per_tree_clean = rate_per_tree_clean;
    }

    public String getRate_per_coconut_kg() {
        return rate_per_coconut_kg;
    }

    public void setRate_per_coconut_kg(String rate_per_coconut_kg) {
        this.rate_per_coconut_kg = rate_per_coconut_kg;
    }

    public String getRate_per_pesticide_kg() {
        return rate_per_pesticide_kg;
    }

    public void setRate_per_pesticide_kg(String rate_per_pesticide_kg) {
        this.rate_per_pesticide_kg = rate_per_pesticide_kg;
    }

    public String getSpecification1() {
        return specification1;
    }

    public void setSpecification1(String specification1) {
        this.specification1 = specification1;
    }

    public String getSpecification2() {
        return specification2;
    }

    public void setSpecification2(String specification2) {
        this.specification2 = specification2;
    }

    public String getSpecification3() {
        return specification3;
    }

    public void setSpecification3(String specification3) {
        this.specification3 = specification3;
    }



    protected Machineries_Model(Parcel in) {
        machine_id = in.readString();
        machine_type = in.readString();
        machine_name = in.readString();
        rate_per_hour = in.readString();
        rate_per_day = in.readString();
        description = in.readString();
        machine_feature = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(machine_id);
        dest.writeString(machine_type);
        dest.writeString(machine_name);
        dest.writeString(rate_per_hour);
        dest.writeString(rate_per_day);
        dest.writeString(description);
        dest.writeString(machine_feature);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Machineries_Model> CREATOR = new Creator<Machineries_Model>() {
        @Override
        public Machineries_Model createFromParcel(Parcel in) {
            return new Machineries_Model(in);
        }

        @Override
        public Machineries_Model[] newArray(int size) {
            return new Machineries_Model[size];
        }
    };

    public String getMachine_feature() {
        return machine_feature;
    }

    public void setMachine_feature(String machine_feature) {
        this.machine_feature = machine_feature;
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public String getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getRate_per_hour() {
        return rate_per_hour;
    }

    public void setRate_per_hour(String rate_per_hour) {
        this.rate_per_hour = rate_per_hour;
    }

    public String getRate_per_day() {
        return rate_per_day;
    }

    public void setRate_per_day(String rate_per_day) {
        this.rate_per_day = rate_per_day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Machineries_Model{" +
                "machine_name='" + machine_name + '\'' +
                "machine_type='" + machine_type + '\'' +
                "rate_per_day='" + rate_per_day + '\'' +
                "rate_per_hour='" + rate_per_hour + '\'' +
                "description='" + description + '\'' +
                "machine_feature='" + machine_feature + '\'' +
                "image='" + image + '\'' +
                '}';
    }
}
