package com.example.roomofmemory;

import static com.example.roomofmemory.GalleryActivity.StringToBitmap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myData;
    SharedPreferences pref;
    String newContentInput, contentInput;
    private ArrayList<String> searchList;
    private ImageView thumbnail;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);

        //search card view - recycler view 연결
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        myData = new ArrayList<>();
        searchList = new ArrayList<>();

        String loc = "In Ewha womans university";
        String reply = "Be first to reply...                                                                3 ♥";

        //검색 될 리스트 추가
        String content1 = "정문에 디올 사진 걸린 거 봄? \n짱이다 너무 신기해~~";
        String content2 = "맛집 추천 please 정문에서..\n 왤케 사라진 식당이 많은거야ㅠㅠ";
        String content3 = "정문에서 공학관 몇 분 컷 가능?\n 나 지금 이대역임ㅠ";
        String content4 = "오늘 뭐먹을까? 점메추 좀\n 후문 식당이면 더 좋음";
        String content5 = "아 컴파일러 과제 어떡하지 어떡하지...";
        String content6 = "날씨 좋은데 한강가고 싶다\n 나랑 따릉이 탈 사람?";
        searchList.add(content1);
        searchList.add(content2);
        searchList.add(content3);
        searchList.add(content4);
        searchList.add(content5);
        searchList.add(content6);
        if(DateDetail.addDiaryBool){
            contentInput = pref.getString("newContentInput", newContentInput);
            searchList.add(contentInput);
        }

        Intent intent = getIntent();

        String query = intent.getStringExtra("query");

        //리스트를 돌면서 해당 쿼리가 들어가 있는 content, nickname 가져와서 myData에 set하기
        for (String content:searchList) {
            if (content.contains(query)) {
                myData.add(new MyData("hyowon", loc, content, reply, R.drawable.photo2));
            }
        }

        mAdapter = new MyAdapter(this, myData, query);
        recyclerView.setAdapter(mAdapter);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
