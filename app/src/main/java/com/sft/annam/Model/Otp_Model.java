package com.sft.annam.Model;

/**
 * Created by SFT on 7/10/2016.
 */
public class Otp_Model {
    //URLs to register.php and confirm.php file
    public static final String CONFIRM_URL = "http://annamagrotech.com/webservice/farmer/confirmotp.php";
    //public static final String CONFIRM_URL = "http://192.168.1.13/debug/AnnamAgroTech/farmer/confirmotp.php";
    public static final String RESEND_URL = "http://annamagrotech.com/webservice/farmer/resendotp.php?phone=";

    //Keys to send username, password, phone and otp
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_OTP = "otp";

    public static final String ERRERCODE = "errorCode";

    public static String phone;

    //JSON Tag from response from server
    public static final String TAG_RESPONSE= "ErrorMessage";

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Otp_Model.phone = phone;
    }
}
