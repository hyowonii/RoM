package com.example.roomofmemory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myData;
    private View card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //search card view - recycler view 연결
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        myData = new ArrayList<>();

        Intent intent = getIntent();

        if(intent.getStringExtra("t").equals("ok")){
            String loc = "In Ewha womans university";
            String content1 = "정문에 디올 사진 걸린 거 봄? 짱이다\n너무 신기해~~";
            String content2 = "정문 최고 맛집이 어디임? 왤케 사라진\n식당이 많은거야ㅠㅠ 정말 속상해..";
            String content3 = "정문에서 아산 공학관 몇 분 컷 가능?\n 나 지금 이대역임ㅠ";
            myData.add(new MyData("__kwon__", loc, content1, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
            myData.add(new MyData("handewha", loc, content2, "3 comments...                                                                       12 ♥", R.drawable.photo1));
            myData.add(new MyData("이화여니_니", loc, content3, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
        }
        else if(intent.getStringExtra("t").equals("nope")){
            String loc = "In Ewha womans university";
            String content1 = "오늘 뭐먹을까? 점메추 좀\n 후문 식당이면 더 좋음";
            String content2 = "아 컴파일러 과제 어떡하지 어떡하지...";
            String content3 = "날씨 좋은데 한강가고 싶다\n 나랑 따릉이 탈 사람?";
            myData.add(new MyData("__kwon__", loc, content1, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
            myData.add(new MyData("handewha", loc, content2, "3 comments...                                                                       12 ♥", R.drawable.photo1));
            myData.add(new MyData("이화여니_니", loc, content3, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
        }

        mAdapter = new MyAdapter(this, myData);
        recyclerView.setAdapter(mAdapter);
    }
}
