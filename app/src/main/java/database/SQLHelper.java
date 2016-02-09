package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by diego on 08/02/2016.
 */
public class SQLHelper extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "data.db";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("create table "+UserDataDBSchema.UserDataTable.NAME+" ("+UserDataDBSchema.UserDataTable.Cols.COL[1]+" text, "+
                                                                            UserDataDBSchema.UserDataTable.Cols.COL[2]+" text, "+
                                                                            UserDataDBSchema.UserDataTable.Cols.COL[3]+" text, "+
                                                                            UserDataDBSchema.UserDataTable.Cols.COL[4]);*/
        db.execSQL("create table if not exists " + UserDataDBSchema.UserDataTable.NAME + " (" + UserDataDBSchema.UserDataTable.Cols.getCreateQueryCols() + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS "+UserDataDBSchema.UserDataTable.NAME);
        onCreate(db);
    }

    public boolean isUserInfo(){
        SQLiteDatabase db = getWritableDatabase();
        boolean res = false;
        Cursor cursor = db.rawQuery("SELECT * FROM "+UserDataDBSchema.UserDataTable.NAME, null);

        if(cursor.getCount() == 0){
            res = false;
        }
        if(cursor.getCount() > 0){
            res = true;
        }
        return res;
    }

    public boolean insertData(String table, String[] data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long res = -1;
        switch(table){
            case UserDataDBSchema.UserDataTable.NAME:
                if(data.length == 10) {
                    for (int i = 0; i < UserDataDBSchema.UserDataTable.Cols.COL.length; i++) {
                        contentValues.put(UserDataDBSchema.UserDataTable.Cols.COL[i], data[i]);
                    }
                    res = db.insert(UserDataDBSchema.UserDataTable.NAME, null, contentValues);
                }else{
                    System.out.println("Error");
                }
                break;

        }
        if(res == -1){
            return false;
        }else {
            return true;
        }
    }
}
