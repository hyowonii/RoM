package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class RoomsActivity extends AppCompatActivity {

    ImageButton imgBtn_msg, imgBtn_new_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        imgBtn_msg = findViewById(R.id.imgBtn_msg);
        imgBtn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomsActivity.this, MessageListActivity.class);
                startActivity(intent);
            }
        });

        imgBtn_new_room = findViewById(R.id.imgBtn_room_ewha);
        imgBtn_new_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomsActivity.this, CreateRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}