package com.sft.annam.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sft.annam.Fragment.Location_MOdel;


/**
 * Created by JESNA on 8/9/2016.
 */
public class DataBaseHelperFor_location {

    public static final String TABLE_FARMER_LOCATION = "farmer_location";
    private static final String KEY_FARMER_LOCATION_CITY = "farmer_loc_city";
    private static final String KEY_FARMER_LOCATION_STATE= "farmer_state";
    private static final String KEY_FAR_LOCATION_COUNTRY = "farmer_countery";
    private static final String KEY_FAR_LOCATION_LATITUDE= "farmer_latitude";
    private static final String KEY_FAR_LOCATION_LONGITUDE= "farmer_longitude";



    public static final String CREATE_TABLE_FARMER_LOCATION = "CREATE TABLE "
            + TABLE_FARMER_LOCATION
            + "("
            + KEY_FARMER_LOCATION_CITY
            + " TEXT,"
            + KEY_FARMER_LOCATION_STATE
            + " TEXT,"
            + KEY_FAR_LOCATION_COUNTRY
            + " TEXT,"
            + KEY_FAR_LOCATION_LATITUDE
            + " TEXT,"
            + KEY_FAR_LOCATION_LONGITUDE
            + " TEXT"
            + ")";

    public static  boolean  addfarmerlocatin(Context context, Location_MOdel location_model_offline) {

        DatabaseHelper dataBaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();


        if (sqliteDatabase != null) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_FARMER_LOCATION_CITY, location_model_offline.getPlace());
            cv.put(KEY_FARMER_LOCATION_STATE, location_model_offline.getState());


            cv.put(KEY_FAR_LOCATION_COUNTRY, location_model_offline.getCountry());
            cv.put(KEY_FAR_LOCATION_LATITUDE, location_model_offline.getLatitude());

            cv.put(KEY_FAR_LOCATION_LONGITUDE, location_model_offline.getLongitude());




            long i = sqliteDatabase.insert(TABLE_FARMER_LOCATION, null, cv);

            if (i > 0) {
                return true;
            } else {

                return false;

            }

        }

        sqliteDatabase.close();
        return false;
    }

    public static  Location_MOdel getProfileOffline(Context context){

        DatabaseHelper dataBaseHelper=new DatabaseHelper(context);


        Location_MOdel locationmodel_offline = null;


        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();
        if (sqliteDatabase != null) {
            String selectQuery = "SELECT * FROM "
                    + TABLE_FARMER_LOCATION;


            Cursor cursor = sqliteDatabase.rawQuery(selectQuery, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        locationmodel_offline=new Location_MOdel(
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LOCATION_CITY)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LOCATION_STATE)),

                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LOCATION_COUNTRY)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LOCATION_LATITUDE)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LOCATION_LONGITUDE))

                        );

                    } while (cursor.moveToNext());
                }
            }
        }
        sqliteDatabase.close();

        return locationmodel_offline;


    }
}
