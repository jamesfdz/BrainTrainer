package com.codingwithjames.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    Button mGoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        mGoBtn = findViewById(R.id.goButton);
    }

    public void startPlaying(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
