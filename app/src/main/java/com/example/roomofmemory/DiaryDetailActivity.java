package com.example.roomofmemory;

import static com.example.roomofmemory.GalleryActivity.StringToBitmap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

public class DiaryDetailActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView newDiaryContent, diaryNickname;
    CardView comment1, comment2;
    ImageView newDiaryImage;
    String newContentInput;
    static boolean addDiaryBool = false;
    static boolean searchBool = false;

    boolean liked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

        pref = getSharedPreferences("info", Activity.MODE_PRIVATE);

        diaryNickname = findViewById(R.id.dirary_nickname);
        newDiaryContent = findViewById(R.id.diary_content);
        newDiaryImage = findViewById(R.id.diary_image);

        //새로운 내용으로 세팅
        if(addDiaryBool) {
            // 새로운 다이어리 작성 시 나타나는 내용
            //닉네임
            diaryNickname.setText("hyowon");
            //내용
            newContentInput = pref.getString("newContentInput", newContentInput);
            newDiaryContent.setText(newContentInput);
            //사진
            String img = pref.getString("addImageOnDiary", "");
            Bitmap bitmap = StringToBitmap(img);
            newDiaryImage.setImageBitmap(bitmap);

            comment1 = findViewById(R.id.commentView);
            comment2 = findViewById(R.id.commentView2);
            comment1.setVisibility(View.INVISIBLE);
            comment2.setVisibility(View.INVISIBLE);
        }
        else{
            if(searchBool){
                //검색 후 클릭 시 해당하는 사진과 내용 보여주기
                String content = getIntent().getStringExtra("content");
                int thumbnail = getIntent().getIntExtra("thumbnail", R.drawable.photo2);
                newDiaryContent.setText(content);
                newDiaryImage.setImageResource(thumbnail);
            }
        }

        View heart = findViewById(R.id.heartIcon);

        heart.setOnClickListener(view -> {
            if(!liked){
                heart.setBackgroundResource(R.drawable.filled_heart);
                liked = true;
            }
            else{
                heart.setBackgroundResource(R.drawable.heart);
                liked = false;
            }
        });
    }
}
