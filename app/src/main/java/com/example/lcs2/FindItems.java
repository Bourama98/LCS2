/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */
package com.example.lcs2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.lcs2.InfoPages.AboutUS;
import com.example.lcs2.InfoPages.Calendar;
import com.example.lcs2.InfoPages.ContactUs;
import com.example.lcs2.InfoPages.Maps;
import com.example.lcs2.InfoPages.RateUs;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class FindItems extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ArrayList<Product> products, productArrayList, arrayList;
    SharedPreferences sharedPreferences;
    RecyclerView listView;
    String searchText;
    ProductAdapter productAdapter;
    EditText searchNameBox;
    TextView cartCount;
    CircleImageView circleImageView;
    TextView logout;
    Button search;
    private String image_url;
    {
        products = new ArrayList<>();
    }
    ArrayList<Product> products2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_items);
        searchNameBox = (EditText) findViewById(R.id.searchInput);
        search = (Button) findViewById(R.id.search);
        cartCount = findViewById(R.id.cartcount);
        circleImageView = (CircleImageView)findViewById(R.id.home_profile_pic);
        logout = (TextView) findViewById(R.id.logOut);
        searchText = searchNameBox.getText().toString().toLowerCase().trim();
        loadData();
        loadAll();
        Intent intent = getIntent();
        intent.getAction();
        listView = (RecyclerView) findViewById(R.id.productView);
        cartCount.setText(String.valueOf(productArrayList.size()));
        image_url = intent.getStringExtra("image");

        // Load user profile picture
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.dontAnimate();
        Glide.with(getBaseContext()).load(image_url).into(circleImageView);

        //listView.setLayoutManager(new GridLayoutManager(this, 2));
       // productAdapter = new ProductAdapter(this, products2);
       // listView.setAdapter(productAdapter);
      //  productAdapter.notifyDataSetChanged();

        cartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShoppingCart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                FindItems.this.startActivity(intent);
                finish();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Logout.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                FindItems.this.startActivity(intent);
                finish();
            }
        });

        searchNameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null){

                }else{
                    firebaseSearch(s.toString().toLowerCase().trim());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }

    // Create the menu items


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);

        return true;
    }

    // Add On click listener to the menu items
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent myIntent;
        switch (item.getItemId()){
            case  R.id.aboutUS:
                myIntent = new Intent(FindItems.this, AboutUS.class);
                myIntent.putExtra("image", image_url);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            case  R.id.contactUS:
                myIntent = new Intent(FindItems.this, ContactUs.class);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            case  R.id.rateUS:
                myIntent = new Intent(FindItems.this, RateUs.class);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            case  R.id.ourLocations:
                myIntent = new Intent(FindItems.this, Maps.class);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            case  R.id.onFaceBook:
                Toast.makeText(this, "We don't have facebook yet", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.orderHistory:
                myIntent = new Intent(FindItems.this, OrderHistory.class);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            case  R.id.calendar:
                myIntent = new Intent(FindItems.this, Calendar.class);
                FindItems.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return false;
        }
    }


    public void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_item);
        popupMenu.show();

    }

//    // Search for product
//    public void Search(View view) {
//        DBHelper
//                dbHelper = new DBHelper(this, null, null, 6);
//        String name = searchNameBox.getText().toString().trim();
//        ArrayList<Product> product = dbHelper.search(name);
//        //loadDataInListView();
//        //firebaseSearch(searchNameBox.getText().toString().toLowerCase().trim());
//        if (!product.isEmpty()) {
//          // loadDataInListView();
//            //firebaseSearch(searchNameBox.getText().toString().toLowerCase().trim());
//        } else {
//            Toast.makeText(this, "Product Not Found", Toast.LENGTH_SHORT).show();
//           // searchNameBox.setError("Product Not Found.");
//        }
//
//
//    }
    private void firebaseSearch(String search){
       final ArrayList<Product> productList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("product");
        Query query = reference.orderByChild("name").startAt(search).endAt(search + "\uf8ff");
        if(search.isEmpty()){
            return;
        }else {
        query.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    if(!productList.contains(product)){
                        productList.add(product);
                    }
                    if(product== null){
                        Toast.makeText(getBaseContext(), "Product Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getBaseContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        }
        loadDataInListView(productList);
    }


    // Function to populate list view
    private void loadDataInListView(ArrayList<Product> p) {
        listView.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter(this, p);
        listView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(FindItems.this, Maps.class);
        FindItems.this.startActivity(myIntent);
        finish();
    }
   //
    public void loadData(){
        sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("item", null);
        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
        productArrayList = gson.fromJson(json, type);

        if (productArrayList == null){
            productArrayList = new ArrayList<>();
        }

    }


    private void loadAll(){
        final ArrayList<Product> productList = new ArrayList<>();
        FirebaseDatabase reference = FirebaseDatabase.getInstance();
        DatabaseReference query = reference.getReference("product");
        query.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                if(!productList.isEmpty()) {
                  loadDataInListView(productList);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
