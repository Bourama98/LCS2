/*
 * CS-499
 * 3-2 Milestone Two: Enhancement One: Software Design and Engineering
 * Bourama Mangara
 * 19 July 2020
 */


package com.example.lcs2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lcs2.DBManagement.DBHelper;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    private LoginButton faceBookLoginButton;
    private Button home, login;
    private CircleImageView circleImageView;
    private TextView profileName, profileEmail, register;
    private EditText email, passWord;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private CallbackManager callbackManager;
    String image_url;
    String sEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();




        faceBookLoginButton = (LoginButton)findViewById(R.id.facebook_login);
        circleImageView = (CircleImageView)findViewById(R.id.profile_pic);
        profileName = (TextView)findViewById(R.id.profile_name);
        profileEmail = (TextView)findViewById(R.id.profile_email);
        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.passWord);
        login = (Button)findViewById(R.id.login);
        home = (Button)findViewById(R.id.home);
        register = (TextView)findViewById(R.id.register);
        progressBar = (ProgressBar)findViewById(R.id.progressBarLogin);

        sEmail = email.getText().toString().trim();

//        if(isUserLoggedIn() == true){
//
//            Intent intent = new Intent(MainActivity.this, FindItems.class);
//            intent.putExtra("image", image_url);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            MainActivity.this.startActivity(intent);
//            finish();
//        }
//        if(firebaseUser != null){
//            Intent intent = new Intent(getBaseContext(), FindItems.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            MainActivity.this.startActivity(intent);
//            finish();
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String sFullName = fullName.getText().toString().trim();
                String sEmail = email.getText().toString().trim();
                String sPassWord = passWord.getText().toString().trim();
                //String sPhone = phone.getText().toString().trim();
                if(TextUtils.isEmpty(sEmail)){
                    email.setError("Email is Required");
                    return;

                }
                if(TextUtils.isEmpty(sPassWord)){
                    passWord.setError("Password is Required");
                    return;
                }
                if(sPassWord.length() < 6){
                    passWord.setError("Password must be >=6");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

               firebaseAuth.signInWithEmailAndPassword(sEmail, sPassWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           Toast.makeText(MainActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getBaseContext(), FindItems.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           MainActivity.this.startActivity(intent);
                           finish();

                       }else {
                           Toast.makeText(MainActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }
                   }
               });
            }

        });

        callbackManager = CallbackManager.Factory.create();
        faceBookLoginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        faceBookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {// Initiate facebook login
            @Override
            public void onSuccess(LoginResult loginResult) { // if logging success send to the FindItems screen
              Intent intent = new Intent(getBaseContext(), FindItems.class);
              intent.putExtra("image", image_url);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
              MainActivity.this.startActivity(intent);
              finish();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
          if (currentAccessToken == null){// If user is not logged in set the profile display text views to null
              profileName.setText("");
              profileEmail.setText("");
              circleImageView.setImageResource(0);
              Intent intent = new Intent(MainActivity.this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
              MainActivity.this.startActivity(intent);
              Toast.makeText(MainActivity.this, "User Logged Out2", Toast.LENGTH_LONG).show();

              register.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                     startActivity(new Intent(getApplicationContext(), Registration.class));
                  }
              });

          }
          else { // Otherwise load user information
              loadUserProfile(currentAccessToken);
          }

        }
    };
    public void loadUserProfile(final AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {// Getting user data to populate the user profile with name, email and picture
                String first_name = object.getString("first_name");
                String last_name = object.getString("last_name");
                String email = object.getString("email");
                String id = object.getString("id");
                image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                profileEmail.setText(email);
                profileName.setText(first_name +" "+ last_name);
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.dontAnimate();
                Glide.with(getBaseContext()).load(image_url).into(circleImageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name, last_name, email, id");
        request.setParameters(parameters);
        request.executeAsync();
        Intent intent = new Intent(getApplicationContext(), FindItems.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("image", image_url);
        MainActivity.this.startActivity(intent);
        System.out.println(AccessToken.getCurrentAccessToken() + " Bourama2");

    }

    private Boolean isUserLoggedIn() // Check if user is still logged in if so grant access to the app
    {
    if(AccessToken.getCurrentAccessToken() != null){
       // System.out.println(AccessToken.getCurrentAccessToken() + " Bourama1");
        loadUserProfile(AccessToken.getCurrentAccessToken());
        return true;
    }

    else {
        return false;
    }


}
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {// Stop the user from using the device back button after logging out
        finish();
    }

}
