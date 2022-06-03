package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RoomsActivity extends AppCompatActivity {

    ImageButton imgBtn_msg, imgBtn_new_room;
    TextView txt_room_name;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);
        editor = pref.edit();

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",flag);

        txt_room_name = findViewById(R.id.txt_room_ewha);
        setRoomName();

        imgBtn_msg = findViewById(R.id.imgBtn_msg);
        imgBtn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomsActivity.this, MessageListActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });

        imgBtn_new_room = findViewById(R.id.imgBtn_room_ewha);
        imgBtn_new_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0){  //new room 만들기 전이면 creating room 으로 이동
                    Intent intent = new Intent(RoomsActivity.this, CreateRoomActivity.class);
                    startActivity(intent);
                } else if (flag ==1){
                    //new room 만들었다면 그 방으로 이동
                }

            }
        });
    }
    public void setRoomName(){
        String res = pref.getString("room_name","");
        txt_room_name.setText(res);
    }
}