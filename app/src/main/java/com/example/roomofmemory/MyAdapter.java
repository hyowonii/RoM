package com.example.roomofmemory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<MyData> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String content = mDataset.get(position).content;
        SpannableString spannableString = new SpannableString(content);

        String word = "정문";
        int start = content.indexOf(word);
        int end = start + word.length();

        // 타켓 word의 색상 변경
        spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.content.setText(spannableString);
        holder.thumbnail.setImageResource(mDataset.get(position).img);
        holder.nickname.setText(mDataset.get(position).nickname);
        holder.location.setText(mDataset.get(position).location);
        holder.reply.setText(mDataset.get(position).reply);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView thumbnail;
        public TextView nickname, location, content, reply;

        public ViewHolder(View view) {
            super(view);
            thumbnail = (ImageView)view.findViewById(R.id.card_thumbnail);
            nickname = (TextView)view.findViewById(R.id.card_nickname);
            location = (TextView)view.findViewById(R.id.card_location);
            content = (TextView)view.findViewById(R.id.card_content);
            reply = (TextView)view.findViewById(R.id.card_reply);

            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭 이벤트
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mContext, DiaryDetailActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }
}


class MyData{
    public String location;
    public String content;
    public String nickname;
    public String reply;
    public int img;
    public MyData(String nickname, String location, String content, String reply, int img){
        this.nickname = nickname;
        this.location = location;
        this.content = content;
        this.reply = reply;
        this.img = img;
    }
}
