package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {
    String date;
    TextView txt_date;

    private int[] imageIDs = new int[]{
        R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.tool_home:
                Intent intent = new Intent(GalleryActivity.this, RoomsActivity.class);
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
        setContentView(R.layout.activity_gallery);

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Gallery");

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        txt_date = findViewById(R.id.txt_date);
        txt_date.setText(date);

        GridView images = findViewById(R.id.grid_view);
        GalleryAdapter imageAdapter = new GalleryAdapter(this, imageIDs);
        images.setAdapter(imageAdapter);

    }
}