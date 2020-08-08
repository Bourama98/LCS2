package com.example.lcs2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    private Button register;
    private TextView loginPage, fullName;
    private EditText email, phone, passWord;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userID;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (Button)findViewById(R.id.Register);
        loginPage = (TextView)findViewById(R.id.loginPage);
        fullName = (TextView)findViewById(R.id.fullName);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        passWord = (EditText)findViewById(R.id.passWord);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sFullName = fullName.getText().toString().trim();
                String sEmail = email.getText().toString().trim();
                String sPassWord = passWord.getText().toString().trim();
                final String sPhone = phone.getText().toString().trim();
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

                firebaseAuth.createUserWithEmailAndPassword(sEmail, sPassWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference databaseReference = firebaseDatabase.getReference();
                            //Map<String, User> userMap = new HashMap<>();
                            User2 user = new User2(userID, sFullName, sPhone);
                            //userMap.put(userID, user);
                            databaseReference.child("users").child(userID).setValue(user);

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Registration.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });


        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                Registration.this.startActivity(intent);
                finish();
            }
        });

    }
}