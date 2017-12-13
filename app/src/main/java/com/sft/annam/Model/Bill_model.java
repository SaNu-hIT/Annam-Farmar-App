package com.sft.annam.Model;

/**
 * Created by intellyelabs on 05/07/17.
 */

public class Bill_model {


    String booking_id;
    String bill_no;

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getFarmer_phone() {
        return farmer_phone;
    }

    public void setFarmer_phone(String farmer_phone) {
        this.farmer_phone = farmer_phone;
    }

    public String getFarmer_loc() {
        return farmer_loc;
    }

    public void setFarmer_loc(String farmer_loc) {
        this.farmer_loc = farmer_loc;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getLand_area_in_cent() {
        return land_area_in_cent;
    }

    public void setLand_area_in_cent(String land_area_in_cent) {
        this.land_area_in_cent = land_area_in_cent;
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

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    String farmer_name;
    String farmer_phone;

    public Bill_model(String booking_id, String bill_no, String farmer_name, String farmer_phone, String farmer_loc, String date_and_time, String total_amount, String owner_name, String land_area_in_cent, String machine_type, String machine_name, String total_time, String customer_name, String customer_phone) {
        this.booking_id = booking_id;
        this.bill_no = bill_no;
        this.farmer_name = farmer_name;
        this.farmer_phone = farmer_phone;
        this.farmer_loc = farmer_loc;
        this.date_and_time = date_and_time;
        this.total_amount = total_amount;
        this.owner_name = owner_name;
        this.land_area_in_cent = land_area_in_cent;
        this.machine_type = machine_type;
        this.machine_name = machine_name;
        this.total_time = total_time;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
    }

    String farmer_loc;
    String date_and_time;
    String total_amount;
    String owner_name;
    String land_area_in_cent;
    String machine_type;
    String machine_name;
    String total_time;
    String customer_name;
    String customer_phone;


}
