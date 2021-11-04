package com.example.socialite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FriendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIEND = "friend";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_FRIEND + " ( " + ID;
        sqlCreate += " integer primary key autoincrement, " + FIRST_NAME;
        sqlCreate += " text, " + LAST_NAME;
        sqlCreate += " text, " + EMAIL + " text )";

        db.execSQL(sqlCreate);
    }

    public void insertFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIEND + " values (null, '" + friend.getFirstName() +"', '" +
                friend.getLastName() + "', '" + friend.getEmail() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friend> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIEND;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);

        ArrayList<Friend> friends = new ArrayList<>();

        while (cursor.moveToNext()) {
            Friend currentFriend = new Friend(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            friends.add(currentFriend);
        }
        db.close();
        return friends;

    }

    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIEND + " where " + ID + " = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String firstName, String lastName, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_FRIEND + " set " + FIRST_NAME + " = '" + firstName + "', "
                + LAST_NAME + " = '" + lastName + "', " + EMAIL + " = '" + email + "' where " + ID + " = " + id;
        //sqlUpdate += LAST_NAME + " = '" + lastName + "', ";
        //sqlUpdate += EMAIL + " = '" + email + "' where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }
    public void searchByEmail(int id, String firstName, String lastName, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "select from " + TABLE_FRIEND + " where " + ID + " = " + id + " or "+
                LAST_NAME + " = '" + lastName + "' or " + FIRST_NAME + " = '" + firstName;
        //sqlUpdate += LAST_NAME + " = '" + lastName + "', ";
        //sqlUpdate += EMAIL + " = '" + email + "' where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_FRIEND );
        // Re-create tables
        onCreate( db );
    }

}