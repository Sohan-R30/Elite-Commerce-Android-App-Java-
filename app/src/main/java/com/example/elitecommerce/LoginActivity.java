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

import com.example.elitecommerce.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        else
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        binding.signUpHereLink.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, Sign_Up_Activity.class));
            finish();
        });

        binding.signInBtnSignIn.setOnClickListener(view -> {
            String email = binding.emailEditTextSignIn.getText().toString().trim();
            String password = binding.passwordEditTextSignIn.getText().toString().trim();

            if(email.isEmpty() && password.isEmpty())
            {
                Toast.makeText(LoginActivity.this, "Fill All Details Properly", Toast.LENGTH_SHORT).show();
            }
            else if(email.isEmpty())
            {
                Toast.makeText(LoginActivity.this, "Fill Email Properly", Toast.LENGTH_SHORT).show();
            }
            else if (password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Fill Password Properly", Toast.LENGTH_SHORT).show();
            }
            else
            {
              loginEmailPassFunc(email,password);

            }
        });

        binding.googleBtnSignIn.setOnClickListener(view -> {
            Toast.makeText(LoginActivity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });

        binding.facebookBtnSignIn.setOnClickListener(view -> {
            Toast.makeText(LoginActivity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });

        binding.appleBtnSignIn.setOnClickListener(view -> {
            Toast.makeText(LoginActivity.this, "Working on it", Toast.LENGTH_SHORT).show();
        });
    }


    private void loginEmailPassFunc(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("Login User", "Login User Successfully");
                        Toast.makeText(LoginActivity.this, "Login User Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        ActivityLoginBinding bind = ActivityLoginBinding.inflate(getLayoutInflater());
                        bind.emailEditTextSignIn.setText("");
                        bind.passwordEditTextSignIn.setText("");
                        Log.w("Login User", "Login User Failed", task.getException());
                        Toast.makeText(LoginActivity.this, "Failed : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}