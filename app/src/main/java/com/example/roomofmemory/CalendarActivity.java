package com.example.roomofmemory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Collections;

public class CalendarActivity extends AppCompatActivity {

    //search 관련
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myData;
    private MaterialCalendarView calendarView;
    boolean pinned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        searchView = findViewById(R.id.searchView);

        //search card view - recycler view 연결
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        myData = new ArrayList<>();
        mAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(mAdapter);

        CharSequence query = searchView.getQuery();
        if (query.equals("정문")){
            String loc = "In Ewha womans university";
            String content1 = "정문에 디올 사진 걸린 거 봄? 짱이다\n너무 신기해~~";
            String content2 = "정문 최고 맛집이 어디임? 왤케 사라진\n식당이 많은거야ㅠㅠ 정말 속상해..";
            String content3 = "정문에서 아산 공학관 몇 분 컷 가능?\n 나 지금 이대역임ㅠ";
            myData.add(new MyData("___kwon___", loc, content1, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
            myData.add(new MyData("hand_ewha", loc, content2, "3 comments...                                                                       12 ♥", R.drawable.photo1));
            myData.add(new MyData("이화여니_니", loc, content3, "Be first to reply...                                                                34 ♥", R.drawable.photo1));

            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            String loc = "In Ewha womans university";
            String content1 = "오늘 뭐먹을까? 점메추 해줘\n 후문 식당이면 더 좋음";
            String content2 = "아 컴파일러 과제 어떡하지 어떡하지 어떡하지...";
            String content3 = "날씨 좋은데 한강가고 싶다\n 나랑 따릉이 탈 사람?";
            myData.add(new MyData("___kwon___", loc, content1, "Be first to reply...                                                                34 ♥", R.drawable.photo1));
            myData.add(new MyData("hand_ewha", loc, content2, "3 comments...                                                                       12 ♥", R.drawable.photo1));
            myData.add(new MyData("이화여니_니", loc, content3, "Be first to reply...                                                                34 ♥", R.drawable.photo1));

            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            finish();
        }

        //해당 날짜 누르면 핀 생성, 다시 누르면 핀 없애기
        calendarView = findViewById(R.id.calendarView);
        OneDayDecorator decorator = new OneDayDecorator(Collections.singleton(CalendarDay.today()));
        calendarView.setSelectedDate(CalendarDay.today());
        if(!pinned){
            calendarView.addDecorator(decorator);
            pinned = true;
        }
        else{
            calendarView.removeDecorator(decorator);
            pinned = false;
        }

    }
}