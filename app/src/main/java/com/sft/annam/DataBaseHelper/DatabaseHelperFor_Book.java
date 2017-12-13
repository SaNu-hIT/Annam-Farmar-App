package com.sft.annam.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sft.annam.Model.BookNowFrag_model;


/**
 * Created by JESNA on 7/29/2016.
 */
public class DatabaseHelperFor_Book {




    public static final String TABLE_FARMER_VIEW = "farmer_book";

    private static final String KEY_FARMER_LOCATION = "farmer_loc";
    private static final String KEY_FARMER_DATE = "far_date";
    private static final String KEY_FARMER_TIME = "far_time";
    private static final String KEY_FAR_LANDAREA = "far_land_area";
    private static final String KEY_FAR_DAYS = "far_days";
    private static final String KEY_FAR_MIN = "far_min";
    private static final String KEY_FAR_HRS = "far_hrs";
    private static final String KEY_FAR_LATI = "far_latitude";
    private static final String KEY_FAR_LONGI = "far_longitu";
    private static final String KEY_FAR_DAY_HOUR_VALUE = "far_dayhour";
    private static final String KEY_MACHINE_TYPE_ID = "far_machine_type_id";

    private static final String KEY_FAR_KRISHIBHAVAN_ID = "far_krishibhavanid";


    public static final String CREATE_TABLE_FARMER_VIEW = "CREATE TABLE "
            + TABLE_FARMER_VIEW
            + "("
            + KEY_FARMER_LOCATION
            + " TEXT,"
            + KEY_FARMER_DATE
            + " TEXT,"
            + KEY_FARMER_TIME
            + " TEXT,"
            + KEY_FAR_LANDAREA
            + " TEXT,"
            + KEY_FAR_DAYS
            + " TEXT,"
            + KEY_FAR_MIN
            + " TEXT,"
            + KEY_FAR_HRS
            + " TEXT,"
            + KEY_FAR_LATI
            + " TEXT,"
            + KEY_FAR_LONGI
            + " TEXT,"
            + KEY_FAR_DAY_HOUR_VALUE
            + " TEXT,"
            + KEY_MACHINE_TYPE_ID
            + " TEXT,"
            + KEY_FAR_KRISHIBHAVAN_ID
            + " TEXT"
            + ")";

    public static  boolean  addfarmer(Context context, BookNowFrag_model profile_model_offl) {

        DatabaseHelper dataBaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();


        if (sqliteDatabase != null) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_FARMER_LOCATION, profile_model_offl.getFar_location());
            cv.put(KEY_FARMER_DATE, profile_model_offl.getFar_date());
            cv.put(KEY_FARMER_TIME, profile_model_offl.getFar_time());
            cv.put(KEY_FAR_LANDAREA, profile_model_offl.getFar_land_area());
            cv.put(KEY_FAR_DAYS, profile_model_offl.getFar_days());
            cv.put(KEY_FAR_MIN, profile_model_offl.getFar_min());
            cv.put(KEY_FAR_HRS, profile_model_offl.getFar_hrs());
            cv.put(KEY_FAR_LATI, profile_model_offl.getFar_latitude());
            cv.put(KEY_FAR_LONGI, profile_model_offl.getFar_longitude());
            cv.put(KEY_FAR_DAY_HOUR_VALUE, profile_model_offl.getDay_hr_value());
            cv.put(KEY_MACHINE_TYPE_ID, profile_model_offl.getFar_mac_type_id());

            cv.put(KEY_FAR_KRISHIBHAVAN_ID, profile_model_offl.getFar_krishibhavanid());



            long i = sqliteDatabase.insert(TABLE_FARMER_VIEW, null, cv);

            if (i > 0) {
                return true;
            } else {

                return false;

            }

        }

        sqliteDatabase.close();
        return false;
    }

    public static  BookNowFrag_model getProfileOffline(Context context){

        DatabaseHelper dataBaseHelper=new DatabaseHelper(context);


        BookNowFrag_model profilemodel_offline = null;


        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();
        if (sqliteDatabase != null) {
            String selectQuery = "SELECT * FROM "
                    + TABLE_FARMER_VIEW;


            Cursor cursor = sqliteDatabase.rawQuery(selectQuery, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        profilemodel_offline=new BookNowFrag_model(
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LOCATION)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_DATE)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_TIME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LANDAREA)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_DAYS)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_MIN)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_HRS)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LATI)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LONGI)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_DAY_HOUR_VALUE)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_TYPE_ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_KRISHIBHAVAN_ID))

                        );
                    } while (cursor.moveToNext());
                }
            }
        }
        sqliteDatabase.close();

        return profilemodel_offline;


    }

}
