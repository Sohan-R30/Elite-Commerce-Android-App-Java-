package com.example.elitecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.elitecommerce.Adapter.RecylerCartProductAdapter;
import com.example.elitecommerce.Fragments.CartFragment;
import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.Services.RetrofitInstanceCart;
import com.example.elitecommerce.databinding.ActivityProductDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    double p_productPrice_c;
    double p_productRatings_c;
    int p_productReviews_c;

    int p_productQuantity_c;
    private String  p_productId, p_productImage, p_productTitle, p_productCategory, p_userEmail , p_productPrice, p_productRatings,p_productQuantity, p_productReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailsBinding binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        p_productId = getIntent().getExtras().getString("_id");
        p_productImage = getIntent().getExtras().getString("productImage");
        p_productTitle = getIntent().getExtras().getString("productTitle");
        p_productPrice = getIntent().getExtras().getString("productPrice");
        p_productRatings = getIntent().getExtras().getString("productRatings");
        p_productReviews = getIntent().getExtras().getString("productReviews");
        p_productCategory = getIntent().getExtras().getString("productCategory");
        p_productQuantity = getIntent().getExtras().getString("productQuantity");


        FirebaseUser currentUser = auth.getCurrentUser();
        p_userEmail = currentUser.getEmail();
        binding.productPrice.setText(getIntent().getExtras().getString("productPrice"));
        binding.productTitle.setText(getIntent().getExtras().getString("productTitle"));
        binding.productReviews.setText(p_productReviews);
        Glide.with(this)
                .load(getIntent().getExtras().getString("productImage"))
                .into(binding.productImage);

        binding.productCategory.setText(getIntent().getExtras().getString("productCategory"));
        binding.backBtnView.setOnClickListener(view -> {
            dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

        });

        try {
            p_productPrice_c = Double.parseDouble(p_productPrice);
            p_productRatings_c = Double.parseDouble(p_productRatings);
            p_productQuantity_c = Integer.parseInt(p_productQuantity);
            p_productReviews_c = Integer.parseInt(p_productReviews);
        }
        catch (NumberFormatException exception)
        {
            Log.d("exception", exception.toString());
        }

        binding.addToCartBtn.setOnClickListener(v -> {
            RetrofitInstanceCart.getInstance().cartsApi.postCarts(new CartProductModel(p_productId,p_productImage,p_productTitle,p_productPrice_c,p_productRatings_c,p_productReviews_c,p_productCategory,p_productQuantity_c,p_userEmail)).enqueue(new Callback<CartProductModel>() {
                @Override
                public void onResponse(Call<CartProductModel> call, Response<CartProductModel> response) {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(ProductDetailsActivity.this, "Product Added Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Log.d("my_data22", response.toString());
                    }
                }

                @Override
                public void onFailure(Call<CartProductModel> call, Throwable t) {
                    Log.d("my_data33", t.toString());
                }
            });
        });
    }

}
