package com.example.roomofmemory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WriteDiary extends AppCompatActivity {

    Button doneWrite;
    String date;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.tool_home:
                Intent intent = new Intent(WriteDiary.this, RoomsActivity.class);
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
        setContentView(R.layout.activity_write_diary);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Message");

        doneWrite = (Button) findViewById(R.id.doneWrite);
        doneWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DateDetail.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}