///*
// * CS-360-R4495 Mobile Architect & Programming
// * 7-2 Final Project Submission
// * Bourama Mangara
// * 19 April 2020
// */
//package com.example.lcs2;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class MyAdapter  extends BaseAdapter {
//
//   Context context;
//   ArrayList<Product> arrayList;
//
//
//
//    public MyAdapter(Context context, ArrayList<Product> arrayList){
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//    @Override
//    public int getCount() {
//        return this.arrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return arrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//            final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.order_list_item, null);
//            TextView t1_id = (TextView)convertView.findViewById(R.id.productID);
//            TextView t1_name = (TextView)convertView.findViewById(R.id.ProductName);
//            TextView t1_price = (TextView)convertView.findViewById(R.id.Price);
//            final Product product = arrayList.get(position);
//            t1_id.setText(String.valueOf(product.getProductID()));
//            t1_name.setText(product.getProductName());
//            t1_price.setText("$"+String.valueOf(product.getPrice()));
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, Detail_Item.class);
//                    intent.putExtra("id", product.getProductID());
//                    context.startActivity(intent);
//                }
//            });
//
//        return convertView;
//    }
//}
