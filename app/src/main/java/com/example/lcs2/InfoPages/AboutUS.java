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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lcs2.FindItems;
import com.example.lcs2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutUS extends AppCompatActivity {


    CircleImageView circleImageView;
    String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_u_s);
        Intent intent = getIntent();
        intent.getAction();
        circleImageView = (CircleImageView)findViewById(R.id.home_profile_pic);
        // Load user profile picture
        image_url = intent.getStringExtra("image");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.dontAnimate();
        Glide.with(getApplicationContext()).load(image_url).into(circleImageView);
    }


    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(AboutUS.this, FindItems.class);
        myIntent.putExtra("image", image_url);
        AboutUS.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(AboutUS.this, Maps.class);
        AboutUS.this.startActivity(myIntent);
        finish();
    }

}
