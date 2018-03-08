package com.csp.stcmovie;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csp.stcmovie.LocalDB.DBConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a-alt on 02/03/18.
 */

public class MoviesAdapter extends ArrayAdapter {

    private final int page;
    private List<PopularMovie> movieDetailsList;
    TextView movieName;
    ImageView image;
    TextView description;
    TextView rel;
    private int resource;
    ImageView sa;
    ListView listViewDetil;




    private Context context;

    private DBConnection dbConnection;

    public MoviesAdapter(Context context, int resource, List<PopularMovie> PoplarMo, int page) {
        super(context, resource, PoplarMo);
        this.context = context;
        this.movieDetailsList = PoplarMo;
        this.resource = resource;
        this.page = page;
        dbConnection = new DBConnection(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PopularMovie details = movieDetailsList.get(position);


        View view = LayoutInflater.from(context).inflate(R.layout.listmovieadpater, parent, false);


        movieName = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.poster);
        description = (TextView) view.findViewById(R.id.desc);
        rel = (TextView) view.findViewById(R.id.rels);
        sa = (ImageView) view.findViewById(R.id.save);
        listViewDetil = (ListView)view.findViewById(R.id.list_mov) ;


        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMovie(details.getOriginalTitle());

            }
        });


        movieName.setText(details.getOriginalTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w200/" + details.getPosterPath()).into(image);
        description.setText(details.getOverview());
        rel.setText(details.getReleaseDate());

        return view;
    }

    @Nullable
    @Override
    public PopularMovie getItem(int position) {
        return movieDetailsList.get(position);
    }

    private void insertMovie(String name) {
        SQLiteDatabase database = dbConnection.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);

        database.insertWithOnConflict("movie", null, values, SQLiteDatabase.CONFLICT_REPLACE);
        database.close();
    }


}