package com.sft.annam.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sft.annam.Model.Book_Later_Model;


/**
 * Created by JESNA on 7/29/2016.
 */
public class DatabaseHelperFor_Book_Later {


    public static final String TABLE_FARMER_LATER_VIEW = "farmer_book_Later";
    private static final String KEY_FARMER_LATER_LOCATION = "farmer_later_loc";
    private static final String KEY_FAR_LATER_LATI = "far_later_latitude";
    private static final String KEY_FAR_LATER_LONG = "far_later_longitu";
    private static final String KEY_FARMER_LATER__FROM_DATE = "far_from_date";
    private static final String KEY_FARMER_LATER__TO_DATE = "far_to_date";
    private static final String KEY_FARMER_LATER__LANDAREA = "far_landarea";
    private static final String KEY_FARMER_LATER_MACHINERY_TYPE = "far_mac_type";
    private static final String KEY_FARMER_LATER_KRISHI = "far_krish";


    public static final String CREATE_TABLE_FARMER_LATER_VIEW = "CREATE TABLE "
            + TABLE_FARMER_LATER_VIEW
            + "("
            + KEY_FARMER_LATER_LOCATION
            + " TEXT,"
            + KEY_FAR_LATER_LATI
            + " TEXT,"
            + KEY_FAR_LATER_LONG
            + " TEXT,"
            + KEY_FARMER_LATER__FROM_DATE
            + " TEXT,"
            + KEY_FARMER_LATER__TO_DATE
            + " TEXT,"
            + KEY_FARMER_LATER__LANDAREA
            + " TEXT,"
            + KEY_FARMER_LATER_MACHINERY_TYPE
            + " TEXT,"
            + KEY_FARMER_LATER_KRISHI
            + " TEXT"
            + ")";


    public static  boolean  add_booklater(Context context, Book_Later_Model book_model_offl) {

        DatabaseHelper dataBaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();


        if (sqliteDatabase != null) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_FARMER_LATER_LOCATION, book_model_offl.getFar_later_loc());
            cv.put(KEY_FAR_LATER_LATI, book_model_offl.getFar_later_lat());
            cv.put(KEY_FAR_LATER_LONG, book_model_offl.getFar_later_long());
            cv.put(KEY_FARMER_LATER__FROM_DATE, book_model_offl.getFar_later_fromdate());
            cv.put(KEY_FARMER_LATER__TO_DATE, book_model_offl.getFar_later_todate());
            cv.put(KEY_FARMER_LATER__LANDAREA, book_model_offl.getFar_later_landarea());
            cv.put(KEY_FARMER_LATER_MACHINERY_TYPE, book_model_offl.getFar_later_mac_type());
            cv.put(KEY_FARMER_LATER_KRISHI, book_model_offl.getFar_later_krishi());



            long i = sqliteDatabase.insert(TABLE_FARMER_LATER_VIEW, null, cv);

            if (i > 0) {
                return true;
            } else {

                return false;

            }

        }

        sqliteDatabase.close();
        return false;
    }

    public static  Book_Later_Model getBooklaterOffline(Context context){

        DatabaseHelper dataBaseHelper=new DatabaseHelper(context);


        Book_Later_Model book_later_model = null;


        SQLiteDatabase sqliteDatabase = dataBaseHelper.getWritableDatabase();
        if (sqliteDatabase != null) {
            String selectQuery = "SELECT * FROM "
                    + TABLE_FARMER_LATER_VIEW;


            Cursor cursor = sqliteDatabase.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        book_later_model=new Book_Later_Model(
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER_LOCATION)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LATER_LATI)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAR_LATER_LONG)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER__FROM_DATE)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER__TO_DATE)),

                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER__LANDAREA)),
                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER_MACHINERY_TYPE)),



                                cursor.getString(cursor.getColumnIndexOrThrow(KEY_FARMER_LATER_KRISHI))

                        );

                    } while (cursor.moveToNext());
                }
            }
        }
        sqliteDatabase.close();

        return book_later_model;


    }

}
