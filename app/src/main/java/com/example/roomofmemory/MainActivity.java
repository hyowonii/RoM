package com.example.roomofmemory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String readDay = null;
    public String str = null;
    public CalendarView calendarView;
    public ImageButton plusBtn;
    public TextView textTop, addText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        plusBtn = findViewById(R.id.plusBtn);
        textTop = findViewById(R.id.textTop);
        addText = findViewById(R.id.addText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //핀 설정할 수 있는 옵션 주기?
            }
        });

//        plusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), PhotoOrDiary.class); //photos or diary activity로 이동
//                startActivity(intent);
//
//                finish();
//            }
//        });

    }
}