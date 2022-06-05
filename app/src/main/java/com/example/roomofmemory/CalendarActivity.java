package com.example.roomofmemory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
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

    //pin 관련
    private ImageButton plusBtn;

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

        plusBtn = findViewById(R.id.plusBtn);

        Calendar calendar = Calendar.getInstance();
        ArrayList<CalendarDay> dates = new ArrayList<>();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            long delay = 0;
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (System.currentTimeMillis() > delay){
                    delay = System.currentTimeMillis() + 200;
                    return;
                }
                if( System.currentTimeMillis() <= delay ){
                    Intent intent = new Intent(getApplicationContext(), DateDetail.class);
                    Date selectedDate = date.getDate();
                    String day = mFormat.format(selectedDate);
                    intent.putExtra("date", day);
                    startActivity(intent);
                }
            }
        });

        plusBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //날짜 받아와서 dates, calendar 에 저장
                PinDialog pinDialog = new PinDialog(CalendarActivity.this, new PinDialog.DialogListener() {
                    @Override
                    public void clickBtn(String data) {
                        if (data.equals("accepted")) {
                            CalendarDay currentDate = calendarView.getSelectedDate();
                            dates.add(currentDate);
                            calendar.setTime(currentDate.getDate());
                            Toast.makeText(CalendarActivity.this, "pinned", Toast.LENGTH_SHORT).show();
                            calendarView.setSelectedDate(currentDate.getDate());
                            calendarView.addDecorators(new OneDayDecorator(dates, CalendarActivity.this));
                        }
                    }
                });
                pinDialog.show();
                return true;
            }
        });

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