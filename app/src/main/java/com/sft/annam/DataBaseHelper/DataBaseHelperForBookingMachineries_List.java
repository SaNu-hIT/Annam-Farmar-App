package com.sft.annam.DataBaseHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;


import com.sft.annam.Home;

import com.sft.annam.Model.MachineListModel_Booking;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/24/2016.
 *
 *
 */
public class DataBaseHelperForBookingMachineries_List {
    public static final String TABLE_MACHINE_LIST_BOOKING = "machine_list_booking";
    private static final String KEY_MACHINE_BOOKING_ID = "machine_booking_id";
    private static final String KEY_MACHINE_BOOKING_NAME = "machine_booking_name";
    private static final String KEY_MACHINE_BOOKING_TYPE = "machine_booking_type";
    private static final String KEY_MACHINE_BOOKING_RATEPERHOUR = "mac_booking_rate_per_hour";
    private static final String KEY_MACHINE_BOOKING_PICKUP = "mac_booking_pickup";
    private static final String KEY_MACHINE_BOOKING_RATEPER_DAY = "mac_bookng_rate_per_day";
    private static final String KEY_MACHINE_BOOKING_DESCRIPTION = "mac_book_description";
    private static final String KEY_MACHINE_BOOKING_IMAGE = "mac_image";


    public static final String CREATE_TABLE_MACHINELIST_BOOKING = "CREATE TABLE "
            + TABLE_MACHINE_LIST_BOOKING + "(" +KEY_MACHINE_BOOKING_ID+ " TEXT,"+
            KEY_MACHINE_BOOKING_NAME + " TEXT,"
            + KEY_MACHINE_BOOKING_TYPE + " TEXT,"
            + KEY_MACHINE_BOOKING_RATEPERHOUR + " TEXT,"
            + KEY_MACHINE_BOOKING_PICKUP + " TEXT,"
            + KEY_MACHINE_BOOKING_RATEPER_DAY + " TEXT,"
            + KEY_MACHINE_BOOKING_DESCRIPTION + " TEXT,"
            + KEY_MACHINE_BOOKING_IMAGE + " TEXT" + ")";

    public static ArrayList<MachineListModel_Booking> getMachinesbookingList(FragmentActivity activity) {
        MachineListModel_Booking machines_book;
        ArrayList<MachineListModel_Booking> machine_list_Model_Array = new ArrayList<MachineListModel_Booking>();

        DatabaseHelper dbHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MACHINE_LIST_BOOKING;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    machines_book = new MachineListModel_Booking
                            ( cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_ID)),

                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_NAME)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_TYPE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_RATEPERHOUR)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_PICKUP)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_RATEPER_DAY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(KEY_MACHINE_BOOKING_IMAGE)));
                    machine_list_Model_Array.add(machines_book);
                } while (cursor.moveToNext());
            }
        }
        db.close();

        return machine_list_Model_Array;

    }




    public static boolean addMachines(Home context, ArrayList<MachineListModel_Booking> machineListModel_bookings_array) {
        long machine = 0;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        if (db != null) {
            if (machineListModel_bookings_array != null && !machineListModel_bookings_array.isEmpty()) {
                for (int i = 0; i < machineListModel_bookings_array.size(); i++) {

                    ContentValues cv = new ContentValues();
                    cv.put(KEY_MACHINE_BOOKING_ID, machineListModel_bookings_array.get(i).getMachine_id());
                    cv.put(KEY_MACHINE_BOOKING_NAME,machineListModel_bookings_array.get(i).getMachine_name());
                    cv.put(KEY_MACHINE_BOOKING_TYPE,machineListModel_bookings_array.get(i).getMachine_type());
                    cv.put(KEY_MACHINE_BOOKING_RATEPERHOUR, machineListModel_bookings_array.get(i).getMachine_rate_hour());
                    cv.put(KEY_MACHINE_BOOKING_PICKUP, machineListModel_bookings_array.get(i).getMachine_pickup());
                    cv.put(KEY_MACHINE_BOOKING_RATEPER_DAY, machineListModel_bookings_array.get(i).getMachine_rate_perday());
                    cv.put(KEY_MACHINE_BOOKING_DESCRIPTION, machineListModel_bookings_array.get(i).getMachine_description());
                    cv.put(KEY_MACHINE_BOOKING_NAME, machineListModel_bookings_array.get(i).getMachine_image());

                    machine = db.insert(TABLE_MACHINE_LIST_BOOKING, null, cv);



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
