package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRoomActivity extends AppCompatActivity {

    private SharedPreferences pref;
    EditText et_roomName;
    Button btn_create;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        pref = getSharedPreferences("info",MODE_PRIVATE);

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Creating Room");

        et_roomName = findViewById(R.id.room_name);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("room_name",getRoomName());
                editor.commit();
                Intent intent = new Intent(CreateRoomActivity.this, RoomsActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });

    }
    public String getRoomName(){
        String name = et_roomName.getText().toString();
        return name;
    }
}