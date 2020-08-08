///*
// * CS-360-R4495 Mobile Architect & Programming
// * 7-2 Final Project Submission
// * Bourama Mangara
// * 19 April 2020
// */
//
//package com.example.lcs2;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.prefs.PreferenceChangeEvent;
//
//public class ProductReferences {
//    SharedPreferences sharedPreferences;
//    Context context;
//    private final String SHOPPINGCART = "cart";
//    private final String KEY = "item";
//    ArrayList<Product2> products;
//    private Activity activity;
//
//
//    public void saveDataArray(ArrayList<Product2> item){
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(item);
//        editor.putString(KEY, json);
//        editor.apply();
//    }
//
//    public ArrayList<Product> loadData(){
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(KEY, null);
//        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
//        products = gson.fromJson(json, type);
//
//        if (products == null){
//            products = new ArrayList<>();
//        }
//return products;
//    }
//
//    public void saveAccessToken(String token) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("fb_access_token", token);
//        editor.apply(); // This line is IMPORTANT !!!
//    }
//
//
//    public String getToken() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        return prefs.getString("fb_access_token", null);
//    }
//
//    public void clearToken() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.clear();
//        editor.apply(); // This line is IMPORTANT !!!
//    }
//
//}
