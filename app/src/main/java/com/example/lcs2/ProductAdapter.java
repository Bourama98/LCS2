/*
 * CS-360-R4495 Mobile Architect & Programming
 * 7-2 Final Project Submission
 * Bourama Mangara
 * 19 April 2020
 */
package com.example.lcs2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder2>{

    private Context mContext;
    private Cursor mCursor;
    ArrayList<Product> arrayList;
    private ClipData.Item[] items;
    int imageID;
    public ProductAdapter(Context context, ArrayList<Product> arrayList){
        mContext = context;
        this.arrayList = arrayList;

    }

    public ProductAdapter(@NonNull Context context) {

    }

    public class ProductViewHolder2 extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productPrice;
        public TextView idView;
        public ImageView productImage;

        public ProductViewHolder2(@NonNull View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.productID);
            productPrice = itemView.findViewById(R.id.Price);
            productName = itemView.findViewById(R.id.ProductName);
            productImage = (ImageView) itemView.findViewById(R.id.item_image_cart);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        ProductAdapter.ProductViewHolder2 productViewHolder2 = new ProductAdapter.ProductViewHolder2(view);
        return productViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder2 holder, int position) {

        Context c = mContext.getApplicationContext();
        final Product product = arrayList.get(position);
        holder.idView.setText(String.valueOf(product.getId()));
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$"+String.valueOf(product.getUnit_price()));
        //Picasso.get().load(product.getProduct_image()).into(holder.productImage);
        Glide.with(mContext).load(product.getProduct_image()).into(holder.productImage);

        holder.itemView.setTag(product.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Detail_Item.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("name", product.getName());
                intent.putExtra("price", product.getUnit_price());
                System.out.println(product.getUnit_price());
                intent.putExtra("stock", product.getIn_stock());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("category", product.getCategory());
                intent.putExtra("image", product.getProduct_image());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
