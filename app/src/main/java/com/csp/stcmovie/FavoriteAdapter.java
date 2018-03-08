package com.csp.stcmovie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.csp.stcmovie.LocalDB.DBConnection;

import java.util.ArrayList;

/**
 * Created by a-alt on 07/03/18.
 */

public class FavoriteAdapter extends BaseAdapter {

    Context context;
    ArrayList<favAdapter> list;
    private DBConnection dbConnection;
    private Activity activity;

    public FavoriteAdapter(Context context, ArrayList<favAdapter> list, Activity activity) {
        this.context = context;
        this.list = list;
        dbConnection = new DBConnection(context);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        System.out.println(list.get(i).getId());

        if (view == null) {
            view = View.inflate(context, R.layout.activity_favorite, null);
        }

        TextView favtitle_tv = (TextView) view.findViewById(R.id.favtitle);
        ImageButton deleate_btn = (ImageButton) view.findViewById(R.id.deleate);

        System.out.println(list.get(i).getName());

        favtitle_tv.setText(list.get(i).getName());

        deleate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   deleteDB(list.get(i).getId());
            }
        });

        return view;
    }

    private void deleteDB(int id) {
        SQLiteDatabase database = dbConnection.getWritableDatabase();
        database.delete("movie", "id = " + id, null);
        context.startActivity(new Intent(context, favorite.class));
        activity.finish();
//        Cursor cursor = database.rawQuery("DELETE from ",null);
    }


}
