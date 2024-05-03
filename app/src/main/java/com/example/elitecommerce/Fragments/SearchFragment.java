package com.example.elitecommerce.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elitecommerce.Adapter.RecyclerSearchProductAdapter;
import com.example.elitecommerce.Model.ProductsModel;
import com.example.elitecommerce.R;
import com.example.elitecommerce.Services.RetrofitInstance;
import com.example.elitecommerce.databinding.FragmentSearchBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    String categoryFilter = "all";
    String priceFilter = "default";

    String priceRangeFilterMin = "0";
    String priceRangeFilterMax = "100000000";
    BottomSheetDialog dialog;
    ArrayList<ProductsModel> productsList = new ArrayList<ProductsModel>();
    RecyclerSearchProductAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSearchBinding binding = FragmentSearchBinding.inflate(inflater,container,false);

        binding.searchProductEditTxt.clearFocus();
        binding.searchProductRecyclerView.setLayoutManager( new LinearLayoutManager(container.getContext()));

//
//        productsList.add(new ProductModel(R.drawable.product2,"Wireless Doorbell Camera","199.42","4.5","871", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product3,"Instant Pot Multicooker","149.95","4.7","392", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product4,"4K Smart TV","599.99","4.2","148", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product5,"Gaming Headset with Mic","79.99","4.1","583", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product1,"High-Performance Gaming Laptop","1299.99","4.8","25", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product6,"Electric Toothbrush","64.87","4.4","926", "headphone"));
//        productsList.add(new ProductModel(R.drawable.product1,"Bluetooth Headphones with ANC","199.99","4.9","742", "earphone"));
//        productsList.add(new ProductModel(R.drawable.product2,"Portable Projector","249.99","4.3","315", "earphone"));
//        productsList.add(new ProductModel(R.drawable.product6,"Home Security System","399.99","4.6","124", "earphone"));
//        productsList.add(new ProductModel(R.drawable.product4,"Robot Vacuum Cleaner","299.99","4.7","56", "earphone"));
//        productsList.add(new ProductModel(R.drawable.product2,"Gaming Mouse","49.99","4.8","894", "earphone"));
//        productsList.add(new ProductModel(R.drawable.product6,"Electric Razor","129.99","4.2","437", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product4,"Wireless Charging Station","39.99","4.5","982", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product1,"Drone with Camera","499.99","4.1","173", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product5,"Portable Projector Screen","69.994.4","625", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product3,"Smartwatch with GPS","199.99","4.8","214", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product2,"Gaming Keyboard","79.99","4.7","487", "shoes"));
//        productsList.add(new ProductModel(R.drawable.product4,"Electric Kettle","39.99","4.6","952", "watch"));
//        productsList.add(new ProductModel(R.drawable.product4,"Wireless Phone Charger","24.99","4.3","716", "watch"));
//        productsList.add(new ProductModel(R.drawable.product1,"360° Camera","249.99","4.2","389", "watch"));
//        productsList.add(new ProductModel(R.drawable.product5,"Portable Blender","49.99","4.5","841", "watch"));
//        productsList.add(new ProductModel(R.drawable.product5,"Smart Speaker with Assistant","99.99","4.9","593", "watch"));
//        productsList.add(new ProductModel(R.drawable.product2,"Gaming Monitor","299.99","4.8","72", "watch"));
//        productsList.add(new ProductModel(R.drawable.product6,"Hair Dryer","49.99","4.4","678", "watch"));
//        productsList.add(new ProductModel(R.drawable.product4,"Wireless Earbuds with Case","99.99","4.7","342", "watch"));
//        productsList.add(new ProductModel(R.drawable.product1,"Portable Projector Stand","29.99","4.2","914", "watch"));
//        productsList.add(new ProductModel(R.drawable.product5,"Portable Power Bank","24.99","4.6","876", "watch"));


        RetrofitInstance.getInstance().productsApi.getProducts().enqueue(new Callback<ArrayList<ProductsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductsModel>> call, Response<ArrayList<ProductsModel>> response) {

                if (response.isSuccessful())
                {
                    productsList = response.body();

                    adapter = new RecyclerSearchProductAdapter(productsList,container.getContext());
                    binding.searchProductRecyclerView.setAdapter(adapter);
                }
                else
                {
                    Log.d("my_data2", response.toString());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ProductsModel>> call, Throwable t) {
                Log.d("my_data3", t.getLocalizedMessage());
            }
        });





        binding.searchProductEditTxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        binding.backBtnView.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

        binding.OpenFilterBottomSheet.setOnClickListener(view -> {
            dialog = new BottomSheetDialog(getContext(), com.google.android.material.R.style.Theme_Design_Light_BottomSheetDialog);
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


                filteredProducts.clear();
                for (ProductsModel product  : productsList) {


                    if(!priceRangeFilterMin.isEmpty() && !priceRangeFilterMax.isEmpty())
                    {

                        if(Integer.parseInt(priceRangeFilterMin) > Integer.parseInt(priceRangeFilterMax))
                        {
                            Toast.makeText(getContext(),"Give Min and Max Value Properly",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            try {
                                Double pPrice = Double.parseDouble(String.valueOf(product.getProductPrice()));
                                Double fPriceMin = Double.parseDouble(priceRangeFilterMin);
                                Double fPriceMax = Double.parseDouble(priceRangeFilterMax);

                                if (pPrice >= fPriceMin && pPrice <= fPriceMax) {
                                    filteredProducts.add(product);
                                } else {
                                    filteredProducts.remove(product);
                                }
                            } catch (NumberFormatException e) {

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

}