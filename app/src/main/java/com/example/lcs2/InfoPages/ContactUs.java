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

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(ContactUs.this, FindItems.class);
        ContactUs.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(ContactUs.this, Maps.class);
        ContactUs.this.startActivity(myIntent);
        finish();
    }

}
