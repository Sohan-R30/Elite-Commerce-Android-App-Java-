package com.example.elitecommerce.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.elitecommerce.Adapter.RecyclerProductsAdapter;
import com.example.elitecommerce.MainActivity;
import com.example.elitecommerce.Model.ProductsModel;
import com.example.elitecommerce.R;
import com.example.elitecommerce.Services.RetrofitInstance;
import com.example.elitecommerce.databinding.FragmentHomeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    String categoryFilter = "all";
    String priceFilter = "default";

    String priceRangeFilterMin = "0";
    String priceRangeFilterMax = "100000000";

    private FirebaseAuth auth;
    BottomSheetDialog dialog;
    RecyclerProductsAdapter adapter;
    ArrayList<ProductsModel> productsList = new ArrayList<ProductsModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.editTextText.clearFocus();



        auth = FirebaseAuth.getInstance();
         if(auth.getCurrentUser() != null)
        {
            if (auth.getCurrentUser().getDisplayName() != null && !auth.getCurrentUser().getDisplayName().isEmpty()) {
                binding.userName.setText(auth.getCurrentUser().getDisplayName());
            }
            else {
                binding.userName.setText(auth.getCurrentUser().getEmail());
            }
        }

        if (auth.getCurrentUser() != null && auth.getCurrentUser().getPhotoUrl() != null) {
            Glide.with(getActivity())
                    .load(auth.getCurrentUser().getPhotoUrl())
                    .into(binding.profilePicture);
        }

        getAllProductFunc();

        binding.profilePicture.setOnClickListener(v -> {
            MainActivity mn = new MainActivity();
            replaceFragment(new ProfileFragment(), false);

        });
        binding.homeProductRecyclerView.setLayoutManager( new GridLayoutManager(container.getContext(),2));

        RetrofitInstance.getInstance().productsApi.getProducts().enqueue(new Callback<ArrayList<ProductsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductsModel>> call, Response<ArrayList<ProductsModel>> response) {

                if (response.isSuccessful())
                {
                    productsList = response.body();
                    adapter = new RecyclerProductsAdapter(productsList,container.getContext());
                    binding.homeProductRecyclerView.setAdapter(adapter);

                }
                else
                {
                    Log.d("my_data2", response.toString());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ProductsModel>> call, Throwable t) {
                Log.d("my_data3_home", t.getLocalizedMessage());
            }
        });










        binding.editTextText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<ProductsModel> filteredProducts = new ArrayList<>();
                for (ProductsModel product  : productsList)
                {
                    if (product.getProductTitle().toLowerCase().contains(newText.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                }
                if(!filteredProducts.isEmpty())
                {
                    adapter.setFilteredList(filteredProducts);

                }
                return true;

            }
        });


        binding.sideDrawerBtn.setOnClickListener(view -> {
            dialog = new BottomSheetDialog(getContext(), com.google.android.material.R.style.Theme_Design_Light_BottomSheetDialog) ;
            dialog.setContentView(R.layout.filter_bottom_shet_layout);

            ImageView closeBtn = dialog.findViewById(R.id.bottomSheetFilterClossBtn);

            AppCompatButton highPrice = dialog.findViewById(R.id.highPriceFilter);
            AppCompatButton lowPrice = dialog.findViewById(R.id.lowPriceFilter);

            EditText minPrice = dialog.findViewById(R.id.minTextEdit);
            EditText maxPrice = dialog.findViewById(R.id.maxTextEdit);


            AppCompatButton applyFilterBtn = dialog.findViewById(R.id.applyFilterBtn);


            closeBtn.setOnClickListener(v -> {
                dialog.cancel();
            });



            highPrice.setOnClickListener(v -> {
                priceFilter = "high";
                Toast.makeText(getContext(),"Selected Price High to Low",Toast.LENGTH_SHORT).show();
            });
            lowPrice.setOnClickListener(v -> {
                priceFilter = "low";
                Toast.makeText(getContext(),"Selected Price Low to High",Toast.LENGTH_SHORT).show();
            });






            applyFilterBtn.setOnClickListener(v -> {


                if(!minPrice.getText().toString().isEmpty())
                {
                    priceRangeFilterMin = minPrice.getText().toString();
                }

                if(!maxPrice.getText().toString().isEmpty())
                {
                    priceRangeFilterMax = maxPrice.getText().toString();
                }




                ArrayList<ProductsModel> filteredProducts = new ArrayList<>();



                for (ProductsModel product  : productsList) {

                    if(!priceRangeFilterMin.isEmpty() && !priceRangeFilterMax.isEmpty())
                    {
                        if(Integer.parseInt(priceRangeFilterMin) > Integer.parseInt(priceRangeFilterMax))
                        {
                            Toast.makeText(getContext(),"Give Min and Max Value Properly",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try {
                                Double pPrice = Double.parseDouble(String.valueOf(product.getProductPrice()));
                                Double fPriceMin = Double.parseDouble(priceRangeFilterMin);
                                Double fPriceMax = Double.parseDouble(priceRangeFilterMax);

                                if(pPrice >= fPriceMin && pPrice <= fPriceMax)
                                {
                                    filteredProducts.add(product);
                                }
                                else {
                                    filteredProducts.remove(product);
                                }
                            }
                            catch (NumberFormatException e)
                            {

                            }
                        }

                    }
                }
                if (priceFilter == "high")
                {
                    sortProductsByPrice(productsList,true);
                    filteredProducts = productsList;

                }

                if (priceFilter == "low")
                {
                    sortProductsByPrice(productsList,false);
                    filteredProducts = productsList;

                }

                if(!filteredProducts.isEmpty())
                {
                    adapter.setFilteredList(filteredProducts);
                }
                dialog.cancel();
            });

            dialog.show();
        });
        return binding.getRoot();


    }

    private void getAllProductFunc() {


    }

    public static void sortProductsByPrice(ArrayList<ProductsModel> products, boolean high_low) {
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                ProductsModel product1 = products.get(i);
                ProductsModel product2 = products.get(j);

                try {
                    // Convert product prices to doubles
                    double price1 = Double.parseDouble(String.valueOf(product1.getProductPrice()));
                    double price2 = Double.parseDouble(String.valueOf(product2.getProductPrice()));

                    // Compare prices
                    if(high_low)
                    {
                        if (price1 < price2) {
                            // Swap product1 and product2
                            ProductsModel temp = product1;
                            products.set(i, product2);
                            products.set(j, temp);
                        }
                    }
                    else
                    {
                        if (price1 > price2) {
                            // Swap product1 and product2
                            ProductsModel temp = product1;
                            products.set(i, product2);
                            products.set(j, temp);
                        }
                    }

                } catch (NumberFormatException e) {

                }
            }
        }
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