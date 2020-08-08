///*
// * CS-360-R4495 Mobile Architect & Programming
// * 6-2 Final Project Stepping Stone Six: Social Media Integration
// * Bourama Mangara
// * 12 April 2020
// */
//
//package com.example.lcs2;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.lcs2.DBManagement.DBHelper;
//
//import java.util.ArrayList;
//
//public class ItemDetail extends AppCompatActivity {
//    TextView idView;
//    EditText productNameBox;
//    EditText productDescriptionBox;
//    EditText productQuantityBox;
//    EditText productPriceBox;
////    private EditText mEditTextName;
////    private TextView mTextViewAmout;
////    private int mAnoumt = 0;
//    ArrayList<Product> products;
//    //ListView listView;
//    DBHelper dbHelper = new DBHelper(this, null, null, 1);
//    //ProductAdapter productAdapter;
//    EditText searchNameBox;
//    RecyclerView recyclerView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.item_detail);
//        searchNameBox = (EditText)findViewById(R.id.searchInput2);
//
//        Intent intent = getIntent();
//        intent.getAction();
//
//        //DBHelper dbHelper = new DBHelper()
//        //products = new ArrayList<>();
//        products = dbHelper.search(searchNameBox.getText().toString());
//        recyclerView = findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ItemDetail.this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//
//        productAdapter = new ProductAdapter(this, products);
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.setHasFixedSize(true);
//
//
////        mEditTextName = findViewById(R.id.edittext_name);
////        mTextViewAmout = findViewById(R.id.textview_amount);
////        Button buttonIncrease = findViewById(R.id.button_increase);
////        Button buttonDecrease = findViewById(R.id.button_decrease);
////        Button buttonAdd = findViewById(R.id.button_add);
//
////        buttonIncrease.setOnClickListener(new View.OnClickListener(){
//////            @Override
//////            public void onClick(View v){
//////                increase();
//////            }
//////
//////        });
//////        buttonDecrease.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                decrease();
//////            }
//////        });
//////
//////        buttonAdd.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                addItem();
//////            }
//////        });
//
//    }
//    // Function to populate list view
//    private void loadDataInListView() {
//        products = dbHelper.search(searchNameBox.getText().toString());
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        productAdapter = new ProductAdapter(this, products);
//        recyclerView.setAdapter(productAdapter);
//        productAdapter.notifyDataSetChanged();
//    }
//    // Search for product
//    public void Search(View view) {
//        DBHelper
//                dbHelper = new DBHelper(this, null, null, 1);
//         products = dbHelper.search(searchNameBox.getText().toString());
//        if (!products.isEmpty()) {
//            loadDataInListView();
//        } else {
//            searchNameBox.setText("Product Not Found.");
//        }
//    }
//
//    public void searchDetail(View view) {
//        DBHelper
//                dbHelper = new DBHelper(this, null, null, 1);
//        Product product = dbHelper.searchOne(productNameBox.getText().toString());
//        if (product != null) {
//            idView.setText(String.valueOf(product.getProductID()));
//            productNameBox.setText(String.valueOf(product.getProductName()));
//            productDescriptionBox.setText(String.valueOf(product.getProductDescription()));
//            productPriceBox.setText(String.valueOf(product.getPrice()));
//            productQuantityBox.setText(String.valueOf(product.getQuantityInStock()));
//        } else {
//            idView.setText("Product not found.");
//        }
//    }
//
////    private void increase(){
////        mAnoumt++;
////        mTextViewAmout.setText(String.valueOf(mAnoumt));
////    }
////    private void decrease(){
////        if (mAnoumt > 0){
////        mAnoumt--;
////        mTextViewAmout.setText(String.valueOf(mAnoumt));
////        }
////    }
////    private void addItem(){
////
////    }
//}
