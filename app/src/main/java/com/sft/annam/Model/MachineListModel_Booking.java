package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/23/2016.
 */
public class MachineListModel_Booking  implements Parcelable{
    String machine_id;
    String machine_name;
    String machine_type;
    String machine_rate_hour;
    String machine_pickup;
    String rate_per_tree_clean;
    String rate_per_coconut_kg;
    String rate_per_pesticide_kg;

    public String getRate_per_tree_clean() {
        return rate_per_tree_clean;
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

    public void setRate_per_tree_clean(String rate_per_tree_clean) {
        this.rate_per_tree_clean = rate_per_tree_clean;
    }

    public String getRate_per_tree_climb() {
        return rate_per_tree_climb;
    }

    public void setRate_per_tree_climb(String rate_per_tree_climb) {
        this.rate_per_tree_climb = rate_per_tree_climb;
    }

    String rate_per_tree_climb;


    public boolean isLand_type() {
        return land_type;
    }

    public void setLand_type(boolean land_type) {
        this.land_type = land_type;
    }

    String machine_description;

    public boolean isDay() {
        return day;
    }

    public void setDay(boolean day) {
        this.day = day;
    }

    public boolean isLand_area() {
        return land_area;
    }

    public void setLand_area(boolean land_area) {
        this.land_area = land_area;
    }

    public boolean isNo_of_trees_toclimb() {
        return no_of_trees_toclimb;
    }

    public void setNo_of_trees_toclimb(boolean no_of_trees_toclimb) {
        this.no_of_trees_toclimb = no_of_trees_toclimb;
    }

    public boolean isNo_of_trees_to_clean() {
        return no_of_trees_to_clean;
    }

    public void setNo_of_trees_to_clean(boolean no_of_trees_to_clean) {
        this.no_of_trees_to_clean = no_of_trees_to_clean;
    }

    public boolean isWith_fuel() {
        return with_fuel;
    }

    public void setWith_fuel(boolean with_fuel) {
        this.with_fuel = with_fuel;
    }

    public boolean isWithout_fuel() {
        return without_fuel;
    }

    public void setWithout_fuel(boolean without_fuel) {
        this.without_fuel = without_fuel;
    }

    public boolean isWith_driver() {
        return with_driver;
    }

    public void setWith_driver(boolean with_driver) {
        this.with_driver = with_driver;
    }

    public boolean isWithout_friver() {
        return without_friver;
    }

    public void setWithout_friver(boolean without_friver) {
        this.without_friver = without_friver;
    }

    public boolean isCoconut_in_kg() {
        return coconut_in_kg;
    }

    public void setCoconut_in_kg(boolean coconut_in_kg) {
        this.coconut_in_kg = coconut_in_kg;
    }

    public boolean isPestiside_in_kg() {
        return pestiside_in_kg;
    }

    public void setPestiside_in_kg(boolean pestiside_in_kg) {
        this.pestiside_in_kg = pestiside_in_kg;
    }

    String machine_image;
    String machine_rate_perday;

    boolean day,land_area,no_of_trees_toclimb,no_of_trees_to_clean,with_fuel,without_fuel,with_driver,without_friver,coconut_in_kg,pestiside_in_kg,land_type;

    public String getMachine_pickup() {
        return machine_pickup;
    }

    public void setMachine_pickup(String machine_pickup) {
        this.machine_pickup = machine_pickup;
    }

    public MachineListModel_Booking(String machine_id, String machine_name, String machine_type, String machine_rate_hour, String machine_pickup, String machine_description, String machine_image, String machine_rate_perday, boolean day, boolean land_area, boolean no_of_trees_toclimb, boolean no_of_trees_to_clean, boolean with_fuel, boolean without_fuel, boolean with_driver, boolean without_friver, boolean coconut_in_kg, boolean pestiside_in_kg,boolean land_type,String rate_per_tree_climb,String rate_per_tree_clean, String rate_per_coconut_kg,String rate_per_pesticide_kg) {
        this.machine_id = machine_id;
        this.machine_name = machine_name;
        this.machine_type = machine_type;
        this.machine_rate_hour = machine_rate_hour;
        this.machine_pickup = machine_pickup;
        this.machine_description = machine_description;
        this.machine_image = machine_image;
        this.machine_rate_perday = machine_rate_perday;
        this.day = day;
        this.land_area = land_area;
        this.no_of_trees_toclimb = no_of_trees_toclimb;
        this.no_of_trees_to_clean = no_of_trees_to_clean;
        this.with_fuel = with_fuel;
        this.without_fuel = without_fuel;
        this.with_driver = with_driver;
        this.without_friver = without_friver;
        this.coconut_in_kg = coconut_in_kg;
        this.pestiside_in_kg = pestiside_in_kg;
        this.land_type=land_type;
        this.coconut_in_kg=coconut_in_kg;
        this.rate_per_tree_climb=rate_per_tree_climb;
        this.rate_per_tree_clean=rate_per_tree_clean;
        this.rate_per_coconut_kg=rate_per_coconut_kg;
        this.rate_per_pesticide_kg=rate_per_pesticide_kg;
    }

    public MachineListModel_Booking(String machine_id, String machine_name,
                                    String machine_type, String machine_rate_hour,
                                    String machine_pickup,
                                    String machine_description, String machine_image,
                                    String machine_rate_perday) {
        this.machine_id = machine_id;
        this.machine_name = machine_name;
        this.machine_type = machine_type;
        this.machine_rate_hour = machine_rate_hour;
        this.machine_pickup = machine_pickup;
        this.machine_description = machine_description;
        this.machine_image = machine_image;
        this.machine_rate_perday = machine_rate_perday;
    }


    public static final Creator<MachineListModel_Booking> CREATOR = new Creator<MachineListModel_Booking>() {
        @Override
        public MachineListModel_Booking createFromParcel(Parcel in) {
            return new MachineListModel_Booking(in);
        }

        @Override
        public MachineListModel_Booking[] newArray(int size) {
            return new MachineListModel_Booking[size];
        }
    };

    public String getMachine_type() {
        return machine_type;
    }



    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }
    protected MachineListModel_Booking(Parcel in) {
        machine_id = in.readString();
        machine_name = in.readString();
        machine_rate_hour = in.readString();
        machine_pickup = in.readString();
        machine_rate_perday = in.readString();
        machine_description = in.readString();
        machine_image = in.readString();
    }



    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getMachine_rate_hour() {
        return machine_rate_hour;
    }

