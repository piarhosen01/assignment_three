package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnSubmitForm;
    private TextView tvFormValidationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSubmitForm = findViewById(R.id.btnSubmitForm);
        tvFormValidationResult = findViewById(R.id.tvFormValidationResult);

        // Add TextWatcher to email field to validate in real-time
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

        // Handle form submission
        btnSubmitForm.setOnClickListener(v -> {
            if (validateEmail() && validatePassword()) {
                tvFormValidationResult.setText("Form is valid!");
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show();
            } else {
                tvFormValidationResult.setText("Form is invalid. Please check the fields.");
            }
        });
    }

    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Invalid email address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = etPassword.getText().toString().trim();
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }
}
