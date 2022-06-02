package com.example.roomofmemory;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MsgDialog extends Dialog {
    private static MsgDialog msgDialog;
    private MsgDialog(@NonNull Context context){
        super(context);
    }
    public static MsgDialog getInstance(Context context){
        msgDialog = new MsgDialog(context);
        return msgDialog;
    }
    //dialog 생성하기
    public void showMsgDialog(){
        //참조할 다이얼로그 화면을 연결한다.
        msgDialog.setContentView(R.layout.dialog_message);

        //다이얼로그의 구성요소들이 동작할 코드 작성
        Button btn_accept = msgDialog.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "초대를 수락했습니다.", Toast.LENGTH_SHORT).show();
                // 초대 수락했을 때 할 일
            }
        });
        Button btn_decline = msgDialog.findViewById(R.id.btn_decline);
        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "초대를 거절했습니다.", Toast.LENGTH_SHORT).show();
                msgDialog.dismiss();
                // 초대 거절했을 때 할 일
            }
        });
        ImageButton imgBtn_close = msgDialog.findViewById(R.id.imgBtn_close);
        imgBtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgDialog.dismiss();
            }
        });
        msgDialog.show();
    }
}
