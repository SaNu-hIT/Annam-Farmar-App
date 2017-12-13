package com.sft.annam.Model;

/**
 * Created by SFT on 14/11/2016.
 */
public class Constants {
    public static String selectedDate = "";
    public static String selectedTime = "";

    public static String getSelectedDate() {
        return selectedDate;
    }

    public static void setSelectedDate(String selectedDate) {
        Constants.selectedDate = selectedDate;
    }

    public static String getSelectedTime() {
        return selectedTime;
    }

    public static void setSelectedTime(String selectedTime) {
        Constants.selectedTime = selectedTime;
    }
}
