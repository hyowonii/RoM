package com.example.roomofmemory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;

public class WriteDiary extends AppCompatActivity {

    SharedPreferences pref;

    ImageButton addBtn;
    Uri uri;
    ImageView imageView;
    Button doneWrite;
    String date;
    EditText new_content;
    String newContentInput;

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

        pref = getSharedPreferences("info", MODE_PRIVATE);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        // Toolbar를 액티비티의 App Bar로 지정
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Message");

        // 사진 추가 버튼
        addBtn = (ImageButton) findViewById(R.id.addImageOnDiaryBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setDataAndType(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityResult.launch(intent);
            }
        });
        imageView = (ImageView) findViewById(R.id.addImageOnDiary);

        // 새로 입력한 내용
        new_content = (EditText) findViewById(R.id.newContentInput);

        doneWrite = (Button) findViewById(R.id.doneWrite);
        doneWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 새 다이어리 목록 생성 true
                DateDetail.addDiaryBool = true;

                // 내용 넘겨주기
                newContentInput = new_content.getText().toString(); // 입력한 다이어리 내용 가져오
                // sharedpreference에 저장
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("newContentInput", newContentInput);
//                editor.commit();
                // 사진 저장
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                String img = BitmapToString(bitmap);
                editor.putString("addImageOnDiary", img);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), DateDetail.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {

                        uri = (Uri)result.getData().getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);

                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

    // Bitmap -> String
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
}