    public void setMachine_rate_hour(String machine_rate_hour) {
        this.machine_rate_hour = machine_rate_hour;
    }

    public String getMachine_rate_perday() {
        return machine_rate_perday;
    }

    public void setMachine_rate_perday(String machine_rate_perday) {
        this.machine_rate_perday = machine_rate_perday;
    }

    public String getMachine_description() {
        return machine_description;
    }

    public void setMachine_description(String machine_description) {
        this.machine_description = machine_description;
    }

    public String getMachine_image() {
        return machine_image;
    }

    public void setMachine_image(String machine_image) {
        this.machine_image = machine_image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(machine_id);
        parcel.writeString(machine_name);
        parcel.writeString(machine_type);
        parcel.writeString(machine_rate_hour);
        parcel.writeString(machine_pickup);
        parcel.writeString(machine_description);
        parcel.writeString(machine_image);
        parcel.writeString(machine_rate_perday);
    }
    @Override
    public String toString() {
        return "MachineListModel_Booking{" +
                "machine_id='" + machine_id + '\'' +
                "machine_name='" + machine_name + '\'' +
                "machine_type='" + machine_type + '\'' +
                "machine_rate_hour='" + machine_rate_hour + '\'' +
                "machine_pickup='" + machine_pickup + '\'' +
                "machine_image='" + machine_image + '\'' +
                "machine_rate_perday='" + machine_rate_perday + '\'' +
                "machine_description='" + machine_description + '\'' +
                '}';
    }
}
