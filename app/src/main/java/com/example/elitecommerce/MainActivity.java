package com.example.elitecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.elitecommerce.Fragments.CartFragment;
import com.example.elitecommerce.Fragments.HomeFragment;
import com.example.elitecommerce.Fragments.ProfileFragment;
import com.example.elitecommerce.Fragments.SearchFragment;
import com.example.elitecommerce.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

       replaceFragment(new HomeFragment(),true);


       binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId() == R.id.homeFragment)
            {
                replaceFragment( new HomeFragment(), false);
            }
            else if (item.getItemId() == R.id.searchFragment){
                replaceFragment( new SearchFragment(), false);
            }
            else if (item.getItemId() == R.id.cartFragment){
                replaceFragment( new CartFragment() , false);
            }
            else if (item.getItemId() == R.id.profileFragment){
                replaceFragment( new ProfileFragment() , false);
            }
               return true;
           }
       });
    }

    private void replaceFragment(Fragment fragment, boolean isClicked)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(isClicked)
        {
            transaction.add(R.id.frameLayout,fragment);
        }
        else
        {
            transaction.replace(R.id.frameLayout,fragment);
        }

        transaction.commit();
    }
}