/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */

package com.example.lcs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.lcs2.InfoPages.Maps;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class PaymentDetails extends AppCompatActivity {

    TextView txtId, txtAmount, txtStatus;
    SharedPreferences sharedPreferences;
    ArrayList<CartItem> productArrayList;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    Date date = new Date();
    String date1 = String.valueOf(date);

    String totals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        Intent intent = getIntent();
        intent.getAction();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        totals = intent.getStringExtra("PaymentAmount");
        txtId = (TextView)findViewById(R.id.txtId);
        txtAmount = (TextView)findViewById(R.id.txtAmount);
        txtStatus = (TextView)findViewById(R.id.txtStatus);



        try {// Getting payment detail data from the intent to display to the user.
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        submitOrder();
    }

    private void showDetails(JSONObject response, String paymentAmount){// Method call to populate Id, status and amount textview
                                                                        // to display payment detail after approval
        try {
            txtId.setText(response.getString("id"));
            txtStatus.setText(response.getString("state"));
            txtAmount.setText("$"+ paymentAmount);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void navigateToAddProduct(View view){// On click back to add product activity
        Intent myIntent = new Intent(PaymentDetails.this, FindItems.class);
        PaymentDetails.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(PaymentDetails.this, Maps.class);
        PaymentDetails.this.startActivity(myIntent);
        finish();
    }


    public void loadData(){
         sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("item", null);
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        productArrayList = gson.fromJson(json, type);

        if (productArrayList == null){
            productArrayList = new ArrayList<>();
        }

    }
    public void submitOrder(){
        loadData();
        createOrder();
        deleteCart();
    }
    private void deleteCart(){
        sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }
    private void createOrder(){
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        Double total = Double.valueOf(totals);
        int id = databaseReference.push().hashCode() - 1000000;
        if(id < 0){
            id = id *(-1);
        }
        Order order2 = new Order(id, productArrayList, "Pending", total, date1);
        databaseReference.child("order").child(firebaseAuth.getCurrentUser().getUid()).child(date1).setValue(order2);
        //databaseReference.child("order").child(firebaseAuth.getCurrentUser().getUid()).child(date1).setValue(productArrayList);



    }
}
