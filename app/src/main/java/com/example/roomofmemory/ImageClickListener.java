package com.example.roomofmemory;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ImageClickListener implements View.OnClickListener {
    Context context;
    int imageID;
    public ImageClickListener(Context context, int imageID){
        this.context = context;
        this.imageID = imageID;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("imageID",imageID);
        context.startActivity(intent);
    }
}
