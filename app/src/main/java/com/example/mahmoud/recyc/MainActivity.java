package com.example.mahmoud.recyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyAdapter.OnItemClickListener {
    ArrayList <movie> movies;
    private RecyclerView rvlist;
    private RecyAdapter adapter;

    ImageView flagv;
    TextView tvnanecoun,reldate,lang;
    RatingBar rateb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvlist = findViewById(R.id.rvdata);
//        String[] country = {"Egypt", "Sudi Aribia", "Oman", "Jordan", "Chad", "Bahrin", "Iraq","Argentina", "Australia", "Belgien", "Brazile", "France", "Germany", "GreatBratin", "Italy", "Kanda", "Neterland", "Russia", "SouthKorea", "Spain", "Switzerland", "USA"};
//        int[] counId = {R.drawable.egypt, R.drawable.sudi, R.drawable.oman, R.drawable.jordan, R.drawable.chad, R.drawable.bahrin, R.drawable.iraq,R.drawable.argentina, R.drawable.australia, R.drawable.belgien, R.drawable.brazile, R.drawable.france, R.drawable.germany, R.drawable.greatbratin, R.drawable.italy, R.drawable.kanda, R.drawable.neterland, R.drawable.russia, R.drawable.southkorea, R.drawable.spain, R.drawable.switzerland, R.drawable.usa};
//        conteries = new ArrayList <>();
//        for (int i = 0; i < country.length && i < counId.length; i++) {
//            Data cuda = new Data();
//            cuda.str = country[i];
//            cuda.nu = counId[i];
//            conteries.add(cuda);
//        }

//        adapter = new RecyAdapter(conteries, MainActivity.this);
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=5b7b90a6e3498ae7267efbfece0ddf15";
        url(url);
        rvlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvlist.setAdapter(adapter);

        RecyAdapter.setOnItemClickListener(MainActivity.this);

        flagv = findViewById(R.id.ivflag);
        tvnanecoun = findViewById(R.id.tvnamecoun);
        reldate=findViewById(R.id.tvoodate);
        lang=findViewById(R.id.tvlang);
        rateb=findViewById(R.id.rbrate);
    }

    @Override
    public void onItemClick(int position) {
        movie d = movies.get(position);
        tvnanecoun.setText(d.title);
        reldate.setText(d.relase_date);
        lang.setText(d.lng);
        rateb.setRating(Float.parseFloat(d.vote_avg)/2);
        rateb.setEnabled(false);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+d.poster_path).into(flagv);
    }
    ////////

    public void url(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener <String>() {
            @Override
            public void onResponse(String response) {
                handlejson(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }


    public void handlejson(String response) {

        movies = new ArrayList <movie>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray results = jsonObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject j = results.getJSONObject(i);

                movie movie = new movie(j.getString("adult"), j.getString("backdrop_path"), j.getString("id"), j.getString("original_language"), j.getString("original_title"), j.getString("overview"), j.getString("poster_path"), j.getString("release_date"), j.getString("vote_average"), j.getString("vote_count"), j.getString("popularity"));

                movies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("hady", "1");

        rvlist = findViewById(R.id.rvdata);
        Log.e("hady", "2");

        RecyAdapter adapter = new RecyAdapter( movies,MainActivity.this);
        Log.e("hady", "3");
        rvlist.setAdapter(adapter);
        rvlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvlist.setAdapter(adapter);





    }
}