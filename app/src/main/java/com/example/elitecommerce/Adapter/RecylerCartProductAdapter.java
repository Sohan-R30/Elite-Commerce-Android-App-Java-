package com.example.elitecommerce.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.ProductDetailsActivity;
import com.example.elitecommerce.R;
import com.example.elitecommerce.Services.RetrofitInstanceCart;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Glide.with(context)
                .load(productsList.get(position).getProductImage())
                .into(holder.productImage);

        holder.productTitle.setText(productsList.get(position).getProductTitle());
        holder.productPrice.setText(String.valueOf(productsList.get(position).getProductPrice()));
        holder.cartItemQuantity.setText(String.valueOf(productsList.get(position).getProductQuantity()));


        holder.deleteIcon.setOnClickListener(v -> {

            RetrofitInstanceCart.getInstance().cartsApi.deleteCarts(productsList.get(position).get_id().toString()).enqueue(new Callback<CartProductModel>() {

                @Override
                public void onResponse(Call<CartProductModel> call, Response<CartProductModel> response) {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(context, "Item Deleted From Cart" , Toast.LENGTH_SHORT).show();
                        if (position >= 0 && position < productsList.size()) {
                            productsList.remove(position);
                            notifyItemRemoved(position);
                            if (productsList.isEmpty()) {
                                notifyDataSetChanged();
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Item Not Deleted" , Toast.LENGTH_SHORT).show();
                    }
                    Log.d("my_data222", response.toString());
                }

                @Override
                public void onFailure(Call<CartProductModel> call, Throwable t) {
                    Toast.makeText(context, "Something Went Wrong!" , Toast.LENGTH_SHORT).show();
                }
            });
        });
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
