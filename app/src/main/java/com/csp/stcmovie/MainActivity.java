package com.csp.stcmovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.csp.stcmovie.Connecton.retrofit;

public class MainActivity extends AppCompatActivity {
    String API_KEY = "b9e4bd75db9a38fb5ef7b108c506b3db";
    public static String language = "en-US";
    public static int page = 1;
    String URL = "http://api.themoviedb.org";
    String reagon;
    int resource;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;


    List<MovieList> movieDetails;

    int pic;

    Context context;
    private ListView listViewUs;
    private ImageView sh;
    private ImageView fav;
    ArrayList<String> list = new ArrayList<>();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("APPNAME", MODE_PRIVATE);
        editor = preferences.edit();

//Retrieve the values
        Set<String> set = preferences.getStringSet("key", null);



        API APIsService = retrofit.create(API.class);

        listViewUs = (ListView) findViewById(R.id.list_mov);


        // sh = (ImageView)findViewById(R.id.share);



        Call<PopularMovieResponse> call = APIsService.getpopular(API_KEY, language, page, reagon);

        call.enqueue(new Callback<PopularMovieResponse>() {
            @Override
            public void onResponse(Call<PopularMovieResponse> call, final Response<PopularMovieResponse> response) {

                Log.d("kkdk", "onResponse: ");

                MoviesAdapter adapter = new MoviesAdapter(MainActivity.this, pic, response.body().getResults(), response.body().getPage());
                listViewUs.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PopularMovieResponse> call, Throwable t) {

                Log.d("kkdk", "onResponse: ");
            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite, menu);
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.edtSearch);
        return super.onPrepareOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_bar:


                handleMenuSearch();

                return true;

            case R.id.action_favorite:

                Intent i = new Intent(MainActivity.this, favorite.class);
                startActivity(i);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }


    protected void handleMenuSearch() {
        ActionBar action = getSupportActionBar();

        if (isSearchOpened) {

            action.setDisplayShowCustomEnabled(false);
            action.setDisplayShowTitleEnabled(true);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            isSearchOpened = false;
        } else {

            action.setDisplayShowCustomEnabled(true);
            action.setCustomView(R.layout.searchable);
            action.setDisplayShowTitleEnabled(false);

            edtSeach = (EditText) action.getCustomView().findViewById(R.id.edtSearch);
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });


            edtSeach.requestFocus();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);
            isSearchOpened = true;
        }
    }

    private void doSearch() {


    }

    public void share() {
        String shareBody = "http://www.imdb.com/";

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "STCMovie App ");

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}

