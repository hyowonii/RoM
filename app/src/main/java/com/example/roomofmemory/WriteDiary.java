package com.example.roomofmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WriteDiary extends AppCompatActivity {

    Button doneWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        doneWrite = (Button) findViewById(R.id.doneWrite);
        doneWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DateDetail.class);
                startActivity(intent);
            }
        });
    }
}