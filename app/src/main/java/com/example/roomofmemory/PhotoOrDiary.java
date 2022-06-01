package com.example.roomofmemory;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.InputStream;

public class PhotoOrDiary extends AppCompatActivity {

    ImageButton addPhotoVideo;
    ImageButton addDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_or_diary);

        addPhotoVideo = (ImageButton)findViewById(R.id.addPhotoVideo);
        addDiary = (ImageButton) findViewById(R.id.addDiary);

        addPhotoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectPhoto.class);
                startActivity(intent);
            }
        });

        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDiary.class);
                startActivity(intent);
            }
        });

    }
}