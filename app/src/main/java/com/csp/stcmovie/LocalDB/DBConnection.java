package com.csp.stcmovie.LocalDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a-alt on 07/03/18.
 */

public class DBConnection extends SQLiteOpenHelper {
    private final static String dbName = "movie";
    private final static int version = 1;

    public DBConnection(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS movie (name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP table if exists movie");
    }

}

