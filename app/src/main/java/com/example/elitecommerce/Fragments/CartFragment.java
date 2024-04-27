package com.example.elitecommerce.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elitecommerce.Adapter.RecyclerSearchProductAdapter;
import com.example.elitecommerce.Adapter.RecylerCartProductAdapter;
import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.Model.ProductModel;
import com.example.elitecommerce.R;
import com.example.elitecommerce.databinding.FragmentCartBinding;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    ArrayList<CartProductModel> productsList = new ArrayList<CartProductModel>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCartBinding binding = FragmentCartBinding.inflate(inflater,container,false);
        binding.shoppingCartRecylerViewList.setLayoutManager( new LinearLayoutManager(container.getContext()));

        productsList.add(new CartProductModel(R.drawable.product2,"Wireless Doorbell Camera","199.42","1"));
        productsList.add(new CartProductModel(R.drawable.product3,"Instant Pot Multicooker","149.95","1"));
        productsList.add(new CartProductModel(R.drawable.product4,"4K Smart TV","599.99","1"));
        productsList.add(new CartProductModel(R.drawable.product5,"Gaming Headset with Mic","79.99","1"));
        productsList.add(new CartProductModel(R.drawable.product1,"High-Performance Gaming Laptop","1299.99","1"));
        productsList.add(new CartProductModel(R.drawable.product6,"Electric Toothbrush","64.87","1"));
        productsList.add(new CartProductModel(R.drawable.product1,"Bluetooth Headphones with ANC","199.99","1"));
        productsList.add(new CartProductModel(R.drawable.product2,"Portable Projector","249.99","1"));
        productsList.add(new CartProductModel(R.drawable.product6,"Home Security System","399.99","1"));
        productsList.add(new CartProductModel(R.drawable.product4,"Robot Vacuum Cleaner","299.99","1"));

        RecylerCartProductAdapter adapter = new RecylerCartProductAdapter(productsList,container.getContext());
        binding.shoppingCartRecylerViewList.setAdapter(adapter);

        binding.backBtnView.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });


        return binding.getRoot();
    }
}