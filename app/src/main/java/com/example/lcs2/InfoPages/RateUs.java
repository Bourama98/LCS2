/*
 * CS-499
 * 3-2 Milestone Two: Enhancement One: Software Design and Engineering
 * Bourama Mangara
 * 19 July 2020
 */

package com.example.lcs2.InfoPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lcs2.DBManagement.DBHelper;
import com.example.lcs2.FindItems;
import com.example.lcs2.R;

import java.util.ArrayList;

public class RateUs extends AppCompatActivity {
    RatingBar appRating;
    DBHelper dbHelper;
    ArrayList<Integer> ratings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        appRating = (RatingBar)findViewById(R.id.appRating);
        ratings = new ArrayList<>();
        dbHelper = new DBHelper(this, null, null, 6);

        // Get and calculating rating
        ratings = dbHelper.getAllAppRating();
        int ratingTotal = 0;
        for(int i =0; i < ratings.size(); i++){
            ratingTotal = ratingTotal + ratings.get(i).intValue();
        }
        float ratingConvert = ratingTotal / ratings.size();
        appRating.setRating(ratingConvert);

        //Record rating on valute change
        appRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser == true) {
                    int ratingInt = Math.round(rating);
                    dbHelper.addAppRating(ratingInt);// Save rating in SQLITE
                    Toast.makeText(RateUs.this, "Thank you for your rating", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(RateUs.this, FindItems.class);
        RateUs.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(RateUs.this, Maps.class);
        RateUs.this.startActivity(myIntent);
        finish();
    }

}
