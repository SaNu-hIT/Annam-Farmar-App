package com.sft.annam.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JESNA on 7/14/2016.
 */
public class Login_Model implements Parcelable {
    String username;
    String password;



    protected Login_Model(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<Login_Model> CREATOR = new Creator<Login_Model>() {
        @Override
        public Login_Model createFromParcel(Parcel in) {
            return new Login_Model(in);
        }

        @Override
        public Login_Model[] newArray(int size) {
            return new Login_Model[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Login_Model(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(password);
    }
}
