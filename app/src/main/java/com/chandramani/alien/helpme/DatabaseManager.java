package com.chandramani.alien.helpme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String Phone_number = "number";
    private final static String DB_NAME = "Phonebook";
    private static final String TABLE_Name= "Numberss";

    DatabaseManager(Context context){
        super(context, DB_NAME,null,1);
        Log.d("test5","second activity test 4");
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable= "CREATE TABLE IF NOT EXISTS " + TABLE_Name + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Phone_number + " TEXT" + " );";

        Log.d("test6","second activity test 5");

        database.execSQL(createTable);
        Log.d("test7","second activity test 6");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);
        onCreate(sqLiteDatabase);
    }

    public void insertt(String str) {
        Log.d("test2","second activity test 1");

      //  boolean createSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
      //  Log.d("test3","second activity test 2");
        ContentValues values = new ContentValues();

        //  values.put(KEY_ID, information.getId());
        values.put(Phone_number, str);

    //    createSuccessful
        db.insert(TABLE_Name, null, values);
        Log.d("test4","second activity test 3");
        db.close();

        //  return createSuccessful;
    }


    public Cursor getcontact(){
        Log.d("test0","getcontaact ");
        String selectQuery = "SELECT " + Phone_number+ " FROM " + TABLE_Name;

        SQLiteDatabase db = this.getReadableDatabase();
        //  Log.d("test3","second activity test 2");
        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery(selectQuery,null);
        Log.d("testn","working till here");

        return cursor;
    }
    public long getcountt(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_Name);

        return count;

    }


}