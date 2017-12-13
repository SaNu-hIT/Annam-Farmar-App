package com.sft.annam.DataBaseHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;


import com.sft.annam.Home;

import com.sft.annam.Model.Machineries_Model;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/22/2016.
 */
public class DataBaseHelperFor_Machineries {

    public static final String TABLE_MACHINE_LIST = "machine_list";
    private static final String KEY_MACHINE_ID = "machine_id";
    private static final String KEY_MACHINE_TYPE = "machine_type";
    private static final String KEY_MACHINE_NAME = "machine_name";
    private static final String KEY_MACHINE_RATEPERHOUR = "rate_per_hour";
    private static final String KEY_MACHINE_RATEPER_DAY = "rate_per_day";
    private static final String KEY_MACHINE_DESCRIPTION = "description";
    private static final String KEY_MACHINE_FEATURE = "machine_feature";
    private static final String KEY_MACHINE_IMAGE = "image";


    private static final String KEY_MACHINE_TREE_CLIEMB = "rate_per_tree_climb";
    private static final String KEY_MACHINE_TREE_CLEAN = "rate_per_tree_clean";
    private static final String KEY_MACHINE_COCUNUT = "rate_per_coconut_kg";
    private static final String KEY_MACHINE_PRESTISIDE= "rate_per_pesticide_kg";
    private static final String KEY_MACHINE_SPEC1 = "specification1";
    private static final String KEY_MACHINE_SPEC2 = "specification2";
    private static final String KEY_MACHINE_SPEC3 = "specification3";






    public static final String CREATE_TABLE_MACHINELIST = "CREATE TABLE "
            + TABLE_MACHINE_LIST + "(" +KEY_MACHINE_ID+ " TEXT,"+ KEY_MACHINE_TYPE + " TEXT,"
            + KEY_MACHINE_NAME + " TEXT,"
            + KEY_MACHINE_RATEPERHOUR + " TEXT,"
            + KEY_MACHINE_RATEPER_DAY + " TEXT,"
            + KEY_MACHINE_DESCRIPTION + " TEXT,"
            + KEY_MACHINE_FEATURE + " TEXT,"
            + KEY_MACHINE_IMAGE + " TEXT ,"
            + KEY_MACHINE_TREE_CLIEMB + " TEXT,"
            + KEY_MACHINE_TREE_CLEAN + " TEXT,"
            + KEY_MACHINE_COCUNUT + " TEXT,"
            + KEY_MACHINE_PRESTISIDE + " TEXT,"
            + KEY_MACHINE_SPEC1 + " TEXT,"
            + KEY_MACHINE_SPEC2 + " TEXT,"
            + KEY_MACHINE_SPEC3 + " TEXT" + ")";

    public static ArrayList<Machineries_Model> getMachinesList(FragmentActivity activity) {
        Machineries_Model machines;
        ArrayList<Machineries_Model> machine_list_Model_Array = new ArrayList<Machineries_Model>();

        DatabaseHelper dbHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MACHINE_LIST;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    machines = new Machineries_Model( cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_TYPE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_NAME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_RATEPERHOUR)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_RATEPER_DAY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_FEATURE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_IMAGE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_TREE_CLIEMB)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_TREE_CLEAN)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_COCUNUT)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_PRESTISIDE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_SPEC1)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_SPEC2)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_SPEC3)));
                    machine_list_Model_Array.add(machines);
                } while (cursor.moveToNext());
            }
        }
        db.close();

        return machine_list_Model_Array;

    }


      /*  public Machineries_Model(String machine_id, String machine_type,
                             String machine_name, String rate_per_hour,
                             String rate_per_day, String description, String image) {
*/

    public static boolean addService(Home context, ArrayList<Machineries_Model> service_listing_model_array) {
        long machine = 0;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        if (db != null) {
            if (service_listing_model_array != null && !service_listing_model_array.isEmpty()) {
                for (int i = 0; i < service_listing_model_array.size(); i++) {

                    ContentValues cv = new ContentValues();
                    cv.put(KEY_MACHINE_ID, service_listing_model_array.get(i).getMachine_id());
                    cv.put(KEY_MACHINE_TYPE,service_listing_model_array.get(i).getMachine_type());
                    cv.put(KEY_MACHINE_NAME, service_listing_model_array.get(i).getMachine_name());
                    cv.put(KEY_MACHINE_RATEPERHOUR, service_listing_model_array.get(i).getRate_per_hour());
                    cv.put(KEY_MACHINE_RATEPER_DAY, service_listing_model_array.get(i).getRate_per_day());
                    cv.put(KEY_MACHINE_DESCRIPTION, service_listing_model_array.get(i).getDescription());
                    cv.put(KEY_MACHINE_FEATURE, service_listing_model_array.get(i).getMachine_feature());
                    cv.put(KEY_MACHINE_IMAGE, service_listing_model_array.get(i).getImage());
                    cv.put(KEY_MACHINE_TREE_CLIEMB, service_listing_model_array.get(i).getRate_per_tree_climb());
                    cv.put(KEY_MACHINE_TREE_CLEAN, service_listing_model_array.get(i).getRate_per_tree_clean());
                    cv.put(KEY_MACHINE_COCUNUT, service_listing_model_array.get(i).getRate_per_coconut_kg());
                    cv.put(KEY_MACHINE_PRESTISIDE, service_listing_model_array.get(i).getRate_per_pesticide_kg());
                    cv.put(KEY_MACHINE_SPEC1, service_listing_model_array.get(i).getSpecification1());
                    cv.put(KEY_MACHINE_SPEC2, service_listing_model_array.get(i).getSpecification2());
                    cv.put(KEY_MACHINE_SPEC3, service_listing_model_array.get(i).getSpecification3());



                    machine = db.insert(TABLE_MACHINE_LIST, null, cv);



                }
            }
            if (machine > 0) {

                return true;
            } else {
                return false;
            }
        }
        db.close();
        return false;
    }
}
