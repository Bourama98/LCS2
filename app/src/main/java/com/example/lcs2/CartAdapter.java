/*
 * CS-360-R4495 Mobile Architect & Programming
 * 7-2 Final Project Submission
 * Bourama Mangara
 * 19 April 2020
 */

package com.example.lcs2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mContext;

    ArrayList<CartItem> arrayList;
    private String SHOPPING_CART = "cart";
    private String KEY = "item";
    SharedPreferences sharedPreferences;

    int imageID;

    public CartAdapter(Context context, ArrayList<CartItem> arrayList){
        mContext = context;
        this.arrayList = arrayList;

    }

    public CartAdapter(@NonNull Context context) {

    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productPrice;
        public TextView idView;
        public ElegantNumberButton amoutButtom;
        public ImageView imageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.cart_productID);
            amoutButtom = itemView.findViewById(R.id.cart_AmountButton);
            productPrice = itemView.findViewById(R.id.cartItemPrice);
            productName = itemView.findViewById(R.id.cartProductName);
            imageView = itemView.findViewById(R.id.item_image_cart);
        }
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
//        View view = inflater.inflate(R.layout.orderHistoryList, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_view, parent, false);
        CartAdapter.CartViewHolder productViewHolder = new CartAdapter.CartViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.CartViewHolder holder, final int position) {


        final CartItem product = arrayList.get(position);
        holder.idView.setText(String.valueOf(product.getId()));
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$"+String.valueOf(product.getUnit_price()));
        holder.itemView.setTag(product.getId());
        holder.amoutButtom.setNumber(String.valueOf(product.getQuantity()));
        Glide.with(mContext).load(product.getProduct_image()).into(holder.imageView);

        //String image = product.getImage();

       // Context c = mContext.getApplicationContext();

        //imageID = Integer.valueOf(c.getResources().getIdentifier("drawable/" + image, null, c.getPackageName()));
       // holder.imageView.setImageResource(imageID);

        holder.amoutButtom.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                loadData();
                product.setQuantity(newValue);
                arrayList.set(position, product);
                if (newValue == 0){
                    arrayList.remove(product);
                    saveData();
                }
                saveData();
                //Intent intent = new Intent(mContext.getApplicationContext(), ShoppingCart.class);
               // mContext.getApplicationContext().startActivity(intent);
//                sharedPreferences = mContext.getApplicationContext().getSharedPreferences(SHOPPING_CART, mContext.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                Gson gson = new Gson();
//                String json = gson.toJson(arrayList);
//                editor.putString(KEY, json);
//                editor.apply();


            }
        });
        //System.out.println("The position:" + position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    private void saveData(){
        sharedPreferences = mContext.getApplicationContext().getSharedPreferences(SHOPPING_CART, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY, json);
        editor.apply();
    }
    private void loadData(){
        sharedPreferences = mContext.getApplicationContext().getSharedPreferences(SHOPPING_CART, mContext.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY, null);
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        arrayList = gson.fromJson(json, type);

        if (arrayList == null){
            arrayList = new ArrayList<>();
        }

    }

    }

