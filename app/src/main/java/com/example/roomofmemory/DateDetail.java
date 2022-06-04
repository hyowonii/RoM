package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDetail extends AppCompatActivity {

    ImageButton addDiary, imgBtn_before, imgBtn_after, imgBtn_gal;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView txt_roomName, txt_date;
    String room_name, today_date, date;
    Calendar cal;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy / MM / dd");

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.tool_home:
                Intent intent = new Intent(DateDetail.this, RoomsActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_detail);

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        txt_roomName = findViewById(R.id.textView8);
        room_name = pref.getString("room_name","Ewha");
        txt_roomName.setText(room_name);

        cal = Calendar.getInstance();
        try {
            mDate = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(mDate);

        txt_date = findViewById(R.id.textView6);
        txt_date.setText(date);

        addDiary = (ImageButton)findViewById(R.id.addDiaryBtn);
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDiary.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

        imgBtn_before = findViewById(R.id.imageButton4);
        imgBtn_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.DATE, -1);
                date = mFormat.format(cal.getTime()).toString();
                txt_date.setText(date);
            }
        });
        imgBtn_after = findViewById(R.id.imageButton3);
        imgBtn_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.DATE, 1);
                date = mFormat.format(cal.getTime()).toString();
                txt_date.setText(date);
            }
        });

        imgBtn_gal = findViewById(R.id.imageButton5);
        imgBtn_gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}