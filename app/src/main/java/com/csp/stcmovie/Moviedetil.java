package com.csp.stcmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by a-alt on 08/03/18.
 */

public class Moviedetil extends AppCompatActivity {

    private ImageView image;

    private TextView title, date, rating, overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        title = (TextView) findViewById(R.id.title);

            title.setText(Temp.getTmptitle());

    }
}
