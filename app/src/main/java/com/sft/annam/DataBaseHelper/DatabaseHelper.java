package com.sft.annam.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sft.annam.Utilities.Config;


/**
 * Created by JESNA on 7/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "annamfarmer.db";
    private static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseHelperFor_Machineries.CREATE_TABLE_MACHINELIST);
        Config.LogDebug("table created", "" + DataBaseHelperFor_Machineries.TABLE_MACHINE_LIST);
        sqLiteDatabase.execSQL(DataBaseHelperForBookingMachineries_List.CREATE_TABLE_MACHINELIST_BOOKING);
        Config.LogDebug("table created", "" + DataBaseHelperForBookingMachineries_List.TABLE_MACHINE_LIST_BOOKING);
        sqLiteDatabase.execSQL(DatabaseHelperFor_Book.CREATE_TABLE_FARMER_VIEW);
        Config.LogDebug("table created", "" + DatabaseHelperFor_Book.TABLE_FARMER_VIEW);
        sqLiteDatabase.execSQL(DatabaseHelperFor_Book_Later.CREATE_TABLE_FARMER_LATER_VIEW);
        Config.LogDebug("table created", "" + DatabaseHelperFor_Book_Later.CREATE_TABLE_FARMER_LATER_VIEW);
        sqLiteDatabase.execSQL(DataBaseHelperFor_location.CREATE_TABLE_FARMER_LOCATION);
        Config.LogDebug("table created", "" + DataBaseHelperFor_location.CREATE_TABLE_FARMER_LOCATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version, int new_version) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseHelperFor_Machineries.TABLE_MACHINE_LIST);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseHelperForBookingMachineries_List.TABLE_MACHINE_LIST_BOOKING);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseHelperFor_Book.TABLE_FARMER_VIEW);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseHelperFor_Book_Later.TABLE_FARMER_LATER_VIEW);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseHelperFor_location.TABLE_FARMER_LOCATION);


    }
}
