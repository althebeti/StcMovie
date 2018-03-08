package com.csp.stcmovie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.csp.stcmovie.LocalDB.DBConnection;

import java.util.ArrayList;


public class favorite extends AppCompatActivity {

    String API_KEY = "b9e4bd75db9a38fb5ef7b108c506b3db";
    public static String language = "en-US";
    public static int page = 1;
    String URL = "http://api.themoviedb.org";
    String reagon;
    int pic;
    private ListView listviewfav;


    TextView favname;
    ImageView favposter;
    TextView favdesc;
    TextView favrel;

    String titlenam, descS, relasS, posterR;

    DBConnection dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listfavorite);

        listviewfav = (ListView) findViewById(R.id.list_fav);
        dbConnection = new DBConnection(this);

        loadData();

    }

    public void loadData() {
        ArrayList<favAdapter> favArray = new ArrayList<favAdapter>();
        favAdapter favAdapter = new favAdapter();

        Cursor cursor = readMovie();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            favAdapter.setName(cursor.getString(cursor.getColumnIndex("name")));
            favArray.add(favAdapter);
            Log.d("Name",cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();
        }

        FavoriteAdapter adapter = new FavoriteAdapter(this, favArray, favorite.this);
        listviewfav.setAdapter(adapter);
    }


    private Cursor readMovie() {
        SQLiteDatabase database = dbConnection.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from movie", null);

        return cursor;
    }


}