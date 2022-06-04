package com.example.roomofmemory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ImageView img_new;
    Bitmap bmp;

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

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);
        editor = pref.edit();

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Gallery");

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        txt_date = findViewById(R.id.txt_date);
        txt_date.setText(date);

        img_new = findViewById(R.id.img_new);
        bmp = StringToBitmap(pref.getString("newImage",""));
        img_new.setImageBitmap(bmp);

    }
    //  String ->  BitMap
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}