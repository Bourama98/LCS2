/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */

package com.example.lcs2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Launcher extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        AccessToken token = AccessToken.getCurrentAccessToken();

         if(firebaseUser != null || token != null){
             progressBar.setVisibility(View.GONE);
             Intent intent1 = new Intent(getBaseContext(), FindItems.class);
             intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
             startActivity(intent1);
         }

         else {

             Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
             intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
             Launcher.this.startActivity(intent2);
         }
        finish();

    }
}