package com.example.elitecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.example.elitecommerce.databinding.ActivityProductDetailsBinding;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailsBinding binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.productPrice.setText(getIntent().getExtras().getString("productPrice"));
        binding.productTitle.setText(getIntent().getExtras().getString("productTitle"));
        binding.productReviews.setText(getIntent().getExtras().getString("productReviews"));
        binding.productImage.setImageResource(getIntent().getIntExtra("productImage",0));


        binding.backBtnView.setOnClickListener(view -> {
            dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

        });

    }
}