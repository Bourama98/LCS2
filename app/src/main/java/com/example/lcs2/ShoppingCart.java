/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */
package com.example.lcs2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.lcs2.DBManagement.DBHelper;
import com.example.lcs2.InfoPages.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity {
    TextView total, tax, tBeforeTax;
    Button placeOder;

    private String SHOPPINGCART = "cart";
    private String KEY = "item";
    SharedPreferences sharedPreferences;

    ArrayList<CartItem> cartItems;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    Double intTotal = 0.0;
    Double Tax = 0.0;
    Double totalAfterTax = 0.0;
    final static double taxtfee = 0.065;
    private String image_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        total = (TextView)findViewById(R.id.total);
        tax = (TextView)findViewById(R.id.tax);
        tBeforeTax = (TextView)findViewById(R.id.total_before_tax);
        placeOder = (Button)findViewById(R.id.place_order);
        final Intent intent = getIntent();
        intent.getAction();
        image_url = intent.getStringExtra("image");

        loadData(); // calling the loadData method to populate shopping cart

        recyclerView = findViewById(R.id.cart_recycleview); // getting the reference of the the recylerview from the resource file
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // set up the layout for the view and pass it to the recyclerview
        recyclerView.setLayoutManager(layoutManager);


        cartAdapter = new CartAdapter(this, cartItems); // populate the adapter class and set the recyclerview adapter
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setHasFixedSize(true);

        // Calulate the total of the cart, tax, and the total with tax (this can be done in a separate method for cleaner code)
        if(!cartItems.isEmpty()){
            for(int i = 0; i < cartItems.size(); ++i){
           intTotal = intTotal + (cartItems.get(i).getUnit_price() * cartItems.get(i).getQuantity());
        }}

        Tax = intTotal * taxtfee;
        Tax = Math.round(Tax * 100.0)/100.0;
        totalAfterTax = intTotal + Tax;
        totalAfterTax = Math.round(totalAfterTax * 100.0)/100.0;

        tBeforeTax.setText("$"+String.valueOf(intTotal));
        tax.setText("$"+String.valueOf(Tax));
        total.setText("$"+String.valueOf(totalAfterTax));

        // End of cost calculation
        if(cartItems.size() == 0){// check if the shopping cart is empty and return if so

            placeOder.setVisibility(View.INVISIBLE);
            return;
        }

       placeOder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) { // The onclickListner to detect when place order button is click
               if(cartItems.size() == 0){// check if the shopping cart is empty and return if so
                   placeOder.setVisibility(View.INVISIBLE);
                   placeOder.setClickable(false);
                   Toast.makeText(ShoppingCart.this, "Please add product to the cart first", Toast.LENGTH_LONG).show();
                   return;
               }
               else { // Allow order fulfillment if the cart is not empty
                   placeOder.setClickable(true);
                   Intent intent1 = new Intent(ShoppingCart.this, Payment.class);
                   intent1.putExtra("total", String.valueOf(totalAfterTax));
                   intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   ShoppingCart.this.startActivity(intent1);
                   finish();
               }
           }
       });

    }



    public void saveData(){ // not use in this class, will be clean up later
        sharedPreferences = getSharedPreferences(SHOPPINGCART, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(KEY, json);
        editor.apply();
    }

    public void loadData(){ // get shopping cart info from the sharedRefernces
        sharedPreferences = getSharedPreferences(SHOPPINGCART, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY, null);
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        cartItems = gson.fromJson(json, type);

        if (cartItems == null){
            cartItems = new ArrayList<>();
        }

    }

    public void navigateToAddProduct(View view){// On click back to add product activity
        Intent myIntent = new Intent(ShoppingCart.this, FindItems.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        myIntent.putExtra("image", image_url);
        ShoppingCart.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(ShoppingCart.this, Maps.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ShoppingCart.this.startActivity(myIntent);
        finish();
    }
}
