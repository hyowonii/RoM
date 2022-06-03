package com.example.roomofmemory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    //search 관련
    private SearchView searchView;
    public ArrayList<MyData> myData;
    public MaterialCalendarView calendarView;

    //pin 관련
    private ImageButton plusBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        searchView = findViewById(R.id.searchView);

        myData = new ArrayList<>();

        plusBtn = findViewById(R.id.plusBtn);

        Calendar calendar = Calendar.getInstance();
        ArrayList<CalendarDay> dates = new ArrayList<>();

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
    }

}