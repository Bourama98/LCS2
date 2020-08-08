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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import com.example.lcs2.InfoPages.Maps;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    //FirebaseAuth firebaseAuth;
    //DatabaseReference databaseReference;

    RecyclerView recyclerView;
    OrderHistoryAdapter historyAdapter;
    final ArrayList<Order> order2ArrayList= new ArrayList<>();
    BottomAppBar bottomAppBar;
    ScrollView scroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        recyclerView = findViewById(R.id.orderHistoryRecycler);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        scroller = findViewById(R.id.scrollableHistory);
        loadAllOrder();
        //firebaseAuth = FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference();
        //userID = firebaseAuth.getCurrentUser().getUid();





        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext()); // set up the layout for the view and pass it to the recyclerview
       // recyclerView.setLayoutManager(layoutManager);

       // historyAdapter = new OrderHistoryAdapter(getBaseContext(), order2ArrayList);
        //recyclerView.setAdapter(historyAdapter);
        //recyclerView.setHasFixedSize(true);

    }

    private void loadDataInListView(ArrayList<Order> p) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new OrderHistoryAdapter(this, p);
        recyclerView.setAdapter(historyAdapter);
        //historyAdapter.notifyDataSetChanged();
    }

    public void loadAllOrder(){
        FirebaseDatabase reference = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(userID);
        DatabaseReference ref = reference.getReference("order").child(userID);
        Query query = ref.orderByValue().limitToLast(10);
        query.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                  System.out.println(dataSnapshot.getKey());
                  Order order2 = dataSnapshot.getValue(Order.class);
                  order2ArrayList.add(order2);
              }
             loadDataInListView(order2ArrayList);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });


    }


    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(OrderHistory.this, FindItems.class);
        OrderHistory.this.startActivity(myIntent);
        finish();
    }
    public void storeLocations(View view){ // On click open the google map activity
        Intent myIntent = new Intent(OrderHistory.this, Maps.class);
        OrderHistory.this.startActivity(myIntent);
        finish();
    }

}
