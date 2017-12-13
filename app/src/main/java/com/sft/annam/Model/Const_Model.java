package com.sft.annam.Model;

/**
 * Created by SFT on 16/9/2016.
 */
public class Const_Model {
    public static String machine_name = "";
    public static String machine_details = "";
    public static String dayorhouralue = "";
    public static String fromDate = "";
    public static String days = "";
    public static String hours = "";
    public static String mints = "";
    public static boolean booklaterFlag = false;


    /*Getter Methods*/

    public static String getMachine_name (){
        return machine_name;
    }

    public static String getFromDate() {
        return fromDate;
    }

    public static String getDayorhouralue() {
        return dayorhouralue;
    }

    public static String getDays() {
        return days;
    }

    public static String getHours() {
        return hours;
    }

    public static String getMints() {
        return mints;
    }

    /*Setter Methods*/

    public static void setMachine_name (String mac_name){
        machine_name = mac_name;
    }

    public static void setDayorhouralue(String dayorhouralue) {
        Const_Model.dayorhouralue = dayorhouralue;
    }

    public static void setFromDate(String fromDate) {
        Const_Model.fromDate = fromDate;
    }

    public static void setDays(String days) {
        Const_Model.days = days;
    }

    public static void setHours(String hours) {
        Const_Model.hours = hours;
    }

    public static void setMints(String mints) {
        Const_Model.mints = mints;
    }

    public static String getMachine_details() {
        return machine_details;
    }

    public static void setMachine_details(String machine_details) {
        Const_Model.machine_details = machine_details;
    }
}
