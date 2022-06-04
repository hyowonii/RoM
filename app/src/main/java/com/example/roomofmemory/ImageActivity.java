package com.example.roomofmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_image);

        ImageView imageView = findViewById(R.id.img_simple);
        setImage(imageView);
    }
    private void setImage(ImageView imageView){
        Intent receivedIntent = getIntent();

        int imageID = (Integer)receivedIntent.getExtras().get("imageID");
        imageView.setImageResource(imageID);
    }
}