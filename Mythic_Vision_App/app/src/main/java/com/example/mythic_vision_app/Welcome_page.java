package com.example.mythic_vision_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

public class Welcome_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Intent welcomeIntent = new Intent(this, Welcome_page.class);
        startActivity(welcomeIntent);

        // Display a welcome message
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome to Mythic Vision!");

        // Set an OnClickListener for the TextView to launch MainActivity
        welcomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch MainActivity when the TextView is clicked
                Intent mainIntent = new Intent(Welcome_page.this, MainActivity.class);
                startActivity(mainIntent);
                finish();  // Finish the WelcomeActivity to remove it from the back stack
            }
        });
    }
}