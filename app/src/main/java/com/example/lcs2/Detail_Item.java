/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */
package com.example.lcs2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.lcs2.InfoPages.Maps;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Detail_Item extends Activity {


    TextView productNameBox, productDescriptionBox, productPriceBox;
    ImageView imageView;
    Product product;
    private TextView cartCount;
    ElegantNumberButton mAnoumt;
    private String SHOPPINGCART = "cart";
    private String KEY = "item";
    SharedPreferences sharedPreferences;
    ArrayList<CartItem> products;
    CartItem cartItem;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail_item);
        loadData();
        productNameBox = (TextView)findViewById(R.id.pName);
        productDescriptionBox = (TextView)findViewById(R.id.pDescription);
        productPriceBox = (TextView)findViewById(R.id.pPrice);
        imageView = (ImageView)findViewById(R.id.singelItemView);
        mAnoumt = findViewById(R.id.number_button);
        cartCount = findViewById(R.id.cartCount);
        Intent intent = getIntent();
        cartCount.setText(String.valueOf(products.size()));
        final int id = intent.getIntExtra("id", 0);
        final String name = intent.getStringExtra("name");
        final double price = intent.getDoubleExtra("price", 0);
        final int inStock = intent.getIntExtra("stock", 0);
        final String description = intent.getStringExtra("description");
        final String category = intent.getStringExtra("category");
        final String image = intent.getStringExtra("image");

        System.out.println(product + "On create " + price);
        productNameBox.setText(name);
        productPriceBox.setText("$"+ price);
        productDescriptionBox.setText(description);
        Glide.with(getApplicationContext()).load(image).into(imageView);
        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartItem cartItem = new CartItem();
                cartItem.setName(name);
                cartItem.setId(id);
                cartItem.setUnit_price(price);
                cartItem.setQuantity(Integer.parseInt(mAnoumt.getNumber()));
                cartItem.setProduct_image(image);
                products.add(cartItem);
                saveData();
                Toast.makeText(Detail_Item.this, "Thanks", Toast.LENGTH_LONG).show();
                cartCount.setText(String.valueOf(products.size()));
            }
        });
        cartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Item.this, ShoppingCart.class);
                Detail_Item.this.startActivity(intent);
                System.out.println(String.valueOf(products.size()));
                cartCount.setText(String.valueOf(products.size()));
                finish();
            }
        });



    }
public void saveData(){
        sharedPreferences = getSharedPreferences(SHOPPINGCART, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(products);
        editor.putString(KEY, json);
        editor.apply();
}


public void loadData(){
    sharedPreferences = getSharedPreferences(SHOPPINGCART, MODE_PRIVATE);

    Gson gson = new Gson();
    String json = sharedPreferences.getString(KEY, null);
    Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
    products = gson.fromJson(json, type);

    if (products == null){
        products = new ArrayList<>();
    }

}


    public void navigateToAddProduct(View view){// On click back to add product activity
        Intent myIntent = new Intent(Detail_Item.this, FindItems.class);
        Detail_Item.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(Detail_Item.this, Maps.class);
        Detail_Item.this.startActivity(myIntent);
        finish();
    }

    public Product singleItem(int id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("product");
        Query query = reference.orderByChild("id").startAt(id).endAt(id+"\uf8ff").limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product p = new Product();
                p.setName(snapshot.getValue(Product.class).getName());
                p.setId(snapshot.getValue(Product.class).getId());
                p.setUnit_price(snapshot.getValue(Product.class).getUnit_price());
                p.setIn_stock(snapshot.getValue(Product.class).getIn_stock());
                p.setProduct_image(snapshot.getValue(Product.class).getProduct_image());
                System.out.println(p.getDescription());
                p = product;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return product;
    }

}
