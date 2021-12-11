package com.iub.firebaseauthdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {
    public EditText loginEmailId, logInpasswd;
    Button btnLogIn;
    TextView signup;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        loginEmailId = findViewById(R.id.loginEmail);
        logInpasswd = findViewById(R.id.loginpaswd);
        btnLogIn = findViewById(R.id.btnLogIn);
        signup = findViewById(R.id.TVSignIn);
        authStateListener = (firebaseAuth) -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Toast.makeText(ActivityLogin.this, "User logged in ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityLogin.this, UserActivity.class));
            } else {
                Toast.makeText(ActivityLogin.this, "Login to continue", Toast.LENGTH_SHORT).show();
            }
        };
        signup.setOnClickListener((view) -> {
            startActivity(new Intent(ActivityLogin.this, MainActivity.class));
        });
        btnLogIn.setOnClickListener((view) -> {
            String userEmail = loginEmailId.getText().toString();
            String userPaswd = logInpasswd.getText().toString();
            if (userEmail.isEmpty()) {
                loginEmailId.setError("Provide your Email first");
                loginEmailId.requestFocus();
            } else if (userPaswd.isEmpty()) {
                logInpasswd.setError("Enter password");
                logInpasswd.requestFocus();
            } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                Toast.makeText(ActivityLogin.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
            } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener
                        (ActivityLogin.this, (task) -> {
                           if (!task.isSuccessful()) {
                               Toast.makeText(ActivityLogin.this, "Not Successful", Toast.LENGTH_SHORT).show();
                           } else {
                               startActivity(new Intent(ActivityLogin.this, UserActivity.class));
                           }
                        });
            } else {
                Toast.makeText(ActivityLogin.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
