package com.example.elitecommerce.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.elitecommerce.LoginActivity;
import com.example.elitecommerce.R;
import com.example.elitecommerce.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    ShapeableImageView profileImage;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        profileImage = binding.profilePicture;

        if(auth.getCurrentUser().getDisplayName() != null && !auth.getCurrentUser().getDisplayName().isEmpty())
        {
            binding.profileUserName.setText(auth.getCurrentUser().getDisplayName());
        }

        if(auth.getCurrentUser().getEmail() != null && !auth.getCurrentUser().getEmail().isEmpty())
        {
            binding.profileUserEmail.setText(auth.getCurrentUser().getEmail());
        }

        if (auth.getCurrentUser() != null && auth.getCurrentUser().getPhotoUrl() != null) {
            Glide.with(getActivity())
                    .load(auth.getCurrentUser().getPhotoUrl())
                    .into(binding.profilePicture);
        }


        binding.logOutBtn.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });


        binding.backBtnView.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

        binding.updateNameBtn.setOnClickListener(v -> {
            if(!binding.updateNameEditTxt.getText().toString().isEmpty())
            {


                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(binding.updateNameEditTxt.getText().toString())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    binding.updateNameEditTxt.setText(user.getDisplayName());
                                    binding.updateNameEditTxt.setText("");

                                    Toast.makeText(getContext(),"Profile Name Updated",Toast.LENGTH_SHORT).show();

                                    replaceFragment(new ProfileFragment(), false);
                                }
                                else
                                {
                                    Toast.makeText(getContext(),"Update Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else
            {
                Toast.makeText(getContext(),"Empty Name!! Provide Name for Update",Toast.LENGTH_SHORT).show();
            }
        });

        binding.updateProfilePicBtn.setOnClickListener(v -> {
            String action;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                action = Intent.ACTION_OPEN_DOCUMENT;
            } else {
                action = Intent.ACTION_PICK;
            }

            Intent galleryIntent = new Intent(action,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 10);
        });




        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            // Get the selected image Uri
            // Here, data.getData() will return the Uri of the selected image
            Uri uri = data.getData();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(uri)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                profileImage.setImageURI(user.getPhotoUrl());

                                Toast.makeText(getContext(),"Profile Picture Updated",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(getContext(),"Update Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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