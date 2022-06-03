package com.example.roomofmemory;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class PinDialog extends Dialog {
    private DialogListener dialogListener;

    private static PinDialog pinDialog;

    PinDialog(@NonNull Context context, DialogListener dialogListener) {
        super(context);
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.dialog_pin);

        Button btn_accept = findViewById(R.id.btn_accept2);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.clickBtn("accepted");
                dismiss();
            }
        });

        Button btn_decline = findViewById(R.id.btn_decline2);
        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.clickBtn("declined");
                dismiss();
            }
        });
    }

    public interface DialogListener {
        void clickBtn(String data);
    }

    public static PinDialog getInstance(Context context, DialogListener dialogListener) {
        pinDialog = new PinDialog(context, dialogListener);
        return pinDialog;
    }
}
