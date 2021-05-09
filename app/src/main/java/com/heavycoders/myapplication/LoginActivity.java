package com.heavycoders.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout loginPasswordLayout,loginEmailLayout;
    private EditText loginPassword, loginEmail;
    private TextView signUpButton;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Please Wait");
        pd.setMessage("Logging in...");
        pd.setCancelable(false);

        loginEmailLayout = findViewById(R.id.loginEmailLayout);
        loginPasswordLayout = findViewById(R.id.loginPasswordLayout);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        signUpButton = findViewById(R.id.signUpButton);
        login = findViewById(R.id.login);

        signUpButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        login.setOnClickListener(v -> {
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();
            loginEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    loginEmailLayout.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.toString().isEmpty()){
                        loginEmailLayout.setError("E-Mail cannot be empty.");
                    }
                }
            });
            loginPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    loginPasswordLayout.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.toString().isEmpty()){
                        loginPasswordLayout.setError("Password cannot be empty.");
                    }
                }
            });
            if(email.isEmpty() && password.isEmpty()){
                loginEmailLayout.setError("E-Mail cannot be empty.");
                loginPasswordLayout.setError("Password cannot be empty.");
            } else if(email.isEmpty()){
                loginEmailLayout.setError("E-Mail cannot be empty.");
            } else if(password.isEmpty()){
                loginPasswordLayout.setError("Password cannot be empty.");
            } else {
                pd.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Successfully Logged in.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        pd.dismiss();
                        Toast.makeText(LoginActivity.this, "Login Failed : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


    }
}