package com.example.elitecommerce.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elitecommerce.Adapter.RecyclerProductsAdapter;
import com.example.elitecommerce.Adapter.RecylerCartProductAdapter;
import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.Model.ProductsModel;
import com.example.elitecommerce.ProductDetailsActivity;
import com.example.elitecommerce.R;
import com.example.elitecommerce.Services.RetrofitInstance;
import com.example.elitecommerce.Services.RetrofitInstanceCart;
import com.example.elitecommerce.databinding.FragmentCartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    ArrayList<CartProductModel> productsList = new ArrayList<CartProductModel>();
    RecylerCartProductAdapter adapter;
    FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCartBinding binding = FragmentCartBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        binding.shoppingCartRecylerViewList.setLayoutManager( new LinearLayoutManager(container.getContext()));



//        productsList.add(new CartProductModel(R.drawable.product2,"Wireless Doorbell Camera","199.42","1"));
//        productsList.add(new CartProductModel(R.drawable.product3,"Instant Pot Multicooker","149.95","1"));
//        productsList.add(new CartProductModel(R.drawable.product4,"4K Smart TV","599.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product5,"Gaming Headset with Mic","79.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product1,"High-Performance Gaming Laptop","1299.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product6,"Electric Toothbrush","64.87","1"));
//        productsList.add(new CartProductModel(R.drawable.product1,"Bluetooth Headphones with ANC","199.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product2,"Portable Projector","249.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product6,"Home Security System","399.99","1"));
//        productsList.add(new CartProductModel(R.drawable.product4,"Robot Vacuum Cleaner","299.99","1"));


        FirebaseUser currentUser = auth.getCurrentUser();
        RetrofitInstanceCart.getInstance().cartsApi.getCarts(currentUser.getEmail()).enqueue(new Callback<ArrayList<CartProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CartProductModel>> call, Response<ArrayList<CartProductModel>> response) {

                if (response.isSuccessful())
                {
                    productsList = response.body();
                    adapter = new RecylerCartProductAdapter(productsList,container.getContext());
                    binding.shoppingCartRecylerViewList.setAdapter(adapter);

                }
                else
                {
                    Log.d("my_data2", response.toString());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<CartProductModel>> call, Throwable t) {
                Log.d("my_data3", t.getLocalizedMessage());
            }
        });

        binding.payBtn.setOnClickListener(v -> {
            RetrofitInstanceCart.getInstance().cartsApi.deleteAllCarts(currentUser.getEmail()).enqueue(new Callback<CartProductModel>() {
                @Override
                public void onResponse(Call<CartProductModel> call, Response<CartProductModel> response) {
                    if (response.isSuccessful())
                    {
                        Toast.makeText( getContext(),  "Paid Successful", Toast.LENGTH_SHORT).show();
                        replaceFragment(new HomeFragment(), false);
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

        RecylerCartProductAdapter adapter = new RecylerCartProductAdapter(productsList,container.getContext());
        binding.shoppingCartRecylerViewList.setAdapter(adapter);

        binding.backBtnView.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });


        return binding.getRoot();
    }
    private void replaceFragment(Fragment fragment, boolean isClicked)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
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