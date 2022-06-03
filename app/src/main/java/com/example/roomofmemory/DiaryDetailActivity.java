package com.example.roomofmemory;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class DiaryDetailActivity extends AppCompatActivity {

    boolean liked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

        View heart = findViewById(R.id.heartIcon);

        heart.setOnClickListener(view -> {
            if(!liked){
                heart.setBackgroundResource(R.drawable.filled_heart);
                liked = true;
            }
            else{
                heart.setBackgroundResource(R.drawable.heart);
                liked = false;
            }
        });
    }
}
