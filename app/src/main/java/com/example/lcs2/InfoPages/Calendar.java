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

import com.example.lcs2.FindItems;
import com.example.lcs2.R;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(Calendar.this, FindItems.class);
        Calendar.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(Calendar.this, Maps.class);
        Calendar.this.startActivity(myIntent);
        finish();
    }
}
