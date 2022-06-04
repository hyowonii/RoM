package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDetail extends AppCompatActivity {

    static boolean addDiaryBool = false;    // 다이어리 목록 생성 여부

    ImageButton addDiary, imgBtn_before, imgBtn_after;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView txt_roomName, txt_date, newDiaryContent;
    LinearLayout diaryDetail1, newDiary;
    ImageView newDiaryImage;
    String room_name, today_date, date, newContentInput;
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

        newDiary = (LinearLayout) findViewById(R.id.newDiary);
        if(addDiaryBool == false) {
            newDiary.setVisibility(View.GONE);
        } else {
            newDiary.setVisibility(View.VISIBLE);
        }

        // 새로운 다이어리 작성 시 나타나는 내용
        //내용
        newDiaryContent = (TextView) findViewById(R.id.newDiaryContent);
        newContentInput = pref.getString("newContentInput", newContentInput);
        newDiaryContent.setText(newContentInput);
        //사진
        String img = pref.getString("addImageOnDiary", "");
        Bitmap bitmap = StringToBitmap(img);
        newDiaryImage = (ImageView) findViewById(R.id.newDiaryImage);
        newDiaryImage.setImageBitmap(bitmap);

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

        // 다이어리 디테일로 이동
        diaryDetail1 = (LinearLayout) findViewById(R.id.diaryDetail1);
        diaryDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryDetailActivity.class);
                startActivity(intent);
            }
        });
        newDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    //  String ->  BitMap
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}