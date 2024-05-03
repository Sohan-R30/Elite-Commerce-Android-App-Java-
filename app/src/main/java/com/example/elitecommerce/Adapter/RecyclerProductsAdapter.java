package com.example.elitecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elitecommerce.Model.ProductsModel;
import com.example.elitecommerce.ProductDetailsActivity;
import com.example.elitecommerce.R;

import java.util.ArrayList;

public class RecyclerProductsAdapter extends RecyclerView.Adapter<RecyclerProductsAdapter.ProductViewHolder> {

    ArrayList<ProductsModel> productsList;
    Context context;

    public RecyclerProductsAdapter(ArrayList<ProductsModel> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    public void setFilteredList(ArrayList<ProductsModel> filteredList)
    {
        this.productsList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item_list,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerProductsAdapter.ProductViewHolder holder, int position) {

        setAnimation(holder.itemView,position);

        Glide.with(context)
                .load(productsList.get(position).getProductImage())
                .into(holder.productImage);
        holder.productTitle.setText(productsList.get(position).getProductTitle());
        holder.productPrice.setText(String.valueOf(productsList.get(position).getProductPrice()));
        holder.productRatings.setText(String.valueOf(productsList.get(position).getProductRatings()));
        holder.productReviews.setText(String.valueOf(productsList.get(position).getProductReviews()));
        holder.productCategory.setText(productsList.get(position).getProductCategory());

        holder.threeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("productTitle",productsList.get(position).getProductTitle());
            intent.putExtra("productPrice",String.valueOf(productsList.get(position).getProductPrice()));
            intent.putExtra("productRatings",String.valueOf(productsList.get(position).getProductRatings()));
            intent.putExtra("productReviews",String.valueOf(productsList.get(position).getProductReviews()));
            intent.putExtra("productImage",productsList.get(position).getProductImage());
            intent.putExtra("productCategory",productsList.get(position).getProductCategory());
            intent.putExtra("productQuantity","1");
            intent.putExtra("_id",productsList.get(position).get_id());
            v.getContext().startActivity(intent);
        });

        holder.productImage.setOnClickListener(v -> {
//            Toast.makeText(context, productsList.get(position).getProductPrice(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("productTitle",productsList.get(position).getProductTitle());
            intent.putExtra("productPrice",String.valueOf(productsList.get(position).getProductPrice()));
            intent.putExtra("productRatings",String.valueOf(productsList.get(position).getProductRatings()));
            intent.putExtra("productReviews",String.valueOf(productsList.get(position).getProductReviews()));
            intent.putExtra("productImage",productsList.get(position).getProductImage());
            intent.putExtra("productCategory",productsList.get(position).getProductCategory());
            intent.putExtra("productQuantity","1");
            intent.putExtra("_id",productsList.get(position).get_id());
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage, threeIcon;
        private TextView productTitle, productPrice , productRatings, productReviews, productCategory;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            threeIcon = itemView.findViewById(R.id.threeIconBtn);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            productRatings = itemView.findViewById(R.id.productRating);
            productReviews = itemView.findViewById(R.id.totalReviews);
            productCategory = itemView.findViewById(R.id.productCategory);


        }

    }
    private void setAnimation(View view, int position)
    {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        view.setAnimation(animation);
    }


}
