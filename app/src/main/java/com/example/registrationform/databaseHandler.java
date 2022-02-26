package com.example.registrationform;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;


import java.util.ArrayList;

import java.util.List;

public class databaseHandler extends SQLiteOpenHelper {

    private static final int DB_Version = 4;
    private static final String DB_name = "userDetails";
    private static final String USER_DATA = "user";
    private static final String ID = "ID";
    private static final String Username = "Username";
    private static final String Full_Name = "Full_Name";
    private static final String Phone_Number = "Phone_Number";
    private static final String Email_Id = "Email";
    private static final String Password = "Password";
    private static final String Confirm_Password = "Confirm_Password";

    public databaseHandler(Context context) {
        super(context, DB_name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Table_UserDetails = "CREATE TABLE " + USER_DATA + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Username + " TEXT," + Full_Name + " TEXT,"
                + Phone_Number + " TEXT," + Email_Id + " TEXT," + Password + " TEXT," + Confirm_Password + " TEXT)";
        sqLiteDatabase.execSQL(Create_Table_UserDetails);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_DATA);
        onCreate(sqLiteDatabase);
    }

    // For Adding User
    void addUser(UserDatabase userDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put(ID, userDatabase.getId());
        contentValues.put(Username, userDatabase.getUsername());
        contentValues.put(Full_Name, userDatabase.getFullName());
        contentValues.put(Phone_Number, userDatabase.getPhoneNumber());
        contentValues.put(Email_Id, userDatabase.getEmail());
        contentValues.put(Password, userDatabase.getPassword());
        contentValues.put(Confirm_Password, userDatabase.getConfirmPassword());

        db.insert(USER_DATA, null, contentValues);
        db.close();
    }

    // To Fetch Single data
    UserDatabase getUserData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_DATA, new String[] {Username, Full_Name, Phone_Number, Email_Id, Password, Confirm_Password}, ID +"=?", new String[] {name}, null, null, null, null );
        if (cursor != null)
            cursor.moveToFirst();

        UserDatabase data = new UserDatabase(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

        db.close();
        return data ;
    }

    // To Fetch All Data
    public List<UserDatabase> getAllUser() {
        List<UserDatabase> userList = new ArrayList<UserDatabase>();

        String selectQuery = "SELECT * FROM " + USER_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserDatabase userData = new UserDatabase();
                userData.setId(cursor.getInt(0));
                userData.setUsername(cursor.getString(1));
                userData.setFullName(cursor.getString(2));
                userData.setPhoneNumber(cursor.getString(3));
                userData.setEmail(cursor.getString(4));
                userData.setPassword(cursor.getString(5));
                userData.setConfirmPassword(cursor.getString(6));
                userList.add(userData);
            }
            while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userList;
    }

    // To update single contact
    public int updateData(UserDatabase userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID, userData.getId());
        contentValues.put(Username, userData.getUsername());
        contentValues.put(Full_Name, userData.getFullName());
        contentValues.put(Phone_Number, userData.getPhoneNumber());
        contentValues.put(Email_Id, userData.getEmail());
        contentValues.put(Password, userData.getPassword());
        contentValues.put(Confirm_Password, userData.getConfirmPassword());

        return db.update(USER_DATA, contentValues, ID + "=?", new
                String[] {String.valueOf(userData.getId())});
    }

    // To delete single contact
    public void deleteUser(UserDatabase userData) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d("Name", userData.getUsername());
        db.delete(USER_DATA, ID + "=?", new String[] {String.valueOf(userData.getId())});
        db.close();
    }

    // Get user count
    public int getUserCounts() {
        String countQuery = "SELECT * FROM " + USER_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        return cursor.getCount();
    }


}
