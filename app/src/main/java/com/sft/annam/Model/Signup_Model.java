package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/19/2016.
 */
public class Signup_Model implements Parcelable{
    String signup_name;
    String signup_address;
    String signup_phone;
    String signup_email;
    String signup_password;
    String signup_panchayath;
    String signup_crops;
    String signup_farmertype;

    protected Signup_Model(Parcel in) {
        signup_name = in.readString();
        signup_address = in.readString();
        signup_phone = in.readString();
        signup_email = in.readString();
        signup_password = in.readString();
        signup_panchayath = in.readString();
        signup_crops = in.readString();
        signup_farmertype = in.readString();
    }

    public static final Creator<Signup_Model> CREATOR = new Creator<Signup_Model>() {
        @Override
        public Signup_Model createFromParcel(Parcel in) {
            return new Signup_Model(in);
        }

        @Override
        public Signup_Model[] newArray(int size) {
            return new Signup_Model[size];
        }
    };

    public String getSignup_farmertype() {
        return signup_farmertype;
    }

    public void setSignup_farmertype(String signup_farmertype) {
        this.signup_farmertype = signup_farmertype;
    }

    public String getSignup_name() {
        return signup_name;
    }

    public void setSignup_name(String signup_name) {
        this.signup_name = signup_name;
    }

    public String getSignup_address() {
        return signup_address;
    }

    public void setSignup_address(String signup_address) {
        this.signup_address = signup_address;
    }

    public String getSignup_phone() {
        return signup_phone;
    }

    public void setSignup_phone(String signup_phone) {
        this.signup_phone = signup_phone;
    }

    public String getSignup_email() {
        return signup_email;
    }

    public void setSignup_email(String signup_email) {
        this.signup_email = signup_email;
    }

    public String getSignup_password() {
        return signup_password;
    }

    public void setSignup_password(String signup_password) {
        this.signup_password = signup_password;
    }

    public String getSignup_panchayath() {
        return signup_panchayath;
    }

    public Signup_Model(String signup_name, String signup_address, String signup_phone,
                        String signup_email, String signup_password, String signup_panchayath,
                        String signup_crops, String signup_farmertype) {
        this.signup_name = signup_name;
        this.signup_address = signup_address;
        this.signup_phone = signup_phone;
        this.signup_email = signup_email;
        this.signup_password = signup_password;
        this.signup_panchayath = signup_panchayath;
        this.signup_crops = signup_crops;
        this.signup_farmertype = signup_farmertype;
    }

    public void setSignup_panchayath(String signup_panchayath) {
        this.signup_panchayath = signup_panchayath;
    }

    public String getSignup_crops() {
        return signup_crops;
    }

    public void setSignup_crops(String signup_crops) {
        this.signup_crops = signup_crops;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(signup_name);
        parcel.writeString(signup_address);
        parcel.writeString(signup_phone);
        parcel.writeString(signup_email);
        parcel.writeString(signup_password);
        parcel.writeString(signup_panchayath);
        parcel.writeString(signup_crops);
        parcel.writeString(signup_farmertype);
    }
}
