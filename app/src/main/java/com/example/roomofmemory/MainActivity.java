package com.example.roomofmemory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public CalendarView calendarView;
    public ImageButton plusBtn;
    public TextView textTop, addText;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar);
        //setContentView(R.layout.fragment_search);

        calendarView = findViewById(R.id.calendarView);
        plusBtn = findViewById(R.id.plusBtn);
        textTop = findViewById(R.id.textTop);
        addText = findViewById(R.id.addText);

        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        myData = new ArrayList<>();
        mAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(mAdapter);

        String loc = "In Ewha womans university";
        String content1 = "정문에 디올 사진 걸린 거 봄? 짱이다\n너무 신기해~~";
        String content2 = "정문 최고 맛집이 어디임? 왤케 사라진\n식당이 많은거야ㅠㅠ 정말 속상해..";
        String content3 = "정문에서 아산 공학관 몇 분 컷 가능?\n 나 지금 이대역임ㅠ";
        myData.add(new MyData("___kwon___", loc, content1, "Be first to reply...                                                                34 ♥", R.drawable.photo1 ));
        myData.add(new MyData("hand_ewha", loc, content2,  "3 comments...                                                                       12 ♥", R.drawable.photo1));
        myData.add(new MyData("이화여니_니", loc, content3, "Be first to reply...                                                                34 ♥", R.drawable.photo1 ));

//        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                //핀 설정할 수 있는 옵션 주기?
//            }
//        });

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