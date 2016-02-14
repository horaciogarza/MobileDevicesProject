package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by diego on 08/02/2016.
 */
public class SQLHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 5;
    private static ArrayList<String> databasesCollection;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + PersonalInfoDBSchema.UserDataTable.NAME + " (" + PersonalInfoDBSchema.UserDataTable.Cols.getCreateQueryCols() + ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + EmergencyPhoneNumDBSchema.EmergencyPhoneNumTable.NAME + " (" + EmergencyPhoneNumDBSchema.EmergencyPhoneNumTable.Cols.getCreateQueryCols() + ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MedicalDataDBSchema.MedicalDataTable.NAME + " (" + MedicalDataDBSchema.MedicalDataTable.Cols.getCreateQueryCols() + ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + InCaseOfEmergencyDBSchema.InCaseOfEmergencyTable.NAME + " (" + InCaseOfEmergencyDBSchema.InCaseOfEmergencyTable.Cols.getCreateQueryCols() + ")");


        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'Tbl%'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Log.i("TABLE: ", c.getString(0));
                databasesCollection.add(c.getString(0));
                c.moveToNext();
            }
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PersonalInfoDBSchema.UserDataTable.NAME);
        onCreate(db);

    }

    public boolean isUserInfo() {

        SQLiteDatabase db = getWritableDatabase();


        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name LIKE 'Tbl%'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Log.i("TABLES: ", c.getString(0));

                c.moveToNext();
            }
        }


        return !(c.getCount() == 0);

    }

    public boolean insertData(String table, String[] data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long res = -1;
        switch (table) {
            case PersonalInfoDBSchema.UserDataTable.NAME:
                if (data.length == 10) {
                    for (int i = 0; i < PersonalInfoDBSchema.UserDataTable.Cols.COL.length; i++) {
                        contentValues.put(PersonalInfoDBSchema.UserDataTable.Cols.COL[i], data[i]);
                    }
                    res = db.insert(PersonalInfoDBSchema.UserDataTable.NAME, null, contentValues);
                } else {
                    System.out.println("Error");
                }
                break;

        }
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
}
