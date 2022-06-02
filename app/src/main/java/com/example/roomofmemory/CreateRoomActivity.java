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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateRoomActivity extends AppCompatActivity {

    private SharedPreferences pref;
    EditText et_roomName, et_userName;
    TextView txt_ppl1, txt_ppl2;
    Button btn_create;
    ImageButton imgBtn_search;
    ImageView img_ppl1, img_ppl2;
    String user1, user2;
    int flag=0;
    int cnt=0;

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



        img_ppl1 = findViewById(R.id.img_people1);
        txt_ppl1 = findViewById(R.id.txt_people1);
        img_ppl2 = findViewById(R.id.img_people2);
        txt_ppl2 = findViewById(R.id.txt_people2);
        et_userName = findViewById(R.id.edit_user_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // 사용자 검색 시
                cnt++;
                if (cnt ==1){
                    user1 = et_userName.getText().toString();   // 사용자 이름 가져오기
                    img_ppl1.setVisibility(View.VISIBLE);   // 선택된 사용자 이미지 표시
                    txt_ppl1.setText(user1);    // 선택된 사용자 이름 표시
                } else if (cnt ==2){
                    user2 = et_userName.getText().toString();   // 사용자 이름 가져오기
                    img_ppl2.setVisibility(View.VISIBLE);   // 선택된 사용자 이미지 표시
                    txt_ppl2.setText(user2);    // 선택된 사용자 이름 표시
                }

            }
        });

        et_roomName = findViewById(R.id.room_name);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("room_name",getRoomName());
                editor.putString("people1",user1);
                editor.putString("people2",user2);
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