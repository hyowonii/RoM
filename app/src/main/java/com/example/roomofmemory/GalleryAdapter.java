package com.example.roomofmemory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
    Context context;
    int[] imageIDs = null;

    public GalleryAdapter(Context context, int[] imageIDs){
        this.context = context;
        this.imageIDs = imageIDs;
    }
    @Override
    public int getCount() {
        return (null != imageIDs) ? imageIDs.length : 0;
    }

    @Override
    public Object getItem(int i) {
        return (null != imageIDs) ? imageIDs[i] : 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = null;

        if (view != null){
            imageView = (ImageView) view;
        } else {
            // GridView 뷰를 구성할 ImageView 뷰의 비트맵을 정의합니다.
            // 그리고 그것의 크기를 320*240으로 줄입니다.
            // 크기를 줄이는 이유는 메모리 부족 문제를 막을 수 있기 때문입니다.
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[i]);
            bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);

            // GridView 뷰를 구성할 ImageView 뷰들을 정의합니다.
            // 뷰에 지정할 이미지는 앞에서 정의한 비트맵 객체입니다.
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bmp);

            imageView.setMaxWidth(320);
            imageView.setMaxHeight(240);
            imageView.setImageResource(imageIDs[i]);

            ImageClickListener imageViewClickListener = new ImageClickListener(context, imageIDs[i]);
            imageView.setOnClickListener(imageViewClickListener);
        }
        return imageView;
    }
}
