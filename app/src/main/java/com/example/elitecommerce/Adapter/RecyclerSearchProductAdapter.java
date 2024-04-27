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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elitecommerce.Model.ProductModel;
import com.example.elitecommerce.ProductDetailsActivity;
import com.example.elitecommerce.R;

import java.util.ArrayList;

public class RecyclerSearchProductAdapter extends RecyclerView.Adapter<RecyclerSearchProductAdapter.SearchProductViewHolder> {

    ArrayList<ProductModel> productsList;
    Context context;

    public RecyclerSearchProductAdapter(ArrayList<ProductModel> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    public void setFilteredList(ArrayList<ProductModel> filteredList)
    {
        this.productsList = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerSearchProductAdapter.SearchProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_product_item_list,parent,false);
        return new RecyclerSearchProductAdapter.SearchProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSearchProductAdapter.SearchProductViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.productImage.setImageResource(productsList.get(position).productImage);
        holder.productTitle.setText(productsList.get(position).productTitle);
        holder.productPrice.setText(productsList.get(position).productPrice);
        holder.productRatings.setText(productsList.get(position).productRatings);
        holder.productReviews.setText(productsList.get(position).productReviews);

        holder.threeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("productTitle",productsList.get(position).productTitle);
            intent.putExtra("productPrice",productsList.get(position).productPrice);
            intent.putExtra("productRatings",productsList.get(position).productRatings);
            intent.putExtra("productReviews",productsList.get(position).productReviews);
            intent.putExtra("productImage",productsList.get(position).productImage);
            v.getContext().startActivity(intent);
        });

        holder.productImage.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("productTitle",productsList.get(position).productTitle);
            intent.putExtra("productPrice",productsList.get(position).productPrice);
            intent.putExtra("productRatings",productsList.get(position).productRatings);
            intent.putExtra("productReviews",productsList.get(position).productReviews);
            intent.putExtra("productImage",productsList.get(position).productImage);
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
       return productsList.size();
    }
    class SearchProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage, threeIcon;
        private TextView productTitle, productPrice , productRatings, productReviews;


        public SearchProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            threeIcon = itemView.findViewById(R.id.threeIconBtn);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            productRatings = itemView.findViewById(R.id.productRating);
            productReviews = itemView.findViewById(R.id.totalReviews);

        }
    }
    private void setAnimation(View view, int position)
    {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        view.setAnimation(animation);
    }

}
