package com.example.lcs2;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lcs2.DBManagement.DBHelper;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class Logout extends AppCompatActivity {

    private LoginButton faceBookLogoutButton;
    private Button home, logout;
    private CircleImageView circleImageView;

    private TextView profileName, profileEmail;
    FacebookSdk FB = new FacebookSdk();

    private CallbackManager callbackManager;
    String image_url;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        faceBookLogoutButton = (LoginButton)findViewById(R.id.facebook_login);
        circleImageView = (CircleImageView)findViewById(R.id.profile_pic);
        profileName = (TextView)findViewById(R.id.profile_name);
        profileEmail = (TextView)findViewById(R.id.profile_email);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        logout = (Button)findViewById(R.id.logout);
        home = (Button)findViewById(R.id.home);
        isUserLoggedIn();
        if(firebaseUser != null){
            profileEmail.setText(firebaseUser.getEmail());
            enableHomeButton();
            faceBookLogoutButton.setVisibility(View.INVISIBLE);
        }

       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               Intent intent = new Intent(getBaseContext(), MainActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               Logout.this.startActivity(intent);
               finish();


           }
       });







    }

//    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
//        {
//            if (currentAccessToken == null){// If user is not logged in set the profile display text views to null
//                profileName.setText("");
//                profileEmail.setText("");
//                profileName.setVisibility(TextView.INVISIBLE);
//                profileEmail.setVisibility(TextView.INVISIBLE);
//                circleImageView.setImageResource(0);
//
//                home.setClickable(false);
//
//
//                Intent intent = new Intent(Logout.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                Logout.this.startActivity(intent);
//                finish();
//                Toast.makeText(Logout.this, "User Logged Out", Toast.LENGTH_SHORT).show();
//
//
//
//            }
//
//             else{
//                loadUserProfile(currentAccessToken);
//            }
//        }
//    };

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
                    //Check if user is on our database if not create
                    dbHelper = new DBHelper(Logout.this, null, null, 6);
                    boolean isUser = dbHelper.searchUser(email);
//                    if(isUser == false){
//                        User user = new User(first_name, last_name, email, "Testing");
//                        dbHelper.addUser(user);
//                    }

                    profileEmail.setText(email);
                    profileName.setText(first_name +" "+ last_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();
                    Glide.with(getApplicationContext()).load(image_url).into(circleImageView);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name, last_name, email, id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private  void isUserLoggedIn() // Check if user is still logged in if so grant access to the app
    {
        if(AccessToken.getCurrentAccessToken() != null){
           loadUserProfile(AccessToken.getCurrentAccessToken());

            logout.setVisibility(View.INVISIBLE);
            logout.setClickable(false);
            enableHomeButton();

        }


    }


    private void enableHomeButton(){
        home.setClickable(true);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindItems.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("image", image_url);
                Logout.this.startActivity(intent);

                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {// Stop the user from using the device back button after logging out

        finish();

    }
}