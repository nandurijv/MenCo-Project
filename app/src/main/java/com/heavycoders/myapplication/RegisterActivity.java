package com.heavycoders.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText rName, rEmail, rNumber, rPassword;
    private Button register;
    private TextView loginButton;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Please Wait");
        pd.setMessage("Creating Account...");
        pd.setCancelable(false);

        rName = findViewById(R.id.rName);
        rEmail = findViewById(R.id.rEmail);
        rNumber = findViewById(R.id.rNumber);
        rPassword = findViewById(R.id.rPassword);
        register = findViewById(R.id.create);
        loginButton = findViewById(R.id.loginButton);
        back = findViewById(R.id.imageView);

        back.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        loginButton.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        register.setOnClickListener(v -> {
            String name = rName.getText().toString();
            String email = rEmail.getText().toString();
            String number = rNumber.getText().toString();
            String password = rPassword.getText().toString();
            if(name.isEmpty() || email.isEmpty() || number.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Fields cannot be empty!!", Toast.LENGTH_SHORT).show();
            } else {
                pd.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        String user = mAuth.getCurrentUser().getUid();
                        DatabaseReference storeValue = FirebaseDatabase.getInstance().getReference().child("users").child(user);
                        HashMap<String, String> info = new HashMap<>();
                        info.put("name", name);
                        info.put("email", email);
                        info.put("number", number);
                        storeValue.setValue(info).addOnSuccessListener(aVoid -> {
                            pd.dismiss();
                            Toast.makeText(this, "Account created successfully!, You can Login now.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        });
                    } else {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration Failed : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}