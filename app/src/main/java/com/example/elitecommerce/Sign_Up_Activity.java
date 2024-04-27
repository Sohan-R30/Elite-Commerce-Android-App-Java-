package com.example.elitecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.elitecommerce.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_Up_Activity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignUpBinding binding = ActivitySignUpBinding.inflate(getLayoutInflater());
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

        binding.signInHereLink.setOnClickListener(view -> {
            startActivity(new Intent(Sign_Up_Activity.this, LoginActivity.class));
            finish();
        });

        binding.signUpBtnSignUp.setOnClickListener(view -> {
            String email = binding.emailEditTextSignUp.getText().toString().trim();
            String password = binding.passwordEditTextSignUp.getText().toString().trim();
            String confirmPassword = binding.confirmPasswordEditTextSignUp.getText().toString().trim();

            if (email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                binding.confirmPasswordEditTextSignUp.setText("");
                Toast.makeText(Sign_Up_Activity.this, "Fill All Details Properly", Toast.LENGTH_SHORT).show();
            }
            else if (email.isEmpty()) {
                Toast.makeText(Sign_Up_Activity.this, "Fill Email Properly", Toast.LENGTH_SHORT).show();
            }
            else if(password.isEmpty())
            {
                Toast.makeText(Sign_Up_Activity.this, "Fill Password Properly", Toast.LENGTH_SHORT).show();
            } else if (confirmPassword.isEmpty()) {
                Toast.makeText(Sign_Up_Activity.this, "Fill Confirm Password Properly", Toast.LENGTH_SHORT).show();
            } else
            {
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Sign_Up_Activity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                } else {
                    signUpEmailPassFunc(email, password);
                }
            }
        });
        binding.googleBtnSignUp.setOnClickListener(view -> {
            Toast.makeText(Sign_Up_Activity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });

        binding.facebookBtnSignUp.setOnClickListener(view -> {
            Toast.makeText(Sign_Up_Activity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });

        binding.appleBtnSignUp.setOnClickListener(view -> {
            Toast.makeText(Sign_Up_Activity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });

    }



    private void signUpEmailPassFunc(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Log.d("Create User", "Create User Successfully");
                        Toast.makeText(Sign_Up_Activity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Sign_Up_Activity.this, LoginActivity.class));
                        finish();

                    } else {
                        ActivitySignUpBinding bind = ActivitySignUpBinding.inflate(getLayoutInflater());
                        bind.emailEditTextSignUp.setText("");
                        bind.passwordEditTextSignUp.setText("");
                        bind.confirmPasswordEditTextSignUp.setText("");
                        Log.w("Create User", "Create User Failed", task.getException());
                        Toast.makeText(Sign_Up_Activity.this, "Failed : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}