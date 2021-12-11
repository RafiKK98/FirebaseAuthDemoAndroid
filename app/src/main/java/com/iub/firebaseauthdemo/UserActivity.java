package com.iub.firebaseauthdemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener((view) -> {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(UserActivity.this, ActivityLogin.class));
        });
    }
}
