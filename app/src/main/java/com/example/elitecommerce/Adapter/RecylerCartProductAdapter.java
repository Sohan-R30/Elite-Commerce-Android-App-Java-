package com.example.elitecommerce.Adapter;

import android.content.Context;
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

import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.R;

import java.util.ArrayList;

public class RecylerCartProductAdapter extends RecyclerView.Adapter<RecylerCartProductAdapter.CartViewHolder> {

    ArrayList<CartProductModel> productsList;
    Context context;

    public RecylerCartProductAdapter(ArrayList<CartProductModel> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    public void setFilteredList(ArrayList<CartProductModel> filteredList)
    {
        this.productsList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecylerCartProductAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item_list,parent,false);
        return new RecylerCartProductAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerCartProductAdapter.CartViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.productImage.setImageResource(productsList.get(position).productImage);
        holder.productTitle.setText(productsList.get(position).productTitle);
        holder.productPrice.setText(productsList.get(position).productPrice);
        holder.cartItemQuantity.setText(productsList.get(position).cartItemQuantity);

        holder.cartItemQuantityIncrease.setOnClickListener(v -> {
            try {
                String quantity = holder.cartItemQuantity.getText().toString();
                double q = Double.parseDouble(quantity);
                if(q < 10)
                {
                    q++;
                    int nQ = (int) q;
                    String sQ =  Integer.toString(nQ);
                    holder.cartItemQuantity.setText(sQ);
                }
            }
            catch (NumberFormatException e)
            {

            }
        });

        holder.cartItemQuantityDecrease.setOnClickListener(v -> {
            try {
                String quantity = holder.cartItemQuantity.getText().toString();
                double q = Double.parseDouble(quantity);
                if(q > 1)
                {
                    q--;
                    int nQ = (int) q;
                    String sQ =  Integer.toString(nQ);
                    holder.cartItemQuantity.setText(sQ);
                }
            }
            catch (NumberFormatException e)
            {

            }
        });


    }

    @Override
    public int getItemCount() {
       return productsList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage, deleteIcon;
        private TextView productTitle, productPrice, cartItemQuantity, cartItemQuantityIncrease, cartItemQuantityDecrease;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            deleteIcon = itemView.findViewById(R.id.singleCartDelete);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            cartItemQuantity = itemView.findViewById(R.id.cartItemQuantity);
            cartItemQuantityIncrease = itemView.findViewById(R.id.cartItemQuantityIncrease);
            cartItemQuantityDecrease = itemView.findViewById(R.id.cartItemQuantityDecrease);

        }
    }
    private void setAnimation(View view, int position)
    {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        view.setAnimation(animation);
    }

}
