package com.sft.annam.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Home on 1/9/2017.
 */

public class SessionManager {
    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    public static final String PREFF_NAME = "ANNAM_FARMAR";
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFF_NAME, PRIVATE_MODE);
        editor = preferences.edit();

    }


    public boolean getArgeeFlag() {
        boolean ss = preferences.getBoolean("AgrreOrNot", false);
        return ss;
    }
public void SetAgreFlag(boolean status)
{
    editor.putBoolean("AgrreOrNot",status);
    editor.commit();
}








    public void ClearAll() {
        editor.clear();
        editor.commit();
    }


}
