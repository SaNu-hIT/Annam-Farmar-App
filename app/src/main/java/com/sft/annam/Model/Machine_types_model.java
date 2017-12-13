package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/23/2016.
 */
public class Machine_types_model implements Parcelable {
    String machine_type_id;
    String machine_name;
    String pickup_required;
    String image;
    String quantitiy;

    public Machine_types_model(String machine_type_id, String machine_name, String pickup_required, String image, String quantity) {
        this.machine_type_id = machine_type_id;
        this.machine_name = machine_name;
        this.pickup_required = pickup_required;
        this.image = image;
        this.quantitiy = quantity;
    }

    protected Machine_types_model(Parcel in) {
        machine_type_id = in.readString();
        machine_name = in.readString();
        pickup_required = in.readString();
        image = in.readString();
        quantitiy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(machine_type_id);
        dest.writeString(machine_name);
        dest.writeString(pickup_required);
        dest.writeString(image);
        dest.writeString(quantitiy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Machine_types_model> CREATOR = new Creator<Machine_types_model>() {
        @Override
        public Machine_types_model createFromParcel(Parcel in) {
            return new Machine_types_model(in);
        }

        @Override
        public Machine_types_model[] newArray(int size) {
            return new Machine_types_model[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantitiy() {
        return quantitiy;
    }

    public void setQuantitiy(String quantitiy) {
        this.quantitiy = quantitiy;
    }

    public String getMachine_type_id() {

        return machine_type_id;
    }

    public void setMachine_type_id(String machine_type_id) {
        this.machine_type_id = machine_type_id;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getPickup_required() {
        return pickup_required;
    }

    public void setPickup_required(String pickup_required) {
        this.pickup_required = pickup_required;
    }

    @Override
    public String toString() {
        return "Machine_types_model{" +
                "machine_type_id='" + machine_type_id + '\'' +
                "machine_name='" + machine_name + '\'' +
                "pickup_required='" + pickup_required + '\'' +
                "image='" + image + '\'' +
                "quantity='" + quantitiy + '\'' +
                '}';
    }

}
