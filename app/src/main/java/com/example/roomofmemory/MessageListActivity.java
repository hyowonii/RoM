package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MessageListActivity extends AppCompatActivity {
    int flag=0;
    private SharedPreferences pref;
    String room_name, people1, people2;
    CardView card;
    TextView txt_people1, txt_people2;
    Button btn_room;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.tool_home:
                Intent intent = new Intent(MessageListActivity.this, RoomsActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        pref = getSharedPreferences("info",MODE_PRIVATE);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",flag);

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Message");

        card = findViewById(R.id.card_new);
        btn_room = findViewById(R.id.btn_ewha_msg);
        txt_people1 = findViewById(R.id.txt_people1);
        txt_people2 = findViewById(R.id.txt_people2);
        if (flag == 1){
            room_name = pref.getString("room_name","Ewha");
            people1 = pref.getString("people1","user1");
            people2 = pref.getString("people2","user2");
            card.setVisibility(View.VISIBLE);
            btn_room.setText(room_name);
            txt_people1.setText(people1);
            txt_people2.setText(people2);
        }

    }
    //activity_message_list 에서 msg_ewha_btn 의 onclick 메소드
    public void show_msg_dialog(View v){
        //클릭 시 msgDialog를 띄워준다
        MsgDialog.getInstance(this).showMsgDialog();
    }
}