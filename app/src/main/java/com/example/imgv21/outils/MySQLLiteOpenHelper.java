package com.example.imgv21.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLLiteOpenHelper extends SQLiteOpenHelper {
    //proprities
    private String creation="CREATE TABLE profil("
            +"datemesure TEXT PRIMARY KEY,"
            +"poids INTEGER NOT NULL,"
            +"taille INTEGER NOT NULL,"
            +"age INTEGER NOT NULL,"
            +"sexe INTEGER NOT NULL);";

    /**
     * Constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLLiteOpenHelper(Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * If change dataBase
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
    }

    /**
     * If changing version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
