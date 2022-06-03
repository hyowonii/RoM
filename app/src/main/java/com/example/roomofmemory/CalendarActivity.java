package com.example.roomofmemory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    //search 관련
    private SearchView searchView;
    public ArrayList<MyData> myData;
    private MaterialCalendarView calendarView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    TextView txt_roomName;
    String room_name, today_date;
    ImageButton imgBtn_plus;

    Date mDate;
    long mNow;
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
                Intent intent = new Intent(CalendarActivity.this, RoomsActivity.class);
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
        setContentView(R.layout.activity_calendar);

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);
        editor = pref.edit();

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        calendarView = findViewById(R.id.calendarView);
        searchView = findViewById(R.id.searchView);
        txt_roomName = findViewById(R.id.textTop);
        room_name = pref.getString("room_name","Ewha");
        txt_roomName.setText(room_name);
        myData = new ArrayList<>();

        //특정 날짜에 핀
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        ArrayList<CalendarDay> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CalendarDay day = CalendarDay.from(calendar);
            dates.add(day);
            calendar.add(Calendar.DATE, 5);
        }

        calendarView.setSelectedDate(calendar);
        calendarView.addDecorators(new OneDayDecorator(dates, this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("정문")){
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("t", "ok");
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("t", "nope");
                    startActivity(intent);
                    finish();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        imgBtn_plus = findViewById(R.id.plusBtn);
        imgBtn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                today_date = mFormat.format(mDate);
                editor.putString("today_date",today_date);
                editor.commit();
                Intent intent = new Intent(CalendarActivity.this, PhotoOrDiary.class);
                intent.putExtra("date", today_date);
                startActivity(intent);
            }
        });


    }
